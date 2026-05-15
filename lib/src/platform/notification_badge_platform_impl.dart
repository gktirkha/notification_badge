import '../pigeon/notification_badge_api.g.dart';
import 'notification_badge_platform_interface.dart';

class NotificationBadgePlatformImpl
    implements NotificationBadgePlatformInterface {
  final api = NotificationBadgeApi();

  @override
  Future<bool> checkPermissions() async {
    return await api.checkPermissions();
  }

  @override
  Future<bool> decrementCount() async {
    return await api.decrementCount();
  }

  @override
  Future<int> getBadgeCount() async {
    return await api.getBadgeCount();
  }

  @override
  Future<String> getDeviceManufacturer() async {
    return await api.getDeviceManufacturer();
  }

  @override
  Future<bool> incrementCount() async {
    return await api.incrementCount();
  }

  @override
  Future<bool> isSupported() async {
    return await api.isSupported();
  }

  @override
  Future<bool> requestPermissions() async {
    return await api.requestPermissions();
  }

  @override
  Future<bool> setCount(int count) async {
    return await api.setCount(count);
  }
}
