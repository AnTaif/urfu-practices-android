package github.antaif.urfupractices.leaderboard.data.mapper

import github.antaif.urfupractices.leaderboard.data.model.GetDriversChampionshipResponse
import github.antaif.urfupractices.leaderboard.domain.model.LeaderboardDriverEntity
import github.antaif.urfupractices.leaderboard.domain.model.LeaderboardEntity

class LeaderboardMapper {
    fun mapResponse(response: GetDriversChampionshipResponse): LeaderboardEntity {
        return LeaderboardEntity(
            season = response.season.toString(),
            championshipId = response.championshipId,
            driversLeaderboard = response.driversChampionship.map { dc ->
                LeaderboardDriverEntity(
                    position = dc.position,
                    driverName = dc.driver.name,
                    driverShortName = dc.driver.shortName,
                    teamName = dc.team.teamName,
                    points = dc.points,
                    wins = dc.wins,
                    teamCountry = dc.team.country,
                    driverNationality = dc.driver.nationality
                )
            }
        )
    }
}