package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import github.antaif.urfupractices.leaderboard.data.repository.FavoriteDriverRepository
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardDriverDetailsState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.TopLevelBackStack
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class LeaderboardDriverDetailsViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    private val driver: LeaderboardDriverUiModel,
    private val favoriteDriverRepository: FavoriteDriverRepository
) : ViewModel() {
    private val mutableState = MutableStateFlow(LeaderboardDriverDetailsState(driver))
    val state = mutableState.asStateFlow()
    
    val isFavorite = favoriteDriverRepository.getFavoriteShortNames().map { 
        it.contains(driver.driverShortName) 
    }

    fun onBack() {
        topLevelBackStack.removeLast()
    }
    
    fun onFavoriteClick() {
        viewModelScope.launch {
            favoriteDriverRepository.toggleFavorite(driver)
        }
    }
}