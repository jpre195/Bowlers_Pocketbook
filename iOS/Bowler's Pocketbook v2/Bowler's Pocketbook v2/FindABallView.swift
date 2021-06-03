//
//  FindABallView.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI

struct FindABallView: View {
    
    @State private var showingSearch = false
    
    var body: some View {

        VStack {

//            HStack {
//                Spacer()
//                Button(action: {}) {
//                    Image(systemName: "magnifyingglass")
//                }.padding()
//            }
            
//            HStack {
//                Text("Find a Ball")
//                    .font(.system(size: 32, weight : .heavy))
//                    .padding()
//                Spacer()
//
//                Button(action: {
//                    showingSearch.toggle()
//                }, label : {
//                    Image(systemName: "magnifyingglass")
//                        .resizable()
//                        .frame(width : 25, height: 25)
//                })
//                .sheet(isPresented: $showingSearch) {
//                    SearchBallView()
//                }.padding()
//            }
//
            NavigationView {

                List(/*@START_MENU_TOKEN@*/0 ..< 5/*@END_MENU_TOKEN@*/) { item in
                    Image(systemName: "person.circle")
                        .resizable()
                        .frame(width: 48, height: 48)

                    VStack(alignment: .leading) {
                        Text("Brand")
                            .font(.system(size: 12))
                        Text("Ball")
                            .font(.system(size: 12))
                        Text("Coverstock")
                            .font(.system(size: 12))
                    }

                    Spacer()

                    VStack(alignment: .trailing) {

                        Text("RG")
                            .font(.system(size: 12))
                        Text("Differential")
                            .font(.system(size: 12))
                        Text("Core")
                            .font(.system(size: 12))


                    }

                }

                .navigationBarTitle("Find a Ball")
                .toolbar {
                    ToolbarItem(placement: .navigationBarTrailing) {
                        HStack {
                            Button(action: {
                                showingSearch.toggle()
                            }, label : {
                                Image(systemName: "magnifyingglass")
                                    .imageScale(.large)
//                                    .frame(width : 25, height: 25)
                            })
                            .sheet(isPresented: $showingSearch) {
                                SearchBallView()
                            }
                            Spacer()
                        }
                    }
                }
                
            }
        
        }

    }
}

struct FindABallView_Previews: PreviewProvider {
    static var previews: some View {
        FindABallView()
    }
}
