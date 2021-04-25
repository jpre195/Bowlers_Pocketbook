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
                    Label("Build Arsenal", systemImage: "hammer")
                }.tag(1)
                FindABallView().tabItem {
                    Label("Find a Ball", systemImage: "magnifyingglass")
                }.tag(2)
                HomeView().tabItem {
//                    Image(systemName: "house.fill").font(.system(size: 44, weight: .bold)).foregroundColor(.red)
                    Label("Home", systemImage: "house.fill").foregroundColor(.red)
                }.tag(3)
                LogScoresView().tabItem {
                    Label("Log Scores", systemImage:"archivebox")
                }.tag(4)
                AnalyzeScoresView().tabItem {
                    Label("Analyze Scores", systemImage: "gauge")
                }.tag(5)
            }
        }
    }
}
