package com.example.decisionkmm

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform