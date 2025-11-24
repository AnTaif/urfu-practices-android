package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.antaif.urfupractices.leaderboard.data.model.LeaderboardFilterSettings
import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardFilterPreferencesRepository
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LeaderboardFilterViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val leaderboardFilterPreferencesRepository: LeaderboardFilterPreferencesRepository
) : ViewModel() {
    
    private val mutableState = MutableStateFlow(LeaderboardFilterSettings())
    val state = mutableState.asStateFlow()
    
    init {
        loadFilterSettings()
    }
    
    private fun loadFilterSettings() {
        viewModelScope.launch {
            leaderboardFilterPreferencesRepository.filterSettings.first().let {
                mutableState.value = it
            }
        }
    }

    fun updateTeamNameQuery(query: String) {
        mutableState.value = mutableState.value.copy(teamNameQuery = query)
    }
    
    fun updateDriverNameQuery(query: String) {
        mutableState.value = mutableState.value.copy(driverNameQuery = query)
    }

    fun updateMinPoints(minPoints: Int?) {
        mutableState.value = mutableState.value.copy(minPoints = minPoints)
    }

    suspend fun onSaveClick() {
        saveFilters()
        topLevelBackStack.removeLast()
    }
    
    suspend fun saveFilters() {
        leaderboardFilterPreferencesRepository.saveFilterSettings(mutableState.value)
    }
}

