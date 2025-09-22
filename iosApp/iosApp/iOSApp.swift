import SwiftUI
import OSLog

fileprivate let logger = Logger(
    subsystem: "io.lackstudio.module.kmp.apiclient.app",
    category: "General")

@main
struct iOSApp: App {
    init() {
        logger.info("iOSApp.swift")
        logger.debug("iOSApp.swift")
    }
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
