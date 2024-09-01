import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let databaseModule = DatabaseModule()
    
	var body: some Scene {
		WindowGroup {
            DecisionListScreen(decisionDataSource: databaseModule.decisionDataSource)
		}
	}
}
