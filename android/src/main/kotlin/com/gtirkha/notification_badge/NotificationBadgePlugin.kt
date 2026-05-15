package com.gtirkha.notification_badge

import com.gtirkha.notification_badge.pigeon.NotificationBadgeApi
import com.gtirkha.notification_badge.pigeon.NotificationBadgeApiImpl
import io.flutter.embedding.engine.plugins.FlutterPlugin


class NotificationBadgePlugin : FlutterPlugin {
    private var api: NotificationBadgeApi? = null
    override fun onAttachedToEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        api = NotificationBadgeApiImpl()
        NotificationBadgeApi.setUp(binaryMessenger = binding.binaryMessenger, api = api)
    }

    override fun onDetachedFromEngine(binding: FlutterPlugin.FlutterPluginBinding) {
        api = null
        NotificationBadgeApi.setUp(binaryMessenger = binding.binaryMessenger, api = api)
    }
}
