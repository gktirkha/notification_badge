package com.gtirkha.notification_badge.badge_provider

import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle

class HuaweiBadgeProvider(private val context: Context) : BadgeProvider {

    override fun isSupported(): Boolean {
        val manufacturer = Build.MANUFACTURER.lowercase()
        return manufacturer.contains("huawei") || manufacturer.contains("honor")
    }

    override fun setBadgeCount(count: Int): Boolean {
        return try {
            // Method 1: Try using Huawei's badge provider
            if (tryHuaweiBadgeProvider(count)) {
                return true
            }

            // Method 2: Try using broadcast intent
            if (tryHuaweiBroadcast(count)) {
                return true
            }

            false
        } catch (e: Exception) {
            false
        }
    }

    private fun tryHuaweiBadgeProvider(count: Int): Boolean {
        return try {
            val contentValues = ContentValues().apply {
                put("package", context.packageName)
                put("class", getLauncherActivityClass())
                put("badgenumber", count)
            }

            val uri = Uri.parse("content://com.huawei.android.launcher.settings/badge/")
            val result = context.contentResolver.insert(uri, contentValues)
            result != null
        } catch (e: Exception) {
            false
        }
    }

    private fun tryHuaweiBroadcast(count: Int): Boolean {
        return try {
            val bundle = Bundle().apply {
                putString("package", context.packageName)
                putString("class", getLauncherActivityClass())
                putInt("badgenumber", count)
            }

            val intent =
                Intent("com.huawei.android.launcher.action.CHANGE_APPLICATION_NOTIFICATION_NUM").apply {
                    putExtras(bundle)
                }

            context.sendBroadcast(intent)
            true
        } catch (e: Exception) {
            false
        }
    }

    private fun getLauncherActivityClass(): String {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        return intent?.component?.className ?: ""
    }
}