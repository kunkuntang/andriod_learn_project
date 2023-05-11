package com.example.activitytest.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushManager;
import com.example.activitytest.FirstActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PushMessageReceiver extends com.baidu.android.pushservice.PushMessageReceiver {
    private static final String TAG = PushMessageReceiver.class.getSimpleName();

    @Override
    public void onBind(Context context, int errorCode, String appid, String userId, String channelId, String requestId) {
        Log.d(TAG, "onBind: channelId:" + channelId);
//        SessionMgr.shared().setChannelId(channelId);
    }

    @Override
    public void onUnbind(Context context, int errorCode, String requestId) {
        Log.d(TAG, "onUnbind: requestId:" + requestId);
    }

    @Override
    public void onSetTags(Context context, int errorCode, List sucessTags, List failTags, String requestId) {

    }

    @Override
    public void onDelTags(Context context, int i, List<String> list, List<String> list1, String s) {

    }

    @Override
    public void onListTags(Context context, int i, List<String> list, String s) {

    }

    @Override
    public void onMessage(Context context, String message, String customContentString, int notifyId, int source) {
        Log.d(TAG, "onUnbind: message:" + message);
    }

    @Override
    public void onNotificationClicked(Context context, String title, String description, String customContentString) {
        try {
            JSONObject json = new JSONObject(customContentString);
            Intent intent = new Intent(context.getApplicationContext(), FirstActivity.class);
            if (json.has("url")) {
                String url = json.getString("url");
                intent.putExtra("url", url);
            }
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            context.getApplicationContext().startActivity(intent);
        } catch (JSONException e) {
            Log.w(TAG, e);
        }
    }

    @Override
    public void onNotificationArrived(Context context, String title, String description, String
            customContentString) {

    }
}

