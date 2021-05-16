//
//  FindABallView.swift
//  Bowler's Pocketbook v2
//
//  Created by Jeffrey Preston on 5/16/21.
//

import SwiftUI

struct FindABallView: View {
    var body: some View {

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
        }

    }
}

struct FindABallView_Previews: PreviewProvider {
    static var previews: some View {
        FindABallView()
    }
}
