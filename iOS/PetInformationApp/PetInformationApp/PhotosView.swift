//
//  PhotosView.swift
//  PetInformationApp
//
//  Created by Regular Kim on 4/8/26.
//

import SwiftUI

struct PhotosView: View {
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            // `showsIndicators`: 스크롤시 bar 표시 옵션
            HStack{
                DogImageView(imageName: "dog1", strokeColor: Color.green)
                DogImageView(imageName: "dog2", strokeColor: Color.blue)
                DogImageView(imageName: "dog3", strokeColor: Color.red)
            }
            .padding()
            .background(
                RoundedRectangle(cornerRadius: 15)
                    .fill(Color.yellow)
                    .opacity(0.2)
                    .shadow(radius: 5)
            )
        }
    }
}

struct DogImageView: View {
    
    var imageName: String
    var strokeColor: Color
    
    var body: some View {
        Image(imageName)
            .resizable()
            .frame(width: 160, height: 160)
            .overlay(
                Rectangle()
                    .stroke(strokeColor, lineWidth: 3)
            )
    }
}

#Preview {
    PhotosView()
}
