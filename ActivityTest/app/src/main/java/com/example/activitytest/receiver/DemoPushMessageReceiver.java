package com.example.activitytest.receiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.baidu.android.pushservice.PushManager;
import com.example.activitytest.FirstActivity;

import java.util.List;

public class DemoPushMessageReceiver extends com.baidu.android.pushservice.PushMessageReceiver {

    private static final String TAG = "DemoPushMessageReceiver";

    @Override
    public void onBind(Context context, int errorCode, String appid,
                       String userId, String channelId, String requestId) {
        // 绑定成功的回调, 先绑定成功是接收push的前提
        String log = "onBind: errorCode=" + errorCode + ", appid=" + appid
                + ", userId=" + userId + ", channelId=" + channelId + ", requestId=" + requestId;
        Log.d(TAG, log);
        PushManager.requestOppoNotification(context);
    }

    @Override
    public void onUnbind(Context context, int errorCode, String requestId) {
        // 解除绑定成功的回调, 先绑定成功是接收push的前提
        String log = "Unbind: errorCode=" + errorCode + ", requestId=" + requestId;
        Log.d(TAG, log);
        // 用户关闭push(即解绑)的回调
    }
    // onMessage 方法从v7.2.1版本开始新增notifyId参数
    // 若使用透传消息上通知栏，为了支持透传通知栏消息覆盖
    // ，接入方请使用 notifyId 作为 NotificationManager的notify方法中的 id 参数。
    @Override
    public void onMessage(Context context, String message, String customContentString, int notifyId, int source) {
        // 在这里处理自定义透传类型消息的展示及落地页跳转。以下为手百的实现, 供业务方参考
        // 手百消息体: http://wiki.baidu.com/pages/viewpage.action?pageId=699360232
        // 【注】此回调在bdservice_v1进程。通过广播, 交由主线程处理
        try {
            Log.d(TAG, "onMessage: " + message + "_" + customContentString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onNotificationClicked(Context context, String title, String description,
                                      String customContentString) {
        Log.d(TAG, "onNotificationClicked customContentString = " + customContentString);
        // 在这里处理通知类型消息(含厂商代理)的落地页跳转
        Intent intent = new Intent(context, FirstActivity.class);
        intent.setPackage(context.getPackageName());
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    @Override
    public void onNotificationArrived(Context context, String title, String description,
                                      String customContentString) {
        Log.d(TAG, "onNotificationArrived customContentString = " + customContentString);
        // 仅长连接模式下的通知类型消息会回调。若接入代理推送，不回调。
    }

    @Override
    public void onSetTags(Context context, int errorCode,
                          List<String> successTags, List<String> failTags,
                          String requestId) {
        String log = "onSetTags: errorCode=" + errorCode +
                "  successTags = " + successTags + "  failTags= "
                + failTags + ", requestId=" + requestId;
        Log.d(TAG, log);
    }

    @Override
    public void onDelTags(Context context, int errorCode,
                          List<String> successTags, List<String> failTags,
                          String requestId) {
        String log = "onSetTags: errorCode=" + errorCode +
                ", successTags = " + successTags + ", failTags= "
                + failTags + ", requestId=" + requestId;
        Log.d(TAG, log);
    }

    @Override
    public void onListTags(Context context, int errorCode,
                           List<String> tags, String requestId) {
        String log = "onSetTags: errorCode=" + errorCode +
                ",tags = " + tags + ",requestId=" + requestId;
        Log.d(TAG, log);
    }
}
