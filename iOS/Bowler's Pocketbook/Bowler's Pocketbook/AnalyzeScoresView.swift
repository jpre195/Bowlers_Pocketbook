//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI
import CoreData

struct AnalyzeScoresView: View {
    
    @State var scores : [NSManagedObject] = []
    
    var body: some View {
        
        NavigationView {

            List(/*@START_MENU_TOKEN@*/0 ..< 5/*@END_MENU_TOKEN@*/) { item in
                Image(systemName: "person.circle")
                    .resizable()
                    .frame(width: 48, height: 48)
                
                VStack(alignment: .leading) {
                    Text("Event type")
                        .font(.system(size: 12))
                    Text("Date")
                        .font(.system(size: 12))
                }
                
                Spacer()
                
                Text("Score")
                
            }
            
//            List(scores, id: \.self) { score in
//                Image(systemName: "person.circle")
//                    .resizable()
//                    .frame(width: 48, height: 48)
//
//                VStack(alignment: .leading) {
//                    Text("Event type")
//                        .font(.system(size: 12))
//                    Text("Date")
//                        .font(.system(size: 12))
//                }
//
//                Spacer()
//
//                Text("Score")
//
//            }
            
            .navigationBarTitle("View Scores")
        }
    }
}

struct AnalyzeScores_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            AnalyzeScoresView()
        }
    }
}
