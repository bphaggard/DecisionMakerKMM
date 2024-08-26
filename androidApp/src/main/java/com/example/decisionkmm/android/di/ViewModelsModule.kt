package com.example.decisionkmm.android.di

import com.example.decisionkmm.data.decision.DecisionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelsModule = module {

    viewModel { DecisionViewModel(get()) }
}