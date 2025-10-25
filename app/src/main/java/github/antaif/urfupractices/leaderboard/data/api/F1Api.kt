package github.antaif.urfupractices.leaderboard.data.api

import de.jensklingenberg.ktorfit.http.GET
import github.antaif.urfupractices.leaderboard.data.model.GetDriversChampionshipResponse

interface F1Api {
    @GET("current/drivers-championship")
    suspend fun getCurrentDriversChampionship(): GetDriversChampionshipResponse
}