package github.antaif.urfupractices.leaderboard.data.model

import androidx.annotation.Keep
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class GetDriversChampionshipResponse(
    val api: String,
    val championshipId: String,
    @SerialName("drivers_championship") val driversChampionship: List<DriversChampionshipDto>,
    val limit: Int,
    val season: Int,
    val total: Int,
    val url: String
)

@Keep
@Serializable
data class DriversChampionshipDto(
    val classificationId: Int,
    val driver: DriverDto,
    val driverId: String,
    val points: Int,
    val position: Int,
    val team: TeamDto,
    val teamId: String,
    val wins: Int
)

@Keep
@Serializable
data class DriverDto(
    val birthday: String,
    val name: String,
    val nationality: String,
    val number: Int,
    val shortName: String,
    val surname: String,
    val url: String
)

@Keep
@Serializable
data class TeamDto(
    val constructorsChampionships: Int?,
    val country: String,
    val driversChampionships: Int?,
    @Suppress("SpellCheckingInspection") @SerialName("firstAppareance") val firstAppearance: Int,
    val teamId: String,
    val teamName: String,
    val url: String
)