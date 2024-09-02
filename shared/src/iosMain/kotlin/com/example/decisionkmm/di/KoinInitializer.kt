package com.example.decisionkmm.di

import com.example.decisionkmm.data.decision.DecisionViewModel
import com.example.decisionkmm.data.decision.SqlDelightDecisionDataSourceImpl
import com.example.decisionkmm.data.local.DatabaseDriverFactory
import com.example.decisionkmm.database.AppDatabase
import com.example.decisionkmm.domain.decision.DecisionDataSource
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.mp.KoinPlatform.getKoin

fun initKoin() {

    val modules = sharedKoinModules + databaseModule

    startKoin {
        modules(modules)
    }
}

class DecisionsInjector: KoinComponent {

    val decisionsViewModel: DecisionViewModel by inject()
}

class DatabaseModule {

    private val factory by lazy { DatabaseDriverFactory() }
    val decisionDataSource: DecisionDataSource by lazy {
        SqlDelightDecisionDataSourceImpl(AppDatabase(factory.createDriver()))
    }
}