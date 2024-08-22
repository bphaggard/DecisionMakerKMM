package com.example.decisionkmm.data.decision

import com.example.decisionkmm.domain.decision.Decision

fun database.Decision.toDecision(): Decision {
    return Decision(
        id = id,
        title = title
    )
}