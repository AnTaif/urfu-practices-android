package github.antaif.urfupractices.leaderboard.data.repository

import github.antaif.urfupractices.leaderboard.data.api.F1Api
import github.antaif.urfupractices.leaderboard.data.mapper.LeaderboardMapper
import github.antaif.urfupractices.leaderboard.domain.model.LeaderboardEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LeaderboardRepository(
    private val api: F1Api,
    private val mapper: LeaderboardMapper
) {
    suspend fun getCurrentLeaderboard(): LeaderboardEntity = withContext(Dispatchers.IO) {
        val response = api.getCurrentDriversChampionship()
        mapper.mapResponse(response)
    }
}