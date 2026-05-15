import 'package:notification_badge/src/platform/notification_badge_platform_interface.dart';

class NotificationBadge {
  final _instance = NotificationBadgePlatformInterface.instance;

  Future<bool> checkPermissions() async {
    return await _instance.checkPermissions();
  }

  Future<bool> decrementCount() async {
    return await _instance.decrementCount();
  }

  Future<int> getBadgeCount() async {
    return await _instance.getBadgeCount();
  }

  Future<String> getDeviceManufacturer() async {
    return await _instance.getDeviceManufacturer();
  }

  Future<bool> incrementCount() async {
    return await _instance.incrementCount();
  }

  Future<bool> isSupported() async {
    return await _instance.isSupported();
  }

  Future<bool> requestPermissions() async {
    return await _instance.requestPermissions();
  }

  Future<bool> setCount(int count) async {
    return await _instance.setCount(count);
  }
}
