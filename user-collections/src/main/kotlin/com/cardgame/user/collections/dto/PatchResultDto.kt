package com.cardgame.user.collections.dto

import io.swagger.annotations.ApiModelProperty


class PatchResultDto(

    @get:ApiModelProperty("If a card pack was opened, specify which cards were in it")
    var cardIdsInOpenedPack: MutableList<String> = mutableListOf()
)