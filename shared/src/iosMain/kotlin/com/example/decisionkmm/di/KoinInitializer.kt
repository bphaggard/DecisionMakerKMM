package com.example.decisionkmm.di

import com.example.decisionkmm.data.decision.DecisionViewModel
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

fun initKoin() {

    val modules = sharedKoinModules

    startKoin {
        modules(modules)
    }

    class DecisionsInjector: KoinComponent {

        val decisionsViewModel: DecisionViewModel by inject()
    }
}