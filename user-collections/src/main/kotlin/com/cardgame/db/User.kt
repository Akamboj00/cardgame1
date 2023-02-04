package com.cardgame.db

import javax.persistence.*
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank


@Entity
@Table(name="user_data")
class User(


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotBlank
    var userId: String? = null,

    @Min(0)
    var coins: Int = 0,

    @Min(0)
    var cardPacks: Int = 0,

    @OneToMany(mappedBy = "user", cascade = [(CascadeType.ALL)])
    var ownedCards : MutableList<CardCopy> = mutableListOf()
)