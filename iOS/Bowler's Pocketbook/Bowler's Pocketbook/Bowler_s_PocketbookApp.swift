//
//  Bowler_s_PocketbookApp.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

@main
struct Bowler_s_PocketbookApp: App {
    var body: some Scene {
        WindowGroup {
            TabView {
                BuildArsenalView().tabItem {
                    Label("Build Arsenal", systemImage: "gauge")
                }.tag(1)
                Text("Tab Content 2").tabItem {
                    Label("Find a Ball", systemImage: "magnifyingglass")
                }.tag(2)
                HomeView().tabItem {
//                    Image(systemName: "house.fill").font(.system(size: 44, weight: .bold)).foregroundColor(.red)
                    Label("Home", systemImage: "house.fill").foregroundColor(.red)
                }.tag(3)
                Text("Tab Content 4").tabItem {
                    Label("Log Scores", systemImage:"archivebox")
                }.tag(4)
                Text("Tab Content 5").tabItem {
                    Label("Analyze Scores", systemImage: "gear")
                }.tag(5)
            }
        }
    }
}
