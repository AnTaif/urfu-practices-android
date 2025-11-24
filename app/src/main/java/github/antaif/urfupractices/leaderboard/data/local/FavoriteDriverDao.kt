package github.antaif.urfupractices.leaderboard.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDriverDao {
    @Query("SELECT * FROM favorite_drivers ORDER BY position ASC")
    fun getAllFavorites(): Flow<List<FavoriteDriverEntity>>
    
    @Query("SELECT * FROM favorite_drivers WHERE driverShortName = :shortName")
    suspend fun getFavoriteByShortName(shortName: String): FavoriteDriverEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: FavoriteDriverEntity)
    
    @Query("DELETE FROM favorite_drivers WHERE driverShortName = :shortName")
    suspend fun deleteFavoriteByShortName(shortName: String)
    
    @Query("SELECT EXISTS(SELECT 1 FROM favorite_drivers WHERE driverShortName = :shortName)")
    suspend fun isFavorite(shortName: String): Boolean
}

