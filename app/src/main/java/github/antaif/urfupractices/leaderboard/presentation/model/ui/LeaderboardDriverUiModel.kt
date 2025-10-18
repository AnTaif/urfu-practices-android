package github.antaif.urfupractices.leaderboard.presentation.model.ui

import kotlinx.serialization.Serializable

@Serializable
data class LeaderboardDriverUiModel(
    val position: Int,
    val driverName: String,
    val driverShortName: String,
    val teamName: String,
    val points: Int,
    val wins: Int,
    val teamCountry: String,
    val driverNationality: String
)