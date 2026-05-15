package com.gtirkha.notification_badge.badge_provider

interface BadgeProvider {
    fun isSupported(): Boolean
    fun setBadgeCount(count: Int): Boolean
}