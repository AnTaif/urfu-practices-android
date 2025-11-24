package github.antaif.urfupractices.leaderboard.presentation.viewModel

import androidx.lifecycle.ViewModel
import github.antaif.urfupractices.leaderboard.data.repository.FavoriteDriverRepository
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack

class FavoritesViewModel(
    private val topLevelBackStack: TopLevelBackStack<Route>,
    favoriteDriverRepository: FavoriteDriverRepository
) : ViewModel() {
    
    val favorites = favoriteDriverRepository.getAllFavorites()
    
    fun onDriverClick(driver: LeaderboardDriverUiModel) {
        topLevelBackStack.add(Routes.LeaderboardDriverDetails(driver))
    }
}

