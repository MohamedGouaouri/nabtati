package com.example.learn.nabtati.domain.model

data class Tip(
    val tip: String
) {
    operator fun not(): Boolean {
        return tip.isBlank()
    }
}