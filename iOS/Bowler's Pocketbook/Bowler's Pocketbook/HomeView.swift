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
                    
                }.padding([.leading, .trailing], 20)
                
                HStack(spacing: 15) {
                    
                    MetricsView(dashlet: averageData[2])
                    MetricsView(dashlet: averageData[3])
                    
                }.padding([.leading, .trailing], 20)
                
                HStack(spacing: 15) {
                    
                    MetricsView(dashlet: averageData[4])
                    MetricsView(dashlet: averageData[5])
                    
                }.padding([.leading, .trailing], 20)
                
            }.padding(10)
            
            ZStack {
                Color.black.ignoresSafeArea().cornerRadius(30)
                Color.white.ignoresSafeArea().cornerRadius(30).padding(1)
                
                VStack {
                    Text("No data to display").foregroundColor(.black)
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
    Dashlet(title: "High Score", value: "-", color: Color.red),
    Dashlet(title: "Most Used Ball", value: "-", color: Color.orange),
    Dashlet(title: "High Series", value: "-", color: Color.yellow),
    Dashlet(title: "Practice Average", value: "-", color: Color.green),
    Dashlet(title: "League Average", value: "-", color: Color.blue),
    Dashlet(title: "Tournament Average", value: "-", color: Color.purple)
]


struct MetricsView : View {
    
    var dashlet : Dashlet
    
    var body: some View {
        
        let gradient = Gradient(colors: [dashlet.color, Color.white])
//        let gradient = Gradient(colors: [dashlet.color, Color.white, dashlet.color])
        
        ZStack {
            
            dashlet.color
//            LinearGradient(gradient: gradient, startPoint: .bottomLeading, endPoint: .topTrailing)
            
            HStack(alignment: .center) {
                
                VStack(alignment: .leading, spacing: 8) {
                    
                    
                    Text(dashlet.title)
                        .font(.system(size: 12))
                        .foregroundColor(.white)
                        .frame(alignment: .leading)
                        .fixedSize(horizontal: false, vertical: true)
                    
                    Spacer()
                    
                    Text(dashlet.value)
                        .font(.system(size: 30))
                        .fontWeight(.bold)
                        .foregroundColor(.white)
                    
                }
                
                Spacer()
                
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
