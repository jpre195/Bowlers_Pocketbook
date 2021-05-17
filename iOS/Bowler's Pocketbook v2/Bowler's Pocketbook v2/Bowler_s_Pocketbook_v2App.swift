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
    
    @State var selection = 1

    var body: some Scene {
        WindowGroup {
//            ContentView()
//                .environment(\.managedObjectContext, persistenceController.container.viewContext)
            
            TabView(selection: $selection) {
                BuildArsenalView().tabItem {
                    Label("Build Arsenal", systemImage: "hammer")
                }.tag(3).environment(\.managedObjectContext, persistenceController.container.viewContext)
                FindABallView().tabItem {
                    Label("Find a Ball", systemImage: "magnifyingglass")
                }.tag(2).environment(\.managedObjectContext, persistenceController.container.viewContext)
                HomeView().tabItem {
//                    Image(systemName: "house.fill").font(.system(size: 44, weight: .bold)).foregroundColor(.red)
                    Label("Home", systemImage: "house.fill").foregroundColor(.red)
                }.tag(1).environment(\.managedObjectContext, persistenceController.container.viewContext)
                LogScoresView().tabItem {
                    Label("Add Scores", systemImage:"plus.square")
                }.tag(4).environment(\.managedObjectContext, persistenceController.container.viewContext)
                AnalyzeScoresView().tabItem {
                    Label("View Scores", systemImage: "eyeglasses")
                }.tag(5).environment(\.managedObjectContext, persistenceController.container.viewContext)
            }
        }
    }
}
