import Flutter
import UIKit
import UserNotifications

public class NotificationBadgePlugin: NSObject, FlutterPlugin {
  public static func register(with registrar: FlutterPluginRegistrar) {
    let api = NotificationBadgeApiImpl()
    NotificationBadgeApiSetup.setUp(binaryMessenger: registrar.messenger(), api: api)

    let instance = NotificationBadgePlugin()
    NotificationCenter.default.addObserver(
      instance,
      selector: #selector(applicationDidEnterBackground),
      name: UIApplication.didEnterBackgroundNotification,
      object: nil
    )
  }

  @objc private func applicationDidEnterBackground() {
    DispatchQueue.main.async {
      let count = UIApplication.shared.applicationIconBadgeNumber
      guard count > 0 else { return }
      if #available(iOS 16.0, *) {
        UNUserNotificationCenter.current().setBadgeCount(count) { error in
          if let error = error {
            print("[NotificationBadge] Error preserving badge count in background: \(error.localizedDescription)")
          }
        }
      } else {
        UIApplication.shared.applicationIconBadgeNumber = count
      }
    }
  }

  deinit {
    NotificationCenter.default.removeObserver(self)
  }
}
