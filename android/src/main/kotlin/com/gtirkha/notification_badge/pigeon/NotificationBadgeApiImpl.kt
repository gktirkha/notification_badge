package com.gtirkha.notification_badge.pigeon

import android.content.Context
import android.content.SharedPreferences
import com.gtirkha.notification_badge.badge_provider.BadgeProvider
import com.gtirkha.notification_badge.badge_provider.HTCBadgeProvider
import com.gtirkha.notification_badge.badge_provider.HuaweiBadgeProvider
import com.gtirkha.notification_badge.badge_provider.LGBadgeProvider
import com.gtirkha.notification_badge.badge_provider.NovaLauncherBadgeProvider
import com.gtirkha.notification_badge.badge_provider.OnePlusBadgeProvider
import com.gtirkha.notification_badge.badge_provider.OppoBadgeProvider
import com.gtirkha.notification_badge.badge_provider.SamsungBadgeProvider
import com.gtirkha.notification_badge.badge_provider.SonyBadgeProvider
import com.gtirkha.notification_badge.badge_provider.VivoBadgeProvider
import com.gtirkha.notification_badge.badge_provider.XiaomiBadgeProvider

class NotificationBadgeApiImpl(context: Context) : NotificationBadgeApi {
    private val prefs: SharedPreferences =
        context.getSharedPreferences("notification_badge", Context.MODE_PRIVATE)

    private val badgeProviders: List<BadgeProvider> = listOf(
        SamsungBadgeProvider(context),
        XiaomiBadgeProvider(context),
        HuaweiBadgeProvider(context),
        OppoBadgeProvider(context),
        VivoBadgeProvider(context),
        OnePlusBadgeProvider(context),
        SonyBadgeProvider(context),
        HTCBadgeProvider(context),
        LGBadgeProvider(context),
        NovaLauncherBadgeProvider(context)
    )

    override fun setCount(callback: (Result<Boolean>) -> Unit) {
        TODO("Not yet implemented")
    }
}