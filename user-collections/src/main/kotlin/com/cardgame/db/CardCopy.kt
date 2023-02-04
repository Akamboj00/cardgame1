package com.cardgame.db

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull

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