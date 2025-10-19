package github.antaif.urfupractices.leaderboard.domain.model

data class LeaderboardEntity(
    val season: String,
    val championshipId: String,
    val driversLeaderboard: List<LeaderboardDriverEntity>,
)