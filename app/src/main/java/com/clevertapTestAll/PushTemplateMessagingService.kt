package com.clevertapTestAll



import android.R
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import com.clevertap.android.sdk.CleverTapAPI
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import org.json.JSONArray
import java.util.*


class PushTemplateMessagingService : FirebaseMessagingService() {
    private var NOTIFICATION_ID = 1
    override fun onMessageReceived(remoteMessage: RemoteMessage) {

        Log.d(
            "TAG",
            "customRenderNotification() called with: remoteMessage = [$remoteMessage]"
        )

// Apply the layouts to the notification
        if (remoteMessage.data == null) {
            return
        }
        val extras = Bundle()
        for ((key, value) in remoteMessage.data.entries) {
            extras.putString(key, value)
        }
        val title = remoteMessage.data["nt"]
        val message = remoteMessage.data["nm"]
        val channelId = remoteMessage.data["wzrk_cid"]



        val intent = Intent(this, MainActivity2::class.java)
        intent.putExtras(extras)
        val contentIntent = PendingIntent.getActivity(applicationContext,0,intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT)


        val customNotification: NotificationCompat.Builder = NotificationCompat.Builder(
            applicationContext, channelId!!
        )

            .setSmallIcon(R.drawable.arrow_up_float)
            .setStyle(NotificationCompat.DecoratedCustomViewStyle())
            .setContentTitle(title)
            .setContentText(message)
            .setColorized(true)
            .setContentIntent(contentIntent)
            .setAutoCancel(true)


        // Add as notification
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(0, customNotification.build())
        CleverTapAPI.getDefaultInstance(applicationContext)?.pushNotificationViewedEvent(extras)
    }
}
