package com.example.learn.nabtati.services.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.learn.R
import com.example.learn.nabtati.presentation.MainActivity

class NabtatiNotificationService(
    private val context: Context
) {

    private val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    fun showNotification(content: String){
        // pending intent
        val activityIntent = Intent(context, MainActivity::class.java)

        val pendingActivityIntent = PendingIntent.getActivity(
            context,
            123,
            activityIntent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, MAIN_CHANNEL_ID)
            .setSmallIcon(R.drawable.leaf)
            .setContentTitle("Nabtati")
            .setContentText(content)
            .setContentIntent(pendingActivityIntent)
            .build()

        notificationManager.notify(1, notification)
    }

    companion object {
        const val MAIN_CHANNEL_ID = "main_channel"
    }
}