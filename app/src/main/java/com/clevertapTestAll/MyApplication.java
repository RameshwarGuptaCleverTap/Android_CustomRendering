package com.clevertapTestAll;

import android.app.Application;

import com.clevertap.android.pushtemplates.PushTemplateNotificationHandler;
import com.clevertap.android.sdk.ActivityLifecycleCallback;
import com.clevertap.android.sdk.CleverTapAPI;
import com.clevertap.android.sdk.interfaces.NotificationHandler;


public class MyApplication extends Application {
    @Override
    public void onCreate() {
        ActivityLifecycleCallback.register(this);
        super.onCreate();
        CleverTapAPI.setNotificationHandler((NotificationHandler)new PushTemplateNotificationHandler());

    }
}
