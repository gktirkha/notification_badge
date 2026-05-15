import 'notification_badge_platform_impl.dart';
import 'package:plugin_platform_interface/plugin_platform_interface.dart';

abstract class NotificationBadgePlatformInterface extends PlatformInterface {
  NotificationBadgePlatformInterface() : super(token: _token);

  static final Object _token = Object();

  static NotificationBadgePlatformInterface _instance =
      NotificationBadgePlatformImpl();

  static NotificationBadgePlatformInterface get instance => _instance;

  static set instance(NotificationBadgePlatformInterface instance) {
    PlatformInterface.verifyToken(instance, _token);
    _instance = instance;
  }

  Future<bool> setCount(int count);

  Future<bool> isSupported();

  Future<int> getBadgeCount();

  Future<String> getDeviceManufacturer();

  Future<bool> incrementCount();

  Future<bool> decrementCount();

  Future<bool> checkPermissions();

  Future<bool> requestPermissions();
}
