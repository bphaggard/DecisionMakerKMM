package com.example.decisionkmm.data.decision

import com.example.decisionkmm.BaseViewModel
import com.example.decisionkmm.domain.decision.Decision
import com.example.decisionkmm.domain.decision.DecisionDataSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DecisionViewModel(
    private val decisionDataSource: DecisionDataSource
): BaseViewModel() {
    private val _decisions = MutableStateFlow<List<Decision>>(emptyList()) //mutable
    val decisions: StateFlow<List<Decision>> get() = _decisions.asStateFlow() //immutable

    init {
        loadDecisions()
    }

    private fun loadDecisions() {
        scope.launch {
            _decisions.value = decisionDataSource.getAllDecisions()
        }
    }

    fun addDecision(decision: Decision) {
        scope.launch {
            decisionDataSource.insertDecision(decision)
        }
    }

    fun deleteDecisionById(id: Long) {
        scope.launch {
            decisionDataSource.deleteDecisionById(id)
        }
    }

    fun getRandomDecision() {
        scope.launch {

        }
    }

    fun deleteAllDecisions() {
        scope.launch {
            decisionDataSource.deleteAllDecisions()
        }
    }
}