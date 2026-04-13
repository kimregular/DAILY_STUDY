//
//  ContentView.swift
//  NetflixThumbnail
//
//  Created by Regular Kim on 4/13/26.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        
        TabView {
            HomeView()
                .tabItem {
                    Image(systemName: "house.fill")
                    Text("Home")
                }
            Text("탭2")
                .tabItem {
                    Image(systemName: "gamecontroller")
                    Text("Game")
                }
            Text("탭3")
                .tabItem {
                    Image(systemName: "play.rectangle.on.rectangle")
                    Text("New & Hot")
                }
            Text("탭4")
                .tabItem {
                    Image(systemName: "person.crop.circle")
                    Text("나의 넷플릭스")
                }
        }
    }
}

#Preview {
    ContentView()
}
