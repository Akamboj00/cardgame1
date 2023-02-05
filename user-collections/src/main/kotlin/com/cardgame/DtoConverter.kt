package com.cardgame

import com.cardgame.db.CardCopy
import com.cardgame.db.User
import com.cardgame.dto.CardCopyDto
import com.cardgame.dto.UserDto

object DtoConverter {


    fun transform(user: User) : UserDto{

        return UserDto().apply {
            userId = user.userId
            coins = user.coins
            cardPacks = user.cardPacks
            ownedCards = user.ownedCards.map { transform(it) }.toMutableList()
        }
    }

    fun transform(cardCopy: CardCopy) : CardCopyDto{
        return CardCopyDto().apply {
            cardId = cardCopy.cardId
            numberOfCopies = cardCopy.numberOfCopies
        }
    }
}
