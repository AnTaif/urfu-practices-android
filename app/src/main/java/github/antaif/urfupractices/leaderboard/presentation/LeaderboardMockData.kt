package github.antaif.urfupractices.leaderboard.presentation

import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardUiModel

// Структура данных отсюда https://f1api.dev/ru/docs/standings/current-drivers-championship
object LeaderboardMockData {

    private val driversLeaderboard = listOf(
        LeaderboardDriverUiModel(
            position = 1,
            driverName = "Oscar Piastri",
            driverShortName = "PIA",
            teamName = "McLaren Formula 1 Team",
            points = 336,
            wins = 7,
            teamCountry = "Great Britain",
            driverNationality = "Australia"
        ),
        LeaderboardDriverUiModel(
            position = 2,
            driverName = "Lando Norris",
            driverShortName = "NOR",
            teamName = "McLaren Formula 1 Team",
            points = 314,
            wins = 5,
            teamCountry = "Great Britain",
            driverNationality = "Great Britain"
        ),
        LeaderboardDriverUiModel(
            position = 3,
            driverName = "Max Verstappen",
            driverShortName = "VER",
            teamName = "Red Bull Racing",
            points = 273,
            wins = 4,
            teamCountry = "Austria",
            driverNationality = "Netherlands"
        ),
        LeaderboardDriverUiModel(
            position = 4,
            driverName = "George Russell",
            driverShortName = "RUS",
            teamName = "Mercedes Formula 1 Team",
            points = 237,
            wins = 2,
            teamCountry = "Germany",
            driverNationality = "Great Britain"
        ),
        LeaderboardDriverUiModel(
            position = 5,
            driverName = "Charles Leclerc",
            driverShortName = "LEC",
            teamName = "Scuderia Ferrari",
            points = 173,
            wins = 0,
            teamCountry = "Italy",
            driverNationality = "Monaco"
        ),
        LeaderboardDriverUiModel(
            position = 6,
            driverName = "Lewis Hamilton",
            driverShortName = "HAM",
            teamName = "Scuderia Ferrari",
            points = 127,
            wins = 0,
            teamCountry = "Italy",
            driverNationality = "Great Britain"
        ),
        LeaderboardDriverUiModel(
            position = 7,
            driverName = "Andrea Kimi Antonelli",
            driverShortName = "ANT",
            teamName = "Mercedes Formula 1 Team",
            points = 88,
            wins = 0,
            teamCountry = "Germany",
            driverNationality = "Italy"
        ),
        LeaderboardDriverUiModel(
            position = 8,
            driverName = "Alex Albon",
            driverShortName = "ALB",
            teamName = "Williams Racing",
            points = 70,
            wins = 0,
            teamCountry = "Great Britain",
            driverNationality = "Thailand"
        ),
        LeaderboardDriverUiModel(
            position = 9,
            driverName = "Isack Hadjar",
            driverShortName = "HAD",
            teamName = "RB F1 Team",
            points = 39,
            wins = 0,
            teamCountry = "Italy",
            driverNationality = "France"
        ),
        LeaderboardDriverUiModel(
            position = 10,
            driverName = "Nico Hulkenberg",
            driverShortName = "HUL",
            teamName = "Sauber F1 Team",
            points = 37,
            wins = 0,
            teamCountry = "Switzerland",
            driverNationality = "Germany"
        ),
        LeaderboardDriverUiModel(
            position = 11,
            driverName = "Fernando Alonso",
            driverShortName = "ALO",
            teamName = "Aston Martin F1 Team",
            points = 34,
            wins = 0,
            teamCountry = "Great Britain",
            driverNationality = "Spain"
        ),
        LeaderboardDriverUiModel(
            position = 12,
            driverName = "Carlos Sainz",
            driverShortName = "SAI",
            teamName = "Williams Racing",
            points = 32,
            wins = 0,
            teamCountry = "Great Britain",
            driverNationality = "Spain"
        ),
        LeaderboardDriverUiModel(
            position = 13,
            driverName = "Lance Stroll",
            driverShortName = "STR",
            teamName = "Aston Martin F1 Team",
            points = 32,
            wins = 0,
            teamCountry = "Great Britain",
            driverNationality = "Canada"
        ),
        LeaderboardDriverUiModel(
            position = 14,
            driverName = "Liam Lawson",
            driverShortName = "LAW",
            teamName = "RB F1 Team",
            points = 30,
            wins = 0,
            teamCountry = "Italy",
            driverNationality = "New Zealand"
        ),
        LeaderboardDriverUiModel(
            position = 15,
            driverName = "Esteban Ocon",
            driverShortName = "OCO",
            teamName = "Haas F1 Team",
            points = 28,
            wins = 0,
            teamCountry = "United States",
            driverNationality = "France"
        ),
        LeaderboardDriverUiModel(
            position = 16,
            driverName = "Pierre Gasly",
            driverShortName = "GAS",
            teamName = "Alpine F1 Team",
            points = 20,
            wins = 0,
            teamCountry = "France",
            driverNationality = "France"
        ),
        LeaderboardDriverUiModel(
            position = 17,
            driverName = "Yuki Tsunoda",
            driverShortName = "TSU",
            teamName = "Red Bull Racing",
            points = 20,
            wins = 0,
            teamCountry = "Austria",
            driverNationality = "Japan"
        ),
        LeaderboardDriverUiModel(
            position = 18,
            driverName = "Gabriel Bortoleto",
            driverShortName = "BOR",
            teamName = "Sauber F1 Team",
            points = 18,
            wins = 0,
            teamCountry = "Switzerland",
            driverNationality = "Brazil"
        ),
        LeaderboardDriverUiModel(
            position = 19,
            driverName = "Oliver Bearman",
            driverShortName = "BEA",
            teamName = "Haas F1 Team",
            points = 18,
            wins = 0,
            teamCountry = "United States",
            driverNationality = "Great Britain"
        ),
        LeaderboardDriverUiModel(
            position = 20,
            driverName = "Franco Colapinto",
            driverShortName = "COL",
            teamName = "Alpine F1 Team",
            points = 0,
            wins = 0,
            teamCountry = "France",
            driverNationality = "Argentina"
        ),
        LeaderboardDriverUiModel(
            position = 21,
            driverName = "Jack Doohan",
            driverShortName = "DOO",
            teamName = "Alpine F1 Team",
            points = 0,
            wins = 0,
            teamCountry = "France",
            driverNationality = "Australia"
        )
    )

    val leaderboard = LeaderboardUiModel(
        season = "2025",
        championshipId = "f1_2025",
        driversLeaderboard = driversLeaderboard,
    )
}