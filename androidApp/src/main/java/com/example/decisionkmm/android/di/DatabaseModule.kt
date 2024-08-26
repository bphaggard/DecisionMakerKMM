package com.example.decisionkmm.android.di

import app.cash.sqldelight.db.SqlDriver
import com.example.decisionkmm.data.local.DatabaseDriverFactory
import com.example.decisionkmm.database.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {

    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<AppDatabase> { AppDatabase(get()) }
}