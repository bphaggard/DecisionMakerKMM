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

    private val _newDecision = MutableStateFlow("")
    val newDecision: StateFlow<String> get() = _newDecision

    private val _randomDecision = MutableStateFlow("")
    val randomDecision: StateFlow<String> get() = _randomDecision

    fun loadDecisions() {
        scope.launch {
            _decisions.value = decisionDataSource.getAllDecisions()
        }
    }

    fun onNewDecisionChange(newText: String) {
        _newDecision.value = newText
    }

    fun addDecision() {
        scope.launch {
            val decisionTitle = _newDecision.value
            if (decisionTitle.isNotEmpty()) {
                decisionDataSource.insertDecision(Decision(id = null, title = decisionTitle))
                _newDecision.value = ""
                loadDecisions()
            }
        }
    }

    fun deleteDecisionById(id: Long) {
        scope.launch {
            decisionDataSource.deleteDecisionById(id)
        }
    }

    fun getRandomDecision() {
        scope.launch {
            val decision = decisionDataSource.getRandomDecision()
            _randomDecision.value = decision ?: "No decision selected"
        }
    }

    fun deleteAllDecisions() {
        scope.launch {
            decisionDataSource.deleteAllDecisions()
        }
    }
}