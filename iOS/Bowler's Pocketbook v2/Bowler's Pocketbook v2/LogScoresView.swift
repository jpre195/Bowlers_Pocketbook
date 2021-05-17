//
//  LogScoresView.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI
import CoreData

struct LogScoresView: View {

    let eventTypes: [String] = ["Practice", "League", "Tournament"]
    let scoreLimit = 3

    @State var selectedEvent = "Practice"
//    @State var score = ""
    @ObservedObject var score = TextLimiter(limit: 3)
    @ObservedObject var game = TextLimiter(limit: 2)
    @State var gameDate = Date()
    @State var showErrorMessage = false
    @State var showSuccessMessage = false

//    @State var scores : [NSManagedObject] = []

    @Environment(\.managedObjectContext) private var viewContext

    @FetchRequest(
        sortDescriptors: [NSSortDescriptor(keyPath: \Score.date, ascending: false)],
        animation: .default)
    private var scores: FetchedResults<Score>

    var body: some View {


//        VStack {
//
//            HStack {
//
//                Text("Select event type:").padding(10)
//
//                Spacer()
//
//                Picker("Event type",
//                       selection: $selectedEvent) {
//                                ForEach(eventTypes, id: \.self) {
//                                    Text($0)
//                                }
//                }.pickerStyle(MenuPickerStyle()).padding(20)
//
//
//            }.padding(.top, 20)
//
//            HStack {
//
//                Text("Ball used:").padding(10)
//
//                Spacer()
//
//                Text("You selected: \(selectedEvent)").padding(10)
//
//            }
//
//            HStack {
//
//                Text("Score:").padding(10)
//
//                Spacer()
//
//            }
//
//            HStack {
//
//                Text("Date:").padding(10)
//
//                Spacer()
//
//            }
//
//            Spacer()
//        }


        NavigationView {



                Form {

                    Section {

                        Picker(selection: $selectedEvent, label: Text("Event Type")) {
                            ForEach(eventTypes, id: \.self) {

                                Text($0)

                            }

                        }

                        Text("Ball used")

                        DatePicker(selection: $gameDate,
                                   displayedComponents: .date,
                                   label: { Text("Date") })

                        TextField("Game", text: $game.value)
                            .keyboardType(.numberPad)

                        TextField("Score", text: $score.value)
                            .keyboardType(.numberPad)

                    }

                    Section {

                        HStack {

                            Spacer()

//                            Button("Add score") {
//                                print("Loading scores")
//                                self.loadScores()
//                                print("Adding score")
//                                self.addScore()
//                                print("Added score")
//                                hideKeyboard()
//                            }.buttonStyle(BorderlessButtonStyle())

//                            Button(action: {
//                                print("\nCheckpoint 1\n")
//
//                                let newScore = Score(context: moc)
//
//                                print("\nCheckpoint 2\n")
//
//                                newScore.ballUsed = "Omega Crux"
//                                newScore.game = Int16(game.value) ?? 1
//                                newScore.score = Int32(score.value) ?? 0
//                                newScore.date = gameDate
//
//                                print("\nCheckpoint 3\n")
//
//                                try? self.moc.save()
//
//                                print("Score added")
//
//                                hideKeyboard()
//                            }, label: {
//                                Text("Add score")
//                            })
                            
                            Button(action: addScore) {
                                Label("Add Score", systemImage: "plus")
                            }
//                            .alert(isPresented: $showErrorMessage) {
//                                Alert(title: Text("Error"), message: Text("Score must be between 0 and 300."), dismissButton: .default(Text("OK")))
//                            }.alert(isPresented: $showSuccessMessage, content: {
//                                Alert(title: Text("Success"),
//                                      message: Text("Score added."),
//                                      dismissButton: .default(Text("Ok")))
//                            })

                            Spacer()

                        }

                    }


                }.navigationBarTitle("Log Scores")


            }



    }
    
    private func addScore() {
        withAnimation {
            let newScore = Score(context: viewContext)
//            newItem.timestamp = Date()
            
            if (Int(score.value)! > 300) || (Int(score.value)! < 0) {
                showErrorMessage = true
            }
            
            newScore.ballUsed = "Omega Crux"
            newScore.date = gameDate
            newScore.eventType = selectedEvent
            newScore.game = Int16(game.value) ?? 1
            newScore.score = Int32(score.value) ?? 0
            newScore.id = UUID()

            do {
                try viewContext.save()
                showSuccessMessage = true
            } catch {
                // Replace this implementation with code to handle the error appropriately.
                // fatalError() causes the application to generate a crash log and terminate. You should not use this function in a shipping application, although it may be useful during development.
                let nsError = error as NSError
                fatalError("Unresolved error \(nsError), \(nsError.userInfo)")
            }
        }
        
        hideKeyboard()
    }

//    func addScore() {
//        print("Add score start")
//
////        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
////            print("Checkpoint 0")
////            return
////        }
//
//        let appDelegate = UIApplication.shared.delegate as! AppDelegate
//
//        print("Checkpoint 1")
//
//        let managedContext = appDelegate.persistentContainer.viewContext
//
//        print("Checkpoint 2")
//
//        let entity = NSEntityDescription.entity(forEntityName: "Score", in: managedContext)!
//
//        print("Checkpoint 3")
//
//        let newScore = NSManagedObject(entity: entity, insertInto: managedContext)
//
//        print("Setting values")
//
//        newScore.setValue($selectedEvent.wrappedValue, forKeyPath: "eventType")
//        newScore.setValue(Int($score.value.wrappedValue), forKey: "score")
//        newScore.setValue(Int($game.value.wrappedValue), forKey: "game")
//        newScore.setValue(gameDate.format(), forKey: "date")
//        newScore.setValue(nil, forKey: "ballUsed")
//
//        print("Checkpoint 4")
//
//        do {
//
//            try managedContext.save()
//            print("Saved successfully.")
//            self.loadScores()
//
//        } catch let error as NSError {
//            print("Could not save. \(error), \(error.userInfo)")
//        }
//
//    }
//
//    func loadScores() {
//
//        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
//            return
//        }
//
//        let managedContext = appDelegate.persistentContainer.viewContext
//
//        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Score")
//
//        do {
//            scores = try managedContext.fetch(fetchRequest)
//        } catch let error as NSError {
//            print("Could not fetch. \(error), \(error.userInfo)")
//        }
//
//    }
}

#if canImport(UIKit)
extension View {
    func hideKeyboard() {
        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
    }
}
#endif

extension Date {
    /**
     Formats a Date

     - parameters format: (String) for eg dd-MM-yyyy hh-mm-ss
     */
    func format(format:String = "dd-MM-yyyy hh-mm-ss") -> Date {
        let dateFormatter = DateFormatter()
        dateFormatter.dateFormat = format
        let dateString = dateFormatter.string(from: self)
        if let newDate = dateFormatter.date(from: dateString) {
            return newDate
        } else {
            return self
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
