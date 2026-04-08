//
//  ContentView.swift
//  PetInformationApp
//
//  Created by Regular Kim on 4/8/26.
//

import SwiftUI

struct ContentView: View {
    var body: some View {
        
        VStack {
            ProfileView()
            LikesView()
            SkillView()
            PhotosView()
        }
        .padding()
    }
}

#Preview {
    ContentView()
}
