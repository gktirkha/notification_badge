package com.gtirkha.notification_badge.badge_provider

import android.content.Context
import android.content.Intent
import android.os.Build

class OnePlusBadgeProvider(private val context: Context) : BadgeProvider {

    override fun isSupported(): Boolean {
        return Build.MANUFACTURER.lowercase().contains("oneplus")
    }

    override fun setBadgeCount(count: Int): Boolean {
        var anySent = false

        // Legacy OxygenOS launcher broadcast (pre-OxygenOS 12)
        try {
            val intent = Intent("com.oneplus.launcher.action.UPDATE_BADGE").apply {
                putExtra("packageName", context.packageName)
                putExtra("className", getLauncherActivityClass())
                putExtra("count", count)
            }
            context.sendBroadcast(intent)
            anySent = true
        } catch (_: Exception) { }

        // OxygenOS 12+ is ColorOS-based; use OPPO's method as well
        try {
            val intent = Intent("com.oppo.launcher.action.UPDATE_COUNT").apply {
                putExtra("packageName", context.packageName)
                putExtra("count", count)
                putExtra("upgradeNumber", count)
                putExtra("className", getLauncherActivityClass())
            }
            context.sendBroadcast(intent)
            anySent = true
        } catch (_: Exception) { }

        return anySent
    }

    private fun getLauncherActivityClass(): String {
        val launchIntent = context.packageManager.getLaunchIntentForPackage(context.packageName)

        return launchIntent?.component?.className ?: ""
    }
}