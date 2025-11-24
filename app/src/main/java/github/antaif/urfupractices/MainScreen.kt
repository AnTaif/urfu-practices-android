package github.antaif.urfupractices

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import github.antaif.urfupractices.leaderboard.presentation.screen.FavoritesScreen
import github.antaif.urfupractices.leaderboard.presentation.screen.LeaderboardDriverDetailsScreen
import github.antaif.urfupractices.leaderboard.presentation.screen.LeaderboardFilterScreen
import github.antaif.urfupractices.leaderboard.presentation.screen.LeaderboardScreen
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import org.koin.java.KoinJavaComponent.inject

@Composable
fun MainScreen() {
    val topLevelBackStack by inject<TopLevelBackStack<Route>>(clazz = TopLevelBackStack::class.java)

    Scaffold(
        bottomBar = {
            NavigationBar {
                Routes.navigationBarRoutes.forEach { route ->
                    NavigationBarItem(
                        icon = { Icon(route.icon, null) },
                        selected = topLevelBackStack.topLevelKey == route,
                        onClick = {
                            topLevelBackStack.addTopLevel(route)
                        }
                    )
                }
            }
        }) { padding ->
        NavDisplay(
            backStack = topLevelBackStack.backStack,
            onBack = { topLevelBackStack.removeLast() },
            modifier = Modifier.padding(padding),
            entryDecorators = listOf(
                rememberSaveableStateHolderNavEntryDecorator(), // В последней версии (1.0.0-alpha11) переименовали rememberSavedStateNavEntryDecorator()
                rememberViewModelStoreNavEntryDecorator(),
            ),
            entryProvider = entryProvider {
                entry<Routes.Leaderboard> {
                    LeaderboardScreen()
                }
                entry<Routes.LeaderboardDriverDetails> {
                    LeaderboardDriverDetailsScreen(it.driver)
                }
                entry<Routes.Filter> {
                    LeaderboardFilterScreen()
                }
                entry<Routes.Favorites> {
                    FavoritesScreen()
                }
                entry<Routes.Results> {
                    CenteredTextScreen("Лента результатов")
                }
                entry<Routes.RaceSchedule> {
                    CenteredTextScreen("Расписание гонок")
                }
            }
        )
    }
}

@Composable
private fun CenteredTextScreen(text: String) {
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.headlineMedium
        )
    }
}