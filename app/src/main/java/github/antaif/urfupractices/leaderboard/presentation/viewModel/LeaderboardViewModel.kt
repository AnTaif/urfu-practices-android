package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import github.antaif.urfupractices.leaderboard.presentation.LeaderboardMockData
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LeaderboardViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
) : ViewModel() {
    private val mutableState = MutableStateFlow(LeaderboardState(LeaderboardMockData.leaderboard))
    val state = mutableState.asStateFlow()

    fun onDriverClick(driver: LeaderboardDriverUiModel) {
        topLevelBackStack.add(Routes.LeaderboardDriverDetails(driver))
    }
}