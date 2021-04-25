//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

struct LogScoresView: View {
    
    var body: some View {

        VStack {
            HStack {
                Text("Log Scores").bold().font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
            }
            Spacer()
        }
        
    }
}

struct LogScoresView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            LogScoresView()
        }
    }
}
