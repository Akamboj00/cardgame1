package com.cardgame.db

import jakarta.persistence.*
import jakarta.validation.constraints.*

@Entity

class CardCopy(

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id : Long? = null,

    @ManyToOne
    @NotNull
    var user : User? = null,

    @NotBlank
    var cardId: String? = null,

    @Min(0)
    var numberOfCopies : Int = 0
)