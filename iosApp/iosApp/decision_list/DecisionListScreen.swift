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
    @StateObject var viewModel: DecisionViewModel
    
    @State private var decisionValue: String = ""
    let randomDecision = "Your Decision"
    
    init(decisionDataSource: DecisionDataSource) {
        self.decisionDataSource = decisionDataSource
        _viewModel = StateObject(wrappedValue: DecisionViewModel(decisionDataSource: decisionDataSource))
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
                            text: $viewModel.decisionValue
                        )
                        .padding(.horizontal, 20)
                        .textInputAutocapitalization(.never)
                        .autocorrectionDisabled(true)
                        .textFieldStyle(RoundedBorderTextFieldStyle())
                        
                        Button("Add Decision", systemImage: "plus", action: { viewModel.saveDecision() })
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
                            ForEach(viewModel.filteredDecisions, id: \.id) { decision in
                                DecisionItem(decision: decision, onDeleteClick: {
                                    viewModel.deleteDecisionById(id: decision.id?.int64Value)
                                })
                                .buttonStyle(PlainButtonStyle())
                                .listRowBackground(Color(hex: "E1E4D5"))
                            }
                        }
                        .onAppear {
                            viewModel.loadDecisions()
                        }
                        .listStyle(.plain)
                        .listRowSeparator(.hidden)
                    }
                        .padding(5)
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
                            Color(hex: "#B0BFA1")
                                .frame(maxWidth: .infinity, maxHeight: .infinity)
                                .cornerRadius(15)
                                    
                            Text("RESULT :")
                                .foregroundColor(.black)
                                .padding()
                        }
                        .frame(width: UIScreen.main.bounds.width * 0.27)

                        // Right side text
                        Text(viewModel.decisionTitle)
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
                Button(action: {viewModel.deleteAllDecisions()}
                ) {
                    Text("Clear List")
                        .foregroundColor(.black)
                        .padding()
                        .background(Color(hex: "#B0BFA1"))
                        .cornerRadius(10)
                }
                Spacer()
                Button(action: {viewModel.getRandomDecision()}
                ) {
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
