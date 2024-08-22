package com.example.decisionkmm.data.decision

import com.example.decisionkmm.database.AppDatabase
import com.example.decisionkmm.domain.decision.Decision
import com.example.decisionkmm.domain.decision.DecisionDataSource

class SqlDelightDecisionDataSource(db: AppDatabase): DecisionDataSource {
    private val queries = db.appDatabaseQueries

    override suspend fun insertDecision(decision: Decision) {
        queries.insertDecision(
            id = decision.id,
            title = decision.title
        )
    }

    override suspend fun getDecisionById(id: Long): Decision? {
        return queries
            .getDecisionById(id)
            .executeAsOneOrNull()
            ?.toDecision()
    }

    override suspend fun getAllDecisions(): List<Decision> {
        return queries
            .getAllDecisions()
            .executeAsList()
            .map { it.toDecision() }
    }

    override suspend fun deleteDecisionById(id: Long) {
        queries.deleteDecisionById(id)
    }

    override suspend fun getRandomDecision(): String? {
        return queries
            .getRandomDecision()
            .executeAsOneOrNull()
    }

    override suspend fun deleteAllDecisions() {
        queries.deleteAllDecisions()
    }
}