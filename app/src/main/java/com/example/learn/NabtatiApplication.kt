package com.example.learn

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import com.example.learn.nabtati.services.notifications.NabtatiNotificationService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class NabtatiApplication: Application(){


    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val channel = NotificationChannel(
                NabtatiNotificationService.MAIN_CHANNEL_ID,
                "Main",
                NotificationManager.IMPORTANCE_DEFAULT,

            )

            channel.description = "Used for main notifications"

            // Create notification channel
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}