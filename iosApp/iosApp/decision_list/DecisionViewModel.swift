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
        private var decisionDataSource: DecisionDataSource? = nil
        
        private var decisions = [Decision]()
        @Published private(set) var filteredDecisions = [Decision]()
        
        init(decisionDataSource: DecisionDataSource? = nil) {
            self.decisionDataSource = decisionDataSource
        }
        
        func loadDecisions() {
            decisionDataSource?.getAllDecisions(completionHandler: { decisions, error in
                self.decisions = decisions ?? []
                self.filteredDecisions = self.decisions
            })
        }
        
        func deleteDecisionById(id: Int64?) {
            if id != nil {
                decisionDataSource?.deleteDecisionById(id: id!, completionHandler: { error in
                    self.loadDecisions()
                })
            }
        }
        
        func setDecisionDataSource(decisionDataSource: DecisionDataSource) {
            self.decisionDataSource = decisionDataSource
        }
    }
}
