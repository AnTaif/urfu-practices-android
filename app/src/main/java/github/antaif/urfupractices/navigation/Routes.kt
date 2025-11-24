package github.antaif.urfupractices.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.ui.theme.icons.Trophy

data object Routes {
    val navigationBarRoutes = listOf(Leaderboard, Favorites, Results, RaceSchedule)

    data object Leaderboard : TopLevelRoute {
        override val icon = Trophy
    }

    data class LeaderboardDriverDetails(val driver: LeaderboardDriverUiModel) : Route

    data object Filter : Route

    data object Favorites : TopLevelRoute {
        override val icon = Icons.Default.FavoriteBorder
    }

    data object Results : TopLevelRoute {
        override val icon = Icons.Outlined.Notifications
    }

    data object RaceSchedule : TopLevelRoute {
        override val icon = Icons.Default.DateRange
    }
}