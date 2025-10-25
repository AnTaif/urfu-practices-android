package github.antaif.urfupractices.leaderboard.domain.model

data class LeaderboardDriverEntity (
    val position: Int,
    val driverName: String,
    val driverShortName: String,
    val teamName: String,
    val points: Int,
    val wins: Int,
    val teamCountry: String,
    val driverNationality: String
)