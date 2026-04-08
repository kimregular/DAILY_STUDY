//
//  SkillView.swift
//  PetInformationApp
//
//  Created by Regular Kim on 4/8/26.
//

import SwiftUI

struct SkillView: View {
    var body: some View {
        VStack(alignment: .leading, spacing: 10){
            Text("스킬")
                .font(.system(size: 20))
                .bold()
            Divider()
            HStack(alignment: .top, spacing: 30) {
                VStack(alignment: .leading, spacing: 10){
                    Text("- 앉아")
                    Text("- 엎드려(앉아와 자주 혼동함)")
                    Text("- 하우스(집으로 들어가기)")
                }
                VStack(alignment: .trailing, spacing: 10){
                    Text("- 손")
                    Text("- 코")
                }
            }
        }
        .padding()
        .background(RoundedRectangle(cornerRadius: 15)
            .fill(Color.pink)
            .opacity(0.3)
            .shadow(radius: 5))
    }
}

#Preview {
    SkillView()
}
