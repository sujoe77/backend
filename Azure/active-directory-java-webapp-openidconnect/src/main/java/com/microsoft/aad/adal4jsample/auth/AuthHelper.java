/*******************************************************************************
 // Copyright (c) Microsoft Corporation.
 // All rights reserved.
 //
 // This code is licensed under the MIT License.
 //
 // Permission is hereby granted, free of charge, to any person obtaining a copy
 // of this software and associated documentation files(the "Software"), to deal
 // in the Software without restriction, including without limitation the rights
 // to use, copy, modify, merge, publish, distribute, sublicense, and / or sell
 // copies of the Software, and to permit persons to whom the Software is
 // furnished to do so, subject to the following conditions :
 //
 // The above copyright notice and this permission notice shall be included in
 // all copies or substantial portions of the Software.
 //
 // THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 // IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 // FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.IN NO EVENT SHALL THE
 // AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 // LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 // OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 // THE SOFTWARE.
 ******************************************************************************/
package com.microsoft.aad.adal4jsample.auth;

import com.microsoft.aad.adal4j.AuthenticationContext;
import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4j.ClientCredential;
import com.microsoft.aad.adal4jsample.constant.AuthParameterNames;
import com.microsoft.aad.adal4jsample.http.SessionHelper;
import com.nimbusds.jwt.JWTParser;
import com.nimbusds.oauth2.sdk.AuthorizationCode;
import com.nimbusds.openid.connect.sdk.AuthenticationErrorResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import com.nimbusds.openid.connect.sdk.AuthenticationResponseParser;
import com.nimbusds.openid.connect.sdk.AuthenticationSuccessResponse;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.text.ParseException;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public final class AuthHelper {

    public static final String PRINCIPAL_SESSION_NAME = "principal";
    public static final String STATE = "state";

    private AuthHelper() {
    }

    public static boolean isAuthenticated(HttpServletRequest request) {
        return request.getSession().getAttribute(PRINCIPAL_SESSION_NAME) != null;
    }

    public static boolean containsAuthenticationData(HttpServletRequest httpRequest) {
        Map<String, String[]> parameterMap = httpRequest.getParameterMap();
        return httpRequest.getMethod().equalsIgnoreCase("POST")
                && (parameterMap.containsKey(AuthParameterNames.ERROR) ||
                parameterMap.containsKey(AuthParameterNames.ID_TOKEN) ||
                parameterMap.containsKey(AuthParameterNames.CODE));
    }

    public static boolean isAuthenticationSuccessful(AuthenticationResponse authResponse) {
        return authResponse instanceof AuthenticationSuccessResponse;
    }

    public static String getRedirectUrl(String currentUri, String state, String nonce, String authority, String tenant, String clientId) throws UnsupportedEncodingException {
        String redirectUrl = authority
                + tenant
                + "/oauth2/authorize?response_type=code&scope=directory.read.all&response_mode=form_post&redirect_uri="
                + URLEncoder.encode(currentUri, "UTF-8") + "&client_id="
                + clientId + "&resource=https%3a%2f%2fgraph.microsoft.com"
                + "&state=" + state
                + "&nonce=" + nonce;
        return redirectUrl;
    }

    public static boolean isAuthDataExpired(HttpServletRequest httpRequest) {
        AuthenticationResult authData = SessionHelper.getSessionPrincipal(httpRequest);
        return authData.getExpiresOnDate().before(new Date());
    }

    public static void updateAuthDataUsingRefreshToken(HttpServletRequest httpRequest, String authority, String tenent, String clientId, String clientSecret) throws Throwable {
        AuthenticationResult result = getAccessTokenFromRefreshToken(
                SessionHelper.getSessionPrincipal(httpRequest).getRefreshToken(),
                authority,
                tenent,
                clientId,
                clientSecret);
        SessionHelper.setSessionPrincipal(httpRequest, result);
    }

    public static AuthenticationResult getAccessTokenFromRefreshToken(String refreshToken, String authority, String tenant, String clientId, String clientSecret) throws Throwable {
        AuthenticationContext context;
        AuthenticationResult result = null;
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(1);
            context = new AuthenticationContext(authority + tenant + "/", true, service);
            Future<AuthenticationResult> future = context.acquireTokenByRefreshToken(refreshToken, new ClientCredential(clientId, clientSecret), null, null);
            result = future.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        } finally {
            service.shutdown();
        }

        if (result == null) {
            throw new ServiceUnavailableException("authentication result was null");
        }
        return result;
    }

    public static String getClaimValueFromIdToken(String idToken, String claimKey) throws ParseException {
        return (String) JWTParser.parse(idToken).getJWTClaimsSet().getClaim(claimKey);
    }

    public static AuthenticationResult getAccessToken(AuthorizationCode authorizationCode, ClientCredential credential, String authority, URI redirectUri) throws Throwable {
        ExecutorService service = Executors.newFixedThreadPool(1);
        AuthenticationContext context = new AuthenticationContext(authority, true, service);
        AuthenticationResult result = null;
        try {
            Future<AuthenticationResult> future = context.acquireTokenByAuthorizationCode(
                    authorizationCode.getValue(),
                    redirectUri,
                    credential,
                    null
            );
            result = future.get();
        } catch (ExecutionException e) {
            throw e.getCause();
        } finally {
            service.shutdown();
        }
        if (result == null) {
            throw new ServiceUnavailableException("authentication result was null");
        }
        return result;
    }

    public static void authenticate(HttpServletRequest httpRequest, String clientId, String clientSecret, String authority, String tenant) throws Throwable {
        // validate that state in response equals to state in request
        AuthenticationResponse authResponse = AuthenticationResponseParser.parse(
                SessionHelper.getFullUri(httpRequest),
                SessionHelper.getParameterMap(httpRequest)
        );
        if (isAuthenticationSuccessful(authResponse)) {
            AuthenticationSuccessResponse oidcResponse = (AuthenticationSuccessResponse) authResponse;
            // validate that OIDC Auth Response matches Code Flow (contains only requested artifacts)
            Validator.validateAuthRespMatchesCodeFlow(oidcResponse);
            AuthenticationResult result = getAccessToken(
                    oidcResponse.getAuthorizationCode(),
                    new ClientCredential(clientId, clientSecret),
                    authority + tenant + "/",
                    new URI(httpRequest.getRequestURL().toString())
            );
            // validate nonce to prevent reply attacks (code maybe substituted to one with broader access)
            Validator.validateResult(httpRequest, result);
            SessionHelper.setSessionPrincipal(httpRequest, result);
        } else {
            handleAuthenticationFailed((AuthenticationErrorResponse) authResponse);
        }
    }

    private static void handleAuthenticationFailed(AuthenticationErrorResponse authResponse) throws Exception {
        AuthenticationErrorResponse oidcResponse = authResponse;
        throw new Exception(String.format("Request for auth code failed: %s - %s",
                oidcResponse.getErrorObject().getCode(),
                oidcResponse.getErrorObject().getDescription()));
    }
}
