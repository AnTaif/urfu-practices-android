package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.antaif.urfupractices.leaderboard.domain.interactor.LeaderboardInteractor
import github.antaif.urfupractices.leaderboard.domain.model.LeaderboardEntity
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardUiModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LeaderboardViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: LeaderboardInteractor
) : ViewModel() {
    private val mutableState = MutableStateFlow(LeaderboardState())
    val state = mutableState.asStateFlow()

    init {
        loadCurrentLeaderboard()
    }

    fun onDriverClick(driver: LeaderboardDriverUiModel) {
        topLevelBackStack.add(Routes.LeaderboardDriverDetails(driver))
    }

    private fun loadCurrentLeaderboard() {
        viewModelScope.launch {
            updateState(LeaderboardState.State.Loading)

            val leaderboard = interactor.getCurrentLeaderboard()
            updateState(LeaderboardState.State.Success(mapToUi(leaderboard)))
        }
    }
    
    private fun updateState(state: LeaderboardState.State) =
        mutableState.update { it.copy(state = state) }

    private fun mapToUi(entity: LeaderboardEntity): LeaderboardUiModel {
        return LeaderboardUiModel(
            season = entity.season,
            championshipId = entity.championshipId,
            driversLeaderboard = entity.driversLeaderboard.map {
                LeaderboardDriverUiModel(
                    position = it.position,
                    driverName = it.driverName,
                    driverShortName = it.driverShortName,
                    teamName = it.teamName,
                    points = it.points,
                    wins = it.wins,
                    teamCountry = it.teamCountry,
                    driverNationality = it.driverNationality
                )
            }
        )
    }
}