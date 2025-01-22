package com.microsoft.codepush.react;

import org.json.JSONException;
import org.json.JSONObject;

public class CodePushInvalidUpdateException extends RuntimeException {
    public CodePushInvalidUpdateException(String message) {
        super(message);
        logException(message);
    }

    private void logException(String message) {
        // Log the exception details
        JSONObject payload = new JSONObject();
        try {
            payload.put("exception", "CodePushInvalidUpdateException");
            payload.put("message", message);
            payload.put("timestamp", System.currentTimeMillis());
            CodePushUtils.reportAnalyticsEvent("Codepush_Rollback", payload);
        } catch (JSONException e) {
            CodePushUtils.log("Error logging invalidUpdateException");
        }
    }
}
