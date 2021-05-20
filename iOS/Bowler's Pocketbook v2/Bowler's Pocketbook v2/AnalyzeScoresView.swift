//
//  AnalyzeScoresView.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI
import CoreData

struct AnalyzeScoresView: View {

//    @State var scores : [NSManagedObject] = []
    
    @State private var showingSearch = false

    @Environment(\.managedObjectContext) private var viewContext

    @FetchRequest(
        sortDescriptors: [NSSortDescriptor(keyPath: \Score.date, ascending: false)],
        animation: .default)
    private var scores: FetchedResults<Score>

    var body: some View {

        NavigationView {
            
            List {
                ForEach(scores) { (score: Score) in
                    HStack {
                        Image(systemName: "person.circle")
                            .resizable()
                            .frame(width: 48, height: 48)
        
                        VStack(alignment: .leading) {
                            Text(score.eventType ?? "Event type error")
                                .font(.system(size: 12))
        //                    Text("Event Type")
        //                        .font(.system(size: 12))
                            Text("Game: \(score.game)")
                                .font(.system(size: 12))
                            Text("\(score.date!, formatter: dateFormatter)")
                                .font(.system(size: 12))
                            
                            
                        }
        
                        Spacer()
        
                        Text(String(score.score))
                            .font(.system(size: 24))
                    }
                }
                .onDelete(perform: deleteScores)
            }

//            List(scores, id: \.id) { item in
//                Image(systemName: "person.circle")
//                    .resizable()
//                    .frame(width: 48, height: 48)
//
//                VStack(alignment: .leading) {
//                    Text(item.eventType ?? "Event type error")
//                        .font(.system(size: 12))
////                    Text("Event Type")
////                        .font(.system(size: 12))
//                    Text("Date")
//                        .font(.system(size: 12))
//                }
//
//                Spacer()
//
//                Text("Score")
//
//            }

//            List(scores, id: \.id) { item in
//                Image(systemName: "person.circle")
//                    .resizable()
//                    .frame(width: 48, height: 48)
//
//                VStack(alignment: .leading) {
//                    Text((item as? Score)?.eventType ?? "Event type error")
//                        .font(.system(size: 12))
////                    Text("Event Type")
////                        .font(.system(size: 12))
//                    Text("Date")
//                        .font(.system(size: 12))
//                }
//
//                Spacer()
//
//                Text("Score")
//
//            }

//            List(0 ..< 5) { item in
//                Image(systemName: "person.circle")
//                    .resizable()
//                    .frame(width: 48, height: 48)
//
//                VStack(alignment: .leading) {
//                    Text("Event Type")
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
            .toolbar {
                ToolbarItem(placement: .navigationBarTrailing) {
                    HStack {
                        Button(action: {
                            showingSearch.toggle()
                        }, label : {
                            Image(systemName: "magnifyingglass")
                        })
                        .sheet(isPresented: $showingSearch) {
                            SearchScoreView()
                        }
                        Spacer()
                    }
                }
            }
        }
    }
    
    private func deleteScores(offsets: IndexSet) {
        withAnimation {
            offsets.map { scores[$0] }.forEach(viewContext.delete)

            do {
                try viewContext.save()
            } catch {
                // Replace this implementation with code to handle the error appropriately.
                // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
                let nsError = error as NSError
                fatalError("Unresolved error \(nsError), \(nsError.userInfo)")
            }
        }
    }
    
    private let dateFormatter: DateFormatter = {
        let formatter = DateFormatter()
        formatter.dateStyle = .short
        return formatter
    }()
}

struct AnalyzeScores_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            AnalyzeScoresView()
        }
    }
}

