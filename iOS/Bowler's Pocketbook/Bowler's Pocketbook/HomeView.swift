//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

struct HomeView: View {
    
    var body: some View {
        VStack {
            Text("Bowler's Pocketbook").font(.title).bold()
            Spacer()
            
            HStack {
                
                Button("Build Arsenal") {
                    
                }.padding(24)
                
                Button("Find a Ball") {

                }.padding(24)
            }
            
            HStack {
                Button("Log Scores") {
                    Text("Log Scores")
                }.padding(24)
                
                Button("Analyze Scores") {
                    Text("Analyze Scores")
                }.padding(24)
            }
            
            Spacer()
        }
        
    }
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            HomeView()
        }
    }
}
