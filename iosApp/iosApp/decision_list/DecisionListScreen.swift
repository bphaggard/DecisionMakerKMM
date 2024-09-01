//
//  DecisionListScreen.swift
//  iosApp
//
//  Created by Patrik Mlcoch on 27.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import shared

struct DecisionListScreen: View {
    private var decisionDataSource: DecisionDataSource
    @StateObject var viewModel = DecisionViewModel(decisionDataSource: nil)
    let randomDecision = "Your Decision"
    
    @State private var decisionValue: String = ""
    
    init(decisionDataSource: DecisionDataSource) {
        self.decisionDataSource = decisionDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                AppBar()
            }
            //Textfiled and Add Button
            RoundedRectangle(cornerRadius: 15)
                .fill(Color(hex: "#E1E4D5"))
                .frame(
                    width: UIScreen.main.bounds.width * 0.9,
                    height: UIScreen.main.bounds.height * 0.1
                )
                .overlay(
                    HStack {
                        TextField(
                            "Enter Decision",
                            text: $decisionValue
                        )
                        .padding(.horizontal, 20)
                        .textInputAutocapitalization(.never)
                        .autocorrectionDisabled(true)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        
                        Button("Add Decision", systemImage: "plus", action: {})
                            .padding(.horizontal, 20)
                            .labelStyle(.iconOnly)
                            .foregroundColor(.black)

                    }
                )
            Spacer(minLength: 10)
            //Values Card
            RoundedRectangle(cornerRadius: 15)
                .fill(Color(hex: "#E1E4D5"))
                .frame(
                    width: UIScreen.main.bounds.width * 0.9,
                    height: UIScreen.main.bounds.height * 0.5
                )
                .overlay(
                    VStack {
                        List {
                            ForEach(viewModel.filteredDecisions, id: \.self.id) { decision in
                                Button(action: {}) {
                                    DecisionItem(decision: decision, onDeleteClick: {
                                        viewModel.deleteDecisionById(id: decision.id?.int64Value)
                                    })
                                }
                            }
                        }
                        .onAppear {
                            viewModel.loadDecisions()
                        }
                        .listStyle(.plain)
                        .listRowSeparator(.hidden)
                    }
                        .padding()
                )
            Spacer(minLength: 10)
            // Result Row
            RoundedRectangle(cornerRadius: 15)
                .fill(Color(hex: "#E1E4D5"))
                .frame(
                    width: UIScreen.main.bounds.width * 0.9,
                    height: UIScreen.main.bounds.height * 0.1
                )
                .overlay(
                    HStack {
                        ZStack {
                            Color(hex: "#B0BFA1") // Use a custom hex color
                                .frame(maxWidth: .infinity, maxHeight: .infinity)
                                .cornerRadius(15) // Round only left corners
                                    
                            Text("RESULT :")
                                .foregroundColor(.black)
                                .padding()
                        }
                        .frame(width: UIScreen.main.bounds.width * 0.27) // 30% width of the card

                        // Right side text
                            Text(randomDecision)
                                .font(.system(size: 20))
                                .fontWeight(.bold)
                                .padding(.horizontal, 15)
                                .foregroundColor(.black)
                        Spacer()
                    }
                )
            Spacer(minLength: 10)
            // Clear and Choose Button
            HStack {
                Button(action: {
                    
                }) {
                    Text("Clear List")
                        .foregroundColor(.black)
                        .padding()
                        .background(Color(hex: "#B0BFA1"))
                        .cornerRadius(10)
                }
                Spacer()
                Button(action: {
                
                }) {
                    Text("Choose")
                        .foregroundColor(.black)
                        .padding()
                        .background(Color(hex: "#B0BFA1"))
                        .cornerRadius(10)
                }
            }
            .frame(width: UIScreen.main.bounds.width * 0.9)
            .padding(.horizontal)
        }
        .onAppear {
            viewModel.setDecisionDataSource(decisionDataSource: decisionDataSource)
        }
        .background(Color(hex: "#F9FAEF"))
    }
}

struct AppBar: View {
    var body: some View {
        Text("Easy Decision")
            .font(.title2)
            .fontWeight(/*@START_MENU_TOKEN@*/.bold/*@END_MENU_TOKEN@*/)
    }
}
