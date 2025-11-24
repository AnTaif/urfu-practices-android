package github.antaif.urfupractices.leaderboard.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_drivers")
data class FavoriteDriverEntity(
    @PrimaryKey
    val driverShortName: String,
    val position: Int,
    val driverName: String,
    val teamName: String,
    val points: Int,
    val wins: Int,
    val teamCountry: String,
    val driverNationality: String
)

