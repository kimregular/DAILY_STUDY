//
//  ContentView.swift
//  wordRepay
//
//  Created by Regular Kim on 4/9/26.
//

import SwiftUI

struct ContentView: View {
    
    let title = "끝말잇기 게임"
    
    @State
    var nextWord: String = ""
    // View가 소유하는 상태 값.
    // 값이 바뀌면 화면도 다시 그려진다
    
    @State
    var words: [String] = ["Apple", "Elsa"]
    
    @State
    var showAlert: Bool = false
    
    var body: some View {
        
        VStack {
            Text(title)
                .font(.title)
                .bold(true)
                .padding(.vertical, 16)
                .padding(.horizontal, 20)
            //            .padding(.top, 16)
            //            .padding(.bottom, 16)
            //            .padding(.leading, 20)
            //            .padding(.trailing, 20)
                .background(
                    RoundedRectangle(cornerRadius: 15)
                        .fill(Color.teal)
                        .opacity(0.2)
                        .shadow(radius: 5)
                    
                )
                .padding(.top, 10)
            
            HStack {
                TextField("단어를 입력하세요", text: $nextWord)
                // $ -> 값을 바꿀 수 있게 연결해주는 표시 (바인딩)
                // nextWord와 직접 연결됨 -> 읽기, 쓰기 활성화
                // 입력창(TextField)이 nextWord를 읽고 수정할 수 있게 해준다
                    .padding()
                    .background(
                        RoundedRectangle(cornerRadius: 10)
                            .stroke(lineWidth: 2)
                    )
                
                Button(action: {
                    // 동작
                    
                    
                    if words.isEmpty || words.last?.last?.lowercased() == nextWord.first?.lowercased() {
                        words.append(nextWord)
                    } else {
                        showAlert = true
                    }
                    nextWord = ""
                }, label: {
                    // 뷰
                    Text("제출")
                        .foregroundStyle(Color.white)
                        .padding(.horizontal)
                        .padding(.vertical, 16)
                        .background(RoundedRectangle(cornerRadius: 10))
                })
                .disabled(nextWord.trimmingCharacters(in: .whitespaces).isEmpty)
                .alert("끝말이 이어지는 단어를 입력해주세요", isPresented: $showAlert) {
                    Button("확인", role: .cancel) {
                        showAlert = false
                    }
                }
                
                
            }
            .padding(.horizontal)
            .padding(.top)
            
            List {
                ForEach(words.reversed(), id: \.self) { word in
                    Text(word)
                        .font(.title2)
                }
                .onDelete { indexSet in
                    words.remove(atOffsets: indexSet)
                }
            }
            .listStyle(.plain)
            
            Spacer()
        }
        
        
    }
}

#Preview {
    ContentView()
}
