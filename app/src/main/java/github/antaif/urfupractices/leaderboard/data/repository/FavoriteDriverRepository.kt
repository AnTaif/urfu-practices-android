package github.antaif.urfupractices.leaderboard.data.repository

import github.antaif.urfupractices.leaderboard.data.local.FavoriteDriverDao
import github.antaif.urfupractices.leaderboard.data.local.FavoriteDriverEntity
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FavoriteDriverRepository(
    private val favoriteDriverDao: FavoriteDriverDao
) {
    fun getAllFavorites(): Flow<List<LeaderboardDriverUiModel>> {
        return favoriteDriverDao.getAllFavorites().map { entities ->
            entities.map { it.toUiModel() }
        }
    }
    
    fun getFavoriteShortNames(): Flow<Set<String>> {
        return favoriteDriverDao.getAllFavorites().map { entities ->
            entities.map { it.driverShortName }.toSet()
        }
    }
    
    suspend fun isFavorite(driverShortName: String): Boolean {
        return favoriteDriverDao.isFavorite(driverShortName)
    }
    
    suspend fun toggleFavorite(driver: LeaderboardDriverUiModel) {
        if (isFavorite(driver.driverShortName)) {
            removeFromFavorites(driver.driverShortName)
        } else {
            addToFavorites(driver)
        }
    }
    
    suspend fun addToFavorites(driver: LeaderboardDriverUiModel) {
        favoriteDriverDao.insertFavorite(driver.toEntity())
    }
    
    suspend fun removeFromFavorites(driverShortName: String) {
        favoriteDriverDao.deleteFavoriteByShortName(driverShortName)
    }
    
    private fun FavoriteDriverEntity.toUiModel(): LeaderboardDriverUiModel {
        return LeaderboardDriverUiModel(
            position = position,
            driverName = driverName,
            driverShortName = driverShortName,
            teamName = teamName,
            points = points,
            wins = wins,
            teamCountry = teamCountry,
            driverNationality = driverNationality
        )
    }
    
    private fun LeaderboardDriverUiModel.toEntity(): FavoriteDriverEntity {
        return FavoriteDriverEntity(
            driverShortName = driverShortName,
            position = position,
            driverName = driverName,
            teamName = teamName,
            points = points,
            wins = wins,
            teamCountry = teamCountry,
            driverNationality = driverNationality
        )
    }
}

