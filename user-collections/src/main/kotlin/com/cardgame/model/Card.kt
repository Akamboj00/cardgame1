package com.cardgame.model

import com.cardgame.CardDto
import com.cardgame.Rarity

data class Card(
    val cardId : String,
    val rarity: Rarity
){

    constructor(dto: CardDto): this(
        dto.cardId ?: throw IllegalArgumentException("Null cardId"),
        dto.rarity ?: throw IllegalArgumentException("Null rarity"))
}