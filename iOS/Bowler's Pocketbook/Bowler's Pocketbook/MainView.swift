//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

struct MainView: View {
    
    var body: some View {
        
        TabView(selection: /*@START_MENU_TOKEN@*//*@PLACEHOLDER=Selection@*/.constant(1)/*@END_MENU_TOKEN@*/) {
                Text("Tab Content 1").tabItem {
                    Label("Build Arsenal", systemImage: "gauge")
                }.tag(1)
                Text("Tab Content 2").tabItem {
                    Label("Find a Ball", systemImage: "magnifyingglass")
                }.tag(2)
                Text("Tab Content 3").tabItem {
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

struct MainView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            MainView()
        }
    }
}
