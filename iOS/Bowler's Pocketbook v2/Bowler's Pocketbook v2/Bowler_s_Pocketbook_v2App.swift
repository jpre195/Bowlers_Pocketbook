//
//  Bowler_s_Pocketbook_v2App.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI

@main
struct Bowler_s_Pocketbook_v2App: App {
    let persistenceController = PersistenceController.shared

    var body: some Scene {
        WindowGroup {
            ContentView()
                .environment(\.managedObjectContext, persistenceController.container.viewContext)
        }
    }
}
