//
//  LikesView.swift
//  PetInformationApp
//
//  Created by Regular Kim on 4/8/26.
//

import SwiftUI

struct LikesView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10){
            Text("좋아하는 것")
                .font(.system(size: 20))
                .bold()
            Divider()
            Text("- 각종 고기")
            Text("- 장본게 담겨 있는 종량제 봉투")
            Text("- 집에 새로 온 사람")
        }
        .padding()
        .background(RoundedRectangle(cornerRadius: 15)
            .fill(Color.mint)
            .opacity(0.3)
            .shadow(radius: 5))
    }
}

#Preview {
    LikesView()
}
