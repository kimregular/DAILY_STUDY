//
//  ContentView.swift
//  ToDO
//
//  Created by Regular Kim on 4/13/26.
//

import SwiftUI
import SwiftData

struct ContentView: View {
    
    @Environment(\.modelContext)
    private var modelContext
    
    // @State // class에는 @Observable 추가해줘야함!
    @Query
    var todoList: [Todo] = []
    
    func addTodo() {
        withAnimation {
            let newTodo = Todo(title: "new todo")
            // todoList.append(newTodo)
            modelContext.insert(newTodo)
        }
    }
    
    func deleteTodo(indexSet: IndexSet) {
        withAnimation{
            for index in indexSet {
                //                todoList.remove(at: index)
                let todo = todoList[index]
                modelContext.delete(todo)
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
                    Button(action: addTodo,
                           label: {
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
