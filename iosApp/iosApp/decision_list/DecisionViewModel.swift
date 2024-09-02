//
//  DecisionViewModel.swift
//  iosApp
//
//  Created by Patrik Mlcoch on 27.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

extension DecisionListScreen {
    @MainActor class DecisionViewModel: ObservableObject {
        private var decisionDataSource: DecisionDataSource?
        
        private var decisionId: Int64? = nil
        @Published var decisionTitle = ""
        @Published var decisionValue: String = ""
        
        private var decisions = [Decision]()
        @Published private(set) var filteredDecisions = [Decision]()
        
        init(decisionDataSource: DecisionDataSource? = nil) {
            self.decisionDataSource = decisionDataSource
            loadDecisions() // Load decisions on initialization
        }
        
        func saveDecision() {
            guard !decisionTitle.isEmpty else { return } // Ensure title is not empty
            
            let decision = Decision(id: decisionId?.asKotlinLong(), title: decisionValue)
            decisionDataSource?.insertDecision(decision: decision, completionHandler: { [weak self] error in
                if let error = error {
                    print("Failed to save decision: \(error.localizedDescription)")
                } else {
                    self?.decisionTitle = "" // Clear the title
                    self?.loadDecisions() // Reload the list after saving
                }
            })
        }
        
        func loadDecisions() {
            decisionDataSource?.getAllDecisions(completionHandler: { [weak self] decisions, error in
                if let error = error {
                    print("Failed to load decisions: \(error.localizedDescription)")
                } else {
                    self?.decisions = decisions ?? []
                    self?.filteredDecisions = self?.decisions ?? []
                }
            })
        }
        
        func deleteDecisionById(id: Int64?) {
            
            decisionDataSource?.deleteDecisionById(id: id!, completionHandler: { [weak self] error in
                if let error = error {
                    print("Failed to delete decision: \(error.localizedDescription)")
                } else {
                    self?.loadDecisions()
                }
            })
        }
        
        func deleteAllDecisions() {
            decisionDataSource?.deleteAllDecisions(completionHandler: { [weak self] error in
                if let error = error {
                    print("Failed to delete all decisions: \(error.localizedDescription)")
                } else {
                    self?.loadDecisions() // Reload after deletion
                }
            })
        }
        
        func getRandomDecision() {
            decisionDataSource?.getRandomDecision(completionHandler: { [weak self] decisionTitle, error in
                            if let error = error {
                                print("Failed to get random decision: \(error.localizedDescription)")
                            } else {
                                self?.decisionTitle = decisionTitle ?? "No decision selected"
                            }
                        })
        }
        
        func setDecisionDataSource(decisionDataSource: DecisionDataSource) {
            self.decisionDataSource = decisionDataSource
        }
    }
}

// Helper extension to convert Int64 to KotlinLong
extension Int64 {
    func asKotlinLong() -> KotlinLong {
        return KotlinLong(value: self)
    }
}
