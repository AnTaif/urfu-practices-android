package github.antaif.urfupractices.leaderboard.presentation.model.state

import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardUiModel

data class LeaderboardState(
    val state: State = State.Loading,
) {
    sealed interface State {
        object Loading : State
        data class Error(val error: String) : State
        data class Success(val data: LeaderboardUiModel) : State
    }
}