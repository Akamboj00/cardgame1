package com.cardgame.user.collections.model

import com.cardgame.cards.dto.CardDto
import com.cardgame.cards.dto.Rarity

data class Card(
    val cardId : String,
    val rarity: Rarity
){

    constructor(dto: CardDto): this(
        dto.cardId ?: throw IllegalArgumentException("Null cardId"),
        dto.rarity ?: throw IllegalArgumentException("Null rarity"))
}