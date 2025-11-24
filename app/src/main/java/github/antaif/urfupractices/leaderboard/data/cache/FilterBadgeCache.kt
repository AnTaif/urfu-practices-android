package github.antaif.urfupractices.leaderboard.data.cache

import github.antaif.urfupractices.leaderboard.data.model.LeaderboardFilterSettings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class FilterBadgeCache {
    private val _hasActiveFilters = MutableStateFlow(false)
    val hasActiveFilters: StateFlow<Boolean> = _hasActiveFilters.asStateFlow()
    
    fun updateFilterState(settings: LeaderboardFilterSettings) {
        val hasFilters = settings.driverNameQuery.isNotEmpty() ||
                settings.teamNameQuery.isNotEmpty() ||
                settings.minPoints != null
        _hasActiveFilters.value = hasFilters
    }
}

