//
//  DecisionViewModel.swift
//  iosApp
//
//  Created by Patrik Mlcoch on 27.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor // Ensure all methods run on the main thread
class DecisionViewModel: ObservableObject {
    private var decisionDataSource: DecisionDataSource?
    
    private var decisionId: Int64? = nil
    @Published var decisionValue = "" // Updated to ensure SwiftUI binding works
    @Published private(set) var filteredDecisions = [Decision]()
    @Published var decisionTitle = "" // Updated for the result display
    
    init(decisionDataSource: DecisionDataSource? = nil) {
        self.decisionDataSource = decisionDataSource
        loadDecisions()
    }
    
    func saveDecision() {
        guard !decisionValue.isEmpty else { return }
        
        let decision = Decision(id: decisionId?.asKotlinLong(), title: decisionValue)
        decisionDataSource?.insertDecision(decision: decision) { [weak self] error in
            guard let self = self else { return }
            if let error = error {
                print("Failed to save decision: \(error.localizedDescription)")
            } else {
                DispatchQueue.main.async {
                    self.decisionValue = ""
                    self.loadDecisions()
                }
            }
        }
    }
    
    func loadDecisions() {
        decisionDataSource?.getAllDecisions { [weak self] decisions, error in
            guard let self = self else { return }
            DispatchQueue.main.async {
                self.filteredDecisions = decisions ?? []
            }
        }
    }
    
    func deleteDecisionById(id: Int64?) {
        guard let id = id else { return }
        decisionDataSource?.deleteDecisionById(id: id) { [weak self] error in
            DispatchQueue.main.async {
                if let error = error {
                    print("Failed to delete decision: \(error.localizedDescription)")
                } else {
                    print("Successfully deleted decision with ID: \(id)")
                    self?.loadDecisions() // Reload decisions after deletion
                }
            }
        }
    }
    
    func deleteAllDecisions() {
        decisionDataSource?.deleteAllDecisions { [weak self] error in
            guard let self = self else { return }
            DispatchQueue.main.async {
                self.loadDecisions()
                self.decisionTitle = ""
            }
        }
    }
    
    func getRandomDecision() {
        decisionDataSource?.getRandomDecision { [weak self] decisionTitle, error in
            guard let self = self else { return }
            DispatchQueue.main.async {
                if let error = error {
                    print("Failed to get random decision: \(error.localizedDescription)")
                } else {
                    self.decisionTitle = decisionTitle ?? "No decision selected"
                }
            }
        }
    }
    
    func setDecisionDataSource(decisionDataSource: DecisionDataSource) {
        self.decisionDataSource = decisionDataSource
        loadDecisions()
    }
}


// Helper extension to convert Int64 to KotlinLong
extension Int64 {
    func asKotlinLong() -> KotlinLong {
        return KotlinLong(value: self)
    }
}
