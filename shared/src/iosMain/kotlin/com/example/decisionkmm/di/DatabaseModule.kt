package com.example.decisionkmm.di

import app.cash.sqldelight.db.SqlDriver
import com.example.decisionkmm.data.local.DatabaseDriverFactory
import com.example.decisionkmm.database.AppDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }

    single { AppDatabase(get()) }
}