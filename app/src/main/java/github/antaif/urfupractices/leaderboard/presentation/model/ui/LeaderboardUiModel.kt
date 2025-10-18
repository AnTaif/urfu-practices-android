package github.antaif.urfupractices.leaderboard.presentation.model.ui

import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardUiModel(
    val season: String,
    val championshipId: String,
    val driversLeaderboard: List<LeaderboardDriverUiModel>,
)