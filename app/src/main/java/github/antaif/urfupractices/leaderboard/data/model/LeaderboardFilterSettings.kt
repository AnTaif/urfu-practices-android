package github.antaif.urfupractices.leaderboard.data.model

data class LeaderboardFilterSettings(
    val driverNameQuery: String = "",
    val teamNameQuery: String = "",
    val minPoints: Int? = null,
)

