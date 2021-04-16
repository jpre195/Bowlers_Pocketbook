//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

struct BuildArsenalView: View {
    
    var body: some View {

        VStack {
            HStack {
                Text("Build Arsenal").bold().font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/)
            }
            Spacer()
        }
        
    }
}

struct BuildArsenalView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            BuildArsenalView()
        }
    }
}
