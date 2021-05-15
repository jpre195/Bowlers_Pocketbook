//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
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
    
    @State var scores : [NSManagedObject] = []
    
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
                            
                            Button(action: {
                                self.addScore()
                                hideKeyboard()
                            }, label: {
                                Text("Add score")
                            })
                            
                            Spacer()
                            
                        }
                        
                    }

                    
                }.navigationBarTitle("Log Scores")
                
                
            }
        
        
        
    }
    
    func addScore() {
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let managedContext = appDelegate.persistentContainer.viewContext
        
        let entity = NSEntityDescription.entity(forEntityName: "Score", in: managedContext)!
        
        let newScore = NSManagedObject(entity: entity, insertInto: managedContext)
        
        print("Setting values")
        
        newScore.setValue($selectedEvent.wrappedValue, forKeyPath: "eventType")
//        newScore.setValue(Int($score.value.wrappedValue), forKey: "score")
//        newScore.setValue(Int($game.value.wrappedValue), forKey: "game")
//        newScore.setValue(gameDate.format(), forKey: "date")
//        newScore.setValue(nil, forKey: "ballUsed")
        
        do {
            
            try managedContext.save()
            print("Saved successfully.")
            self.loadScores()
            
        } catch let error as NSError {
            print("Could not save. \(error), \(error.userInfo)")
        }
        
    }
    
    func loadScores() {
        
        guard let appDelegate = UIApplication.shared.delegate as? AppDelegate else {
            return
        }
        
        let managedContext = appDelegate.persistentContainer.viewContext
        
        let fetchRequest = NSFetchRequest<NSManagedObject>(entityName: "Score")
        
        do {
            scores = try managedContext.fetch(fetchRequest)
        } catch let error as NSError {
            print("Could not fetch. \(error), \(error.userInfo)")
        }
        
    }
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
