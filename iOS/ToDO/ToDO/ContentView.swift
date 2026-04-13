//
//  ContentView.swift
//  ToDO
//
//  Created by Regular Kim on 4/13/26.
//

import SwiftUI

struct ContentView: View {
    
    @State // class에는 @Observable 추가해줘야함!
    var todoList: [Todo] = []
    
    func deleteTodo(indexSet: IndexSet) {
        withAnimation{
            for index in indexSet {
                todoList.remove(at: index)
            }
        }
    }
    
    var body: some View {
        
        NavigationStack {
            
            List {
                
                ForEach(todoList) { todo in
                    HStack {
                        
                        Image(systemName: todo.isCompleted ? "circle.fill" : "circle")
                            .foregroundStyle(Color.pink)
                            .onTapGesture {
                                todo.isCompleted.toggle()
                            }
                        NavigationLink {
                            TodoDetailView(todo: todo)
                        } label: {
                            Text(todo.title)
                                .strikethrough(todo.isCompleted, color: Color.gray)
                                .foregroundStyle(todo.isCompleted ? .gray : .primary)
                        }
                    }
                    .listRowSeparator(.hidden)
                }
                .onDelete(perform: deleteTodo)
            }
            .listStyle(.plain)
            .navigationTitle(Text("ToDo ✅"))
            .toolbar {
                ToolbarItem {
                    EditButton()
                }
                ToolbarItem {
                    Button(action: {
                        withAnimation{
                            let newTodo = Todo(title: "새로운 Todo")
                            todoList.append(newTodo)
                        }
                    }, label: {
                        Image(systemName: "plus")
                    })
                }
            }
        }
    }
}

#Preview {
    ContentView()
}
