package com.example.cryptotrackerapp.models

data class MarketModelItem(
    val id: String,
    val is_active: Boolean,
    val is_new: Boolean,
    val name: String,
    val rank: Long,
    val symbol: String,
    val type: String
)