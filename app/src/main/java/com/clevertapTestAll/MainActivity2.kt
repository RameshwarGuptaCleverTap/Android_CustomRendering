package com.clevertapTestAll

import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.ActivityResultLauncher
import androidx.annotation.RequiresApi
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.ManifestInfo
import java.util.*
import kotlin.collections.HashMap
import com.clevertap.android.sdk.pushnotification.PushConstants

class MainActivity2 : AppCompatActivity() {

    var cleverTapDefaultInstance: CleverTapAPI? = null

    private lateinit var requestLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        cleverTapDefaultInstance = CleverTapAPI.getDefaultInstance(applicationContext)
        cleverTapDefaultInstance!!.enableDeviceNetworkInfoReporting(true)
        CleverTapAPI.setDebugLevel(3)

        if(cleverTapDefaultInstance != null){
            CleverTapAPI.createNotificationChannelGroup(applicationContext, "1234", "CleverTapPushAndroid")
            CleverTapAPI.createNotificationChannel(applicationContext, "CT4444", "CT-PushAndroid", "Test-NotificationsAndroid", NotificationManager.IMPORTANCE_MAX, "1234", true)
        }

        onUserLoginMethod()

        if (intent != null) {
            CleverTapAPI.getDefaultInstance(applicationContext)?.pushNotificationClickedEvent(intent.extras)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        if (intent != null) {
            CleverTapAPI.getDefaultInstance(applicationContext)?.pushNotificationClickedEvent(intent.extras)
        }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun askForNotificationPermission() {
       // requestLauncher.launch(Manifest.permission.po)
    }

    private fun onUserLoginMethod() {

        val profileUpdate = HashMap<String, Any>()
        profileUpdate["Name"] = "Rameshwar Gupta" // String
        profileUpdate["Identity"] = 934875123943987654 // String or number
        profileUpdate["Email"] = "rameshwa09090912@yahoo.co." // Email address of the user
        profileUpdate["Phone"] = "+919911123321" // Phone (with the country code, starting with +)
        profileUpdate["Gender"] = "M" // Can be either M or F
        profileUpdate["DOB"] = Date() // Date of Birth. Set the Date object to the appropriate value first
        profileUpdate["Photo"] = "www.foobar.com/image.jpeg" // URL to the Image

        profileUpdate["MSG-email"] = false // Disable email notifications
        profileUpdate["MSG-push"] = true // Enable push notifications
        profileUpdate["MSG-sms"] = false // Disable SMS notifications
        profileUpdate["MSG-dndPhone"] = true // Opt out phone
        profileUpdate["MSG-dndEmail"] = true // Opt out email
        profileUpdate["MyStuff"] = arrayListOf("bag", "shoes") //ArrayList of Strings
        profileUpdate["MyStuff"] = arrayOf("Jeans", "Perfume") //String Array
        cleverTapDefaultInstance?.pushProfile(profileUpdate)
    }


    fun sendEventsOnProfile() {

        val prodViewedAction = mapOf(
            "Product Name" to "Casio Chronograph Watch",
            "Category" to "Mens Accessories",
            "Price" to 59.99,
            "Date" to Date())
        cleverTapDefaultInstance?.pushEvent("Product viewed", prodViewedAction)
    }
}