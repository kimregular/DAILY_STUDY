//
//  ProfileView.swift
//  PetInformationApp
//
//  Created by Regular Kim on 4/8/26.
//

import SwiftUI

struct ProfileView: View {
    var body: some View {
        HStack{
            Image("dogProfile")
                .resizable()
            // 이미지는 기본적으로 사이즈 조절이 불가능하다
            // `.resizable()` 메서드로 사이즈 조절을 명시해야한다
                .frame(width: 120, height: 120)
                .clipShape(Circle())
            //.border(Color.black, width: 5) // .border() 메서드는 항상 사각형의 테두리를 그린다.
                .overlay(Circle().stroke(Color.teal, lineWidth: 3))
            // .overlay() -> 같은 shape 위에 덧그림 가능
            
            VStack(alignment: .leading){
                Text("멍멍이")
                Text("강아지")
            }
            .font(.system(size: 20))
            .padding(.leading) // leading 부분에만 패딩 추가하기! 기본은 상하좌우 모두 패딩
        }
        .padding(.bottom, 20)
    }
}

#Preview {
    ProfileView()
}
