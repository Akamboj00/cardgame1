package com.cardgame.db

import jakarta.persistence.*
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank


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