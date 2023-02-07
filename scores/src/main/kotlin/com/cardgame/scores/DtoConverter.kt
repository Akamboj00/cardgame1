package com.cardgame.scores

import com.cardgame.scores.db.UserStats
import com.cardgame.scores.dto.UserStatsDto

object DtoConverter {

    fun transform(stats: UserStats) : UserStatsDto =
        stats.run { UserStatsDto(userId, victories, defeats, draws, score) }

    fun transform(scores: Iterable<UserStats>) : List<UserStatsDto> = scores.map { transform(it) }
}