package github.antaif.urfupractices.leaderboard.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import github.antaif.urfupractices.leaderboard.presentation.LeaderboardMockData
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardDriverDetailsState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardDriverDetailsViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun LeaderboardDriverDetailsScreen(
    driver: LeaderboardDriverUiModel
) {
    val viewModel = koinViewModel<LeaderboardDriverDetailsViewModel> {
        parametersOf(driver)
    }
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeaderboardDriverDetailsContent(
        state,
        onBack = { viewModel.onBack() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeaderboardDriverDetailsContent(
    state: LeaderboardDriverDetailsState,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Driver Details") },
                navigationIcon = {
                    IconButton(
                        onClick = { onBack() }
                    )
                    {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LeaderboardDriverDetails(
            driver = state.driver,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun LeaderboardDriverDetails(
    driver: LeaderboardDriverUiModel,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = driver.driverName,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
        )

        Text(
            text = "(${driver.driverShortName})",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Team: ${driver.teamName}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Country: ${driver.teamCountry}",
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = "Nationality: ${driver.driverNationality}",
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatItem(label = "Position", value = driver.position.toString())
            StatItem(label = "Points", value = driver.points.toString())
            StatItem(label = "Wins", value = driver.wins.toString())
        }
    }
}

@Composable
private fun StatItem(label: String, value: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = value,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold),
        )
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.secondary
        )
    }
}

@Preview
@Composable
private fun LeaderboardDriverDetailsScreenPreview() {
    LeaderboardDriverDetailsContent(
        LeaderboardDriverDetailsState(LeaderboardMockData.leaderboard.driversLeaderboard.first()),
        onBack = {}
    )
}