package com.example.decisionkmm.android.decision_list

import com.example.decisionkmm.domain.decision.Decision

data class DecisionListState(
    val decisions: List<Decision> = emptyList()
)
