//
//  Bowler_s_PocketbookApp.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI
import CoreData

@main
struct Bowler_s_PocketbookApp: App {
    
    @State var selection = 1
    
    var body: some Scene {
        WindowGroup {
            
            TabView(selection: $selection) {
                BuildArsenalView().tabItem {
                    Label("Build Arsenal", systemImage: "hammer")
                }.tag(3)
                FindABallView().tabItem {
                    Label("Find a Ball", systemImage: "magnifyingglass")
                }.tag(2)
                HomeView().tabItem {
//                    Image(systemName: "house.fill").font(.system(size: 44, weight: .bold)).foregroundColor(.red)
                    Label("Home", systemImage: "house.fill").foregroundColor(.red)
                }.tag(1)
                LogScoresView().tabItem {
                    Label("Add Scores", systemImage:"plus.square")
                }.tag(4)
                AnalyzeScoresView().tabItem {
                    Label("View Scores", systemImage: "eyeglasses")
                }.tag(5)
            }
        }
    }
}
