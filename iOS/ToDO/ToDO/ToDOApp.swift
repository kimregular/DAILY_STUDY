//
//  ToDOApp.swift
//  ToDO
//
//  Created by Regular Kim on 4/13/26.
//

import SwiftUI
import SwiftData

@main
struct ToDOApp: App {
    
    var sharedModelContainer: ModelContainer = {
        let schema = Schema ([
            Todo.self
        ])
        
        let modelConfiguration = ModelConfiguration(schema: schema, isStoredInMemoryOnly: false)
        
        do {
            return try ModelContainer(for: schema, configurations: [modelConfiguration])
        } catch {
            fatalError("Could not create ModelContainer: \(error)")
        }
    }()
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
        .modelContainer(sharedModelContainer)
    }
}
