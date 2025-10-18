package github.antaif.urfupractices.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Star
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel

data object Routes {
    val navigationBarRoutes = listOf(Leaderboard, Results, RaceSchedule)

    data object Leaderboard : TopLevelRoute {
        override val icon = Icons.Default.Star
    }

    data class LeaderboardDriverDetails(val driver: LeaderboardDriverUiModel) : Route

    data object Results : TopLevelRoute {
        override val icon = Icons.Default.Notifications
    }

    data object RaceSchedule : TopLevelRoute {
        override val icon = Icons.Default.DateRange
    }
}