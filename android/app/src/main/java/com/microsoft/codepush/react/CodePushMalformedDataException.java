package com.microsoft.codepush.react;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;

public class CodePushMalformedDataException extends RuntimeException {
    public CodePushMalformedDataException(String path, Throwable cause) {
        super("Unable to parse contents of " + path + ", the file may be corrupted.", cause);
        logException("Unable to parse contents of " + path + ", the file may be corrupted.", cause);
    }
    public CodePushMalformedDataException(String url, MalformedURLException cause) {
        super("The package has an invalid downloadUrl: " + url, cause);
        logException("The package has an invalid downloadUrl: " + url, cause);
    }

    private void logException(String message, Throwable cause) {
        // Log the exception details
        JSONObject payload = new JSONObject();
        try {
            payload.put("exception", "CodePushMalformedException");
            payload.put("message", message);
            payload.put("timestamp", System.currentTimeMillis());
            if (cause != null) {
                payload.put("cause", cause.getMessage());
            }
            CodePushUtils.reportAnalyticsEvent("Codepush_rollback", payload);
        } catch (JSONException e) {
            CodePushUtils.log("Error logging malformedException");
        }
    }
}