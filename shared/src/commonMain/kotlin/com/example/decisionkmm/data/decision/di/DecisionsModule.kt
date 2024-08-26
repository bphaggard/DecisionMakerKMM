package com.example.decisionkmm.data.decision.di

import com.example.decisionkmm.data.decision.DecisionViewModel
import com.example.decisionkmm.data.decision.SqlDelightDecisionDataSourceImpl
import com.example.decisionkmm.domain.decision.DecisionDataSource
import org.koin.dsl.module

val decisionModule = module {

    single<DecisionDataSource> { SqlDelightDecisionDataSourceImpl(get()) }
    single<DecisionViewModel> { DecisionViewModel(get()) } //only for iOS
}