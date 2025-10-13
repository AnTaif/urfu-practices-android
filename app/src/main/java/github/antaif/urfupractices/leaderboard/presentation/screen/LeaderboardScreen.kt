package github.antaif.urfupractices.leaderboard.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
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
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardDriverUiModel
import github.antaif.urfupractices.leaderboard.presentation.model.ui.LeaderboardUiModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun LeaderboardScreen() {

    val viewModel = koinViewModel<LeaderboardViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    LeaderboardContent(
        state.leaderboard,
        onDriverClick = { viewModel.onDriverClick(it) }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun LeaderboardContent(
    leaderboard: LeaderboardUiModel,
    onDriverClick: (LeaderboardDriverUiModel) -> Unit,
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = "Лидерборд сезона"
                        )

                        Column {
                            Text(
                                text = leaderboard.season,
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                    }
                }
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .background(MaterialTheme.colorScheme.background)
        ) {
            leaderboard.driversLeaderboard.forEach {
                item(key = it.position) {
                    LeaderboardRow(it) {
                        onDriverClick(it)
                    }
                }
            }
        }
    }
}

@Composable
private fun LeaderboardRow(driver: LeaderboardDriverUiModel, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable { onClick() },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${driver.position}",
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            modifier = Modifier.width(32.dp)
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(
                text = driver.driverName,
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold)
            )
            Text(
                text = driver.teamName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Column(
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "${driver.points} pts",
                style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold)
            )
            Text(
                text = "${driver.wins} wins",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Preview
@Composable
private fun SeasonLeaderboardScreenPreview() {
    LeaderboardContent(LeaderboardMockData.leaderboard, onDriverClick = {})
}
