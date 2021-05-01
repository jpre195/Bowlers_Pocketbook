//
//  ContentView.swift
//  Bowler's Pocketbook
//
//  Created by Jeffrey Preston on 3/31/21.
//

import SwiftUI

struct LogScoresView: View {
    
    let eventTypes: [String] = ["Practice", "League", "Tournament"]
    let scoreLimit = 3
    
    @State var selectedEvent = "Practice"
//    @State var score = ""
    @ObservedObject var score = TextLimiter(limit: 3)
    @State var gameDate = Date()
    
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
                        
                        TextField("Score", text: $score.value)
                            .keyboardType(.numberPad)
                        
                    }
                    
                    Section {
                        
                        HStack {
                            
                            Spacer()
                            
                            Button("Submit") {
                                hideKeyboard()
                            }.buttonStyle(BorderlessButtonStyle())
                            
                            Spacer()
                            
                        }
                        
                    }

                    
                }.navigationBarTitle("Log Scores")
                
                
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

struct LogScoresView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            LogScoresView()
        }
    }
}
