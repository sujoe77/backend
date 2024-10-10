package com.sxp.java.fintranscom.rest.client;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.*;

import static javax.ws.rs.core.HttpHeaders.*;
import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

public class AzureLoggingClient {
    public static final String HMAC_SHA256_ALG = "HmacSHA256";
    public static final String URL_AZURE_INSIGHTS = "https://%s.ods.opinsights.azure.com/api/logs?api-version=2016-04-01";
    public static final String SIGNATURE_HASH_FORMAT = "POST\n%d\napplication/json\nx-ms-date:%s\n/api/logs";
    public static final String SIGNATURE_FORMAT = "SharedKey %s:%s";
    public static final String FORMAT_RFC_1123 = "EEE, dd MMM yyyy HH:mm:ss z";

    private final String timeZone;
    private final String workspaceId;
    private final String workspaceKey;
    private final String logName;
    private final String timeStampField;

    public AzureLoggingClient(String workspaceId, String workspaceKey, String logName, String timeStampField, String timeZone) {
        this.workspaceId = workspaceId;
        this.workspaceKey = workspaceKey;
        this.logName = logName;
        this.timeStampField = timeStampField;
        this.timeZone = timeZone;
    }

    public void sendLog(String content) {
        String dateString = getRFC1123ForNow(timeZone);
        byte[] jsonBytes = content.getBytes(StandardCharsets.US_ASCII);
        String signature = buildSignature(dateString, workspaceKey, jsonBytes.length, workspaceId);
        postData(signature, dateString, content, logName, timeStampField, workspaceId);
    }

    public String buildSignature(String dateString, String key, int contentLength, String workspaceId) {
        try {
            byte[] bytes = Base64.decodeBase64(key);
            SecretKeySpec secret_key = new SecretKeySpec(bytes, HMAC_SHA256_ALG);
            Mac sha256_HMAC = Mac.getInstance(HMAC_SHA256_ALG);
            sha256_HMAC.init(secret_key);
            String signatureToHash = String.format(SIGNATURE_HASH_FORMAT, contentLength, dateString);
            byte[] encrypted = sha256_HMAC.doFinal(signatureToHash.getBytes());
            //printBytes(encrypted);
            String hash = Base64.encodeBase64String(encrypted);
            return String.format(SIGNATURE_FORMAT, workspaceId, hash);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public void postData(final String signature, final String date, String content, final String logName, final String timeStampField, String workspaceId) {
        String url = String.format(URL_AZURE_INSIGHTS, workspaceId);
        HashMap<String, String> headers = new HashMap<String, String>() {{
            put(ACCEPT, APPLICATION_JSON);
            put(AUTHORIZATION, signature);
            put(CONTENT_TYPE, APPLICATION_JSON);
            put("Log-Type", logName);
            put("x-ms-date", date);
            put("time-generated-field", timeStampField);
        }};
        try {
            sendPost(url, headers, content);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
    }

    private void sendPost(String url, Map<String, String> headers, String content) throws Exception {
        HttpPost post = new HttpPost(url);
        for (String name : headers.keySet()) {
            post.addHeader(name, headers.get(name));
        }
        post.setEntity(new StringEntity(content, ContentType.APPLICATION_JSON));

        try (CloseableHttpClient httpClient = HttpClients.createDefault(); CloseableHttpResponse response = httpClient.execute(post)) {
//            System.out.println(EntityUtils.toString(response.getEntity()));
        }
    }

    public String getRFC1123ForNow(String timeZone) {
        return DateFormatUtils.format(getDateForNow(timeZone), FORMAT_RFC_1123, Locale.US);
    }

    public Date getDateForNow(String timeZone) {
        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        return cal.getTime();
    }
}
