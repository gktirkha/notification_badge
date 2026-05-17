package com.gtirkha.notification_badge.badge_provider

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class UniversalBadgeProvider(private val context: Context) : BadgeProvider {

    companion object {
        private const val CHANNEL_ID = "notification_badge_channel"
        private const val NOTIFICATION_ID = 0x42414447
    }

    override fun isSupported(): Boolean = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O

    override fun setBadgeCount(count: Int): Boolean {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) return false

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU &&
            ContextCompat.checkSelfPermission(context, android.Manifest.permission.POST_NOTIFICATIONS)
            != PackageManager.PERMISSION_GRANTED
        ) {
            return false
        }

        return try {
            ensureChannel()
            val manager = NotificationManagerCompat.from(context)
            if (count <= 0) {
                manager.cancel(NOTIFICATION_ID)
            } else {
                val notification = NotificationCompat.Builder(context, CHANNEL_ID)
                    .setSmallIcon(getNotificationIcon())
                    .setNumber(count)
                    .setPriority(NotificationCompat.PRIORITY_MIN)
                    .setDefaults(0)
                    .build()
                manager.notify(NOTIFICATION_ID, notification)
            }
            true
        } catch (_: Exception) {
            false
        }
    }

    private fun ensureChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "App Badge",
                NotificationManager.IMPORTANCE_MIN
            ).apply {
                setShowBadge(true)
                enableLights(false)
                enableVibration(false)
                setSound(null, null)
            }
            (context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager)
                .createNotificationChannel(channel)
        }
    }

    private fun getNotificationIcon(): Int {
        val resId = context.resources.getIdentifier("ic_notification", "drawable", context.packageName)
        return if (resId != 0) resId else android.R.drawable.ic_popup_reminder
    }
}
