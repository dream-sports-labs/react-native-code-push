package com.microsoft.codepush.react;

import org.json.JSONException;
import org.json.JSONObject;

class CodePushUnknownException extends RuntimeException {

    public CodePushUnknownException(String message, Throwable cause) {
        super(message, cause);
        logException(message, cause);
    }

    public CodePushUnknownException(String message) {
        super(message);
        logException(message, null);
    }

    private void logException(String message, Throwable cause) {
        // Log the exception details
        JSONObject payload = new JSONObject();
        try {
            payload.put("exception", "CodePushUnknownException");
            payload.put("message", message);            if (cause != null) {
                payload.put("cause", cause.getMessage());
            }
            CodePushUtils.reportAnalyticsEvent("Codepush_rollback", payload);
        } catch (JSONException e) {
            CodePushUtils.log("Error logging Unknown exception");
        }
    }
}