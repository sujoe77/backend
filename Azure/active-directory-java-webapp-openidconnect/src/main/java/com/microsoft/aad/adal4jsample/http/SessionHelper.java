package com.microsoft.aad.adal4jsample.http;

import com.microsoft.aad.adal4j.AuthenticationResult;
import com.microsoft.aad.adal4jsample.auth.AuthHelper;
import com.microsoft.aad.adal4jsample.entity.StateData;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class SessionHelper {

    public static final Integer STATE_TTL = 3600;
    public static final String STATES = "states";

    public static AuthenticationResult getSessionPrincipal(HttpServletRequest request) {
        return (AuthenticationResult) request.getSession().getAttribute(AuthHelper.PRINCIPAL_SESSION_NAME);
    }

    public static void setSessionPrincipal(HttpServletRequest httpRequest, AuthenticationResult result) {
        httpRequest.getSession().setAttribute(AuthHelper.PRINCIPAL_SESSION_NAME, result);
    }

    public static void removePrincipalFromSession(HttpServletRequest httpRequest) {
        httpRequest.getSession().removeAttribute(AuthHelper.PRINCIPAL_SESSION_NAME);
    }

    @SuppressWarnings("unchecked")
    public static void storeStateInSession(HttpSession session, String state, String nonce) {
        if (session.getAttribute(STATES) == null) {
            session.setAttribute(STATES, new HashMap<String, StateData>());
        }
        ((Map<String, StateData>) session.getAttribute(STATES)).put(state, new StateData(nonce, new Date()));
    }

    @SuppressWarnings("unchecked")
    public static StateData removeStateFromSession(HttpSession session, String state) {
        Map<String, StateData> states = (Map<String, StateData>) session.getAttribute(STATES);
        if (states != null) {
            eliminateExpiredStates(states);
            StateData stateData = states.get(state);
            if (stateData != null) {
                states.remove(state);
                return stateData;
            }
        }
        return null;
    }

    public static void eliminateExpiredStates(Map<String, StateData> map) {
        Iterator<Map.Entry<String, StateData>> it = map.entrySet().iterator();
        Date currTime = new Date();
        while (it.hasNext()) {
            Map.Entry<String, StateData> entry = it.next();
            long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(currTime.getTime() - entry.getValue().getExpirationDate().getTime());
            if (diffInSeconds > STATE_TTL) {
                it.remove();
            }
        }
    }

    public static HashMap<String, String> getParameterMap(HttpServletRequest httpRequest) {
        HashMap<String, String> params = new HashMap<>();
        for (String key : httpRequest.getParameterMap().keySet()) {
            params.put(key, httpRequest.getParameterMap().get(key)[0]);
        }
        return params;
    }

    public static URI getFullUri(HttpServletRequest httpRequest) throws URISyntaxException {
        String currentUri = httpRequest.getRequestURL().toString();
        String queryStr = httpRequest.getQueryString();
        String fullUrl = currentUri + (queryStr != null ? "?" + queryStr : "");
        return new URI(fullUrl);
    }
}
