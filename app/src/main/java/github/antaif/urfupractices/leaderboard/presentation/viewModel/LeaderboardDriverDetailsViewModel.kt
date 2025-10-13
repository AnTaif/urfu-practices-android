package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardDriverDetailsState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LeaderboardDriverDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val driver: LeaderboardDriverUiModel,
) : ViewModel() {
    private val mutableState = MutableStateFlow(LeaderboardDriverDetailsState(driver))
    val state = mutableState.asStateFlow()

    fun onBack() {
        topLevelBackStack.removeLast()
    }
}