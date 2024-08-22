package com.example.decisionkmm.domain.decision

interface DecisionDataSource { //similar like Dao object in Room db
    suspend fun insertDecision(decision: Decision)
    suspend fun getDecisionById(id: Long): Decision?
    suspend fun getAllDecisions(): List<Decision>
    suspend fun deleteDecisionById(id: Long)
    suspend fun getRandomDecision(): String?
    suspend fun deleteAllDecisions()
}