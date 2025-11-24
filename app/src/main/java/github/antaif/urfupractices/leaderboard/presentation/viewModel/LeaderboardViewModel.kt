package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.antaif.urfupractices.core.launchLoadingAndError
import github.antaif.urfupractices.leaderboard.data.cache.FilterBadgeCache
import github.antaif.urfupractices.leaderboard.data.model.LeaderboardFilterSettings
import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardFilterPreferencesRepository
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
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update

class LeaderboardViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val interactor: LeaderboardInteractor,
    private val leaderboardFilterPreferencesRepository: LeaderboardFilterPreferencesRepository,
    private val filterBadgeCache: FilterBadgeCache
) : ViewModel() {
    private val mutableState = MutableStateFlow(LeaderboardState())
    val state = mutableState.asStateFlow()
    val hasActiveFilters = filterBadgeCache.hasActiveFilters
    private var currentFilterSettings: LeaderboardFilterSettings? = null
    private var isFirstLoad = true

    init {
        observeFilterSettings()
    }

    private fun observeFilterSettings() {
        leaderboardFilterPreferencesRepository.filterSettings
            .onEach { settings ->
                val filtersChanged = currentFilterSettings != settings
                currentFilterSettings = settings
                filterBadgeCache.updateFilterState(settings)
                if (filtersChanged || isFirstLoad) {
                    isFirstLoad = false
                    loadCurrentLeaderboard()
                }
            }
            .launchIn(viewModelScope)
    }

    fun onFilterClick() {
        topLevelBackStack.add(Routes.Filter)
    }

    fun onDriverClick(driver: LeaderboardDriverUiModel) {
        topLevelBackStack.add(Routes.LeaderboardDriverDetails(driver))
    }

    fun onRetryClick() = loadCurrentLeaderboard()

    private fun loadCurrentLeaderboard() {
        viewModelScope.launchLoadingAndError(
            handleError = { e -> updateState(LeaderboardState.State.Error(e.localizedMessage)) }
        ) {
            updateState(LeaderboardState.State.Loading)

            val leaderboard = interactor.getCurrentLeaderboard()
            val filteredLeaderboard = applyFilters(mapToUi(leaderboard))
            updateState(LeaderboardState.State.Success(filteredLeaderboard))
        }
    }

    private fun applyFilters(leaderboard: LeaderboardUiModel): LeaderboardUiModel {
        val settings = currentFilterSettings ?: return leaderboard
        var filteredDrivers = leaderboard.driversLeaderboard

        if (settings.teamNameQuery.isNotEmpty()) {
            filteredDrivers = filteredDrivers.filter {
                it.teamName.contains(settings.teamNameQuery, ignoreCase = true)
            }
        }

        if (settings.driverNameQuery.isNotEmpty()) {
            filteredDrivers = filteredDrivers.filter {
                it.driverName.contains(settings.driverNameQuery, ignoreCase = true)
            }
        }

        settings.minPoints?.let { minPoints ->
            filteredDrivers = filteredDrivers.filter { it.points >= minPoints }
        }

        return leaderboard.copy(driversLeaderboard = filteredDrivers)
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