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
            
            VStack(spacing: 20) {
                
                HStack(spacing: 15) {
                    
                    MetricsView(dashlet: averageData[0])
                    MetricsView(dashlet: averageData[1])
                    
                }
                
                HStack(spacing: 15) {
                    
                    MetricsView(dashlet: averageData[2])
                    MetricsView(dashlet: averageData[3])
                    
                }
                
                HStack(spacing: 15) {
                    
                    MetricsView(dashlet: averageData[4])
                    MetricsView(dashlet: averageData[5])
                    
                }
                
            }.padding(10)
            
            ZStack {
                Color.black.ignoresSafeArea().cornerRadius(10)
                Color.white.ignoresSafeArea().cornerRadius(10).padding(1)
                
                VStack {
                    Text("Graphs here").foregroundColor(.black)
                }
            }
            
        }.edgesIgnoringSafeArea([.bottom])
        
    }
}

struct Dashlet : Identifiable {
    
    var id = UUID().uuidString
    var title : String
    var value : String
    var color : Color
    
}

var averageData = [
    Dashlet(title: "High Score", value: "279", color: Color.red),
    Dashlet(title: "Most Used Ball", value: "-", color: Color.orange),
    Dashlet(title: "High Series", value: "790", color: Color.yellow),
    Dashlet(title: "Practice Average", value: "220", color: Color.green),
    Dashlet(title: "League Average", value: "210", color: Color.blue),
    Dashlet(title: "Tournament Average", value: "195", color: Color.purple)
]


struct MetricsView : View {
    
    var dashlet : Dashlet
    
    var body: some View {
        
        ZStack {
            
            dashlet.color
            
            HStack(alignment: .center) {
                
                VStack(alignment: .center, spacing: 8) {
                    
                    
                    Text(dashlet.title)
                        .foregroundColor(.white)
                        .frame(alignment: .center)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Spacer()
                    
                    Text(dashlet.value)
                        .font(.system(size: 30))
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                }
                
            }.padding()
            
        }.cornerRadius(10)
        
    }
    
}

struct HomeView_Previews: PreviewProvider {
    static var previews: some View {
        Group {
            HomeView()
        }
    }
}
