package github.antaif.urfupractices.leaderboard.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import github.antaif.urfupractices.R
import github.antaif.urfupractices.leaderboard.presentation.LeaderboardMockData
import github.antaif.urfupractices.leaderboard.presentation.model.state.LeaderboardDriverDetailsState
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardDriverDetailsViewModel
import github.antaif.urfupractices.uikit.BackButton
import github.antaif.urfupractices.uikit.HeightSpacer
import github.antaif.urfupractices.uikit.Spacing
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
                title = { Text(text = stringResource(R.string.driver_details)) },
                navigationIcon = { BackButton(onBack) }
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
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(Spacing.medium)
            .padding(top = Spacing.medium)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = driver.driverName,
            style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.ExtraBold)
        )

        Text(
            text = "(%s)".format(driver.driverShortName),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.secondary
        )

        HeightSpacer(Spacing.medium)

        Text(
            text = stringResource(R.string.team, driver.teamName),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = stringResource(R.string.country, driver.teamCountry),
            style = MaterialTheme.typography.bodyLarge
        )

        Text(
            text = stringResource(R.string.nationality, driver.driverNationality),
            style = MaterialTheme.typography.bodyLarge
        )

        HeightSpacer(Spacing.large)

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            StatItem(label = stringResource(R.string.position), value = driver.position.toString())
            StatItem(label = stringResource(R.string.points), value = driver.points.toString())
            StatItem(label = stringResource(R.string.wins), value = driver.wins.toString())
        }
    }
}

@Composable
private fun StatItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
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