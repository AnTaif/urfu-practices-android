package github.antaif.urfupractices.leaderboard.domain.interactor

import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardRepository

class LeaderboardInteractor(
    private val leaderboardRepository: LeaderboardRepository
) {
    suspend fun getCurrentLeaderboard() = leaderboardRepository.getCurrentLeaderboard()
}