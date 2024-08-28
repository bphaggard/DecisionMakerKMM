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
    
    init(decisionDataSource: DecisionDataSource) {
        self.decisionDataSource = decisionDataSource
    }
    
    var body: some View {
        VStack {
            ZStack {
                Text("Easy Decision")
                    .font(.title2)
                    .frame(maxWidth: /*@START_MENU_TOKEN@*/.infinity/*@END_MENU_TOKEN@*/, minHeight: 40)
                    .padding()
            }
            
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
        .onAppear {
            viewModel.setDecisionDataSource(decisionDataSource: decisionDataSource)
        }
    }
}
