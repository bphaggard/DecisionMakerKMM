import SwiftUI
import shared

@main
struct iOSApp: App {
    
    private let databaseModule = DatabaseModule()
    
    init() {
        KoinInitializerKt.doInitKoin()
    }
    
	var body: some Scene {
		WindowGroup {
            DecisionListScreen(decisionDataSource: databaseModule.decisionDataSource)
		}
	}
}
