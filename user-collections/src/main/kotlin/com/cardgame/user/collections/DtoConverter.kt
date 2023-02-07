package com.cardgame.user.collections

import com.cardgame.user.collections.db.CardCopy
import com.cardgame.user.collections.db.User
import com.cardgame.user.collections.dto.CardCopyDto
import com.cardgame.user.collections.dto.UserDto

object DtoConverter {


    fun transform(user: User) : UserDto {

        return UserDto().apply {
            userId = user.userId
            coins = user.coins
            cardPacks = user.cardPacks
            ownedCards = user.ownedCards.map { transform(it) }.toMutableList()
        }
    }

    fun transform(cardCopy: CardCopy) : CardCopyDto {
        return CardCopyDto().apply {
            cardId = cardCopy.cardId
            numberOfCopies = cardCopy.numberOfCopies
        }
    }
}
