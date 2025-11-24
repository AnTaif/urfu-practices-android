package github.antaif.urfupractices.leaderboard.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import github.antaif.urfupractices.R
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardFilterViewModel
import github.antaif.urfupractices.uikit.Spacing
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LeaderboardFilterScreen() {
    val viewModel = koinViewModel<LeaderboardFilterViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        TopAppBar(
            title = { Text(stringResource(R.string.filter_settings)) }
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Spacing.medium)
        ) {
            OutlinedTextField(
                value = state.teamNameQuery,
                onValueChange = { value ->
                    viewModel.updateTeamNameQuery(value)
                },
                label = { Text(stringResource(R.string.team_name)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_team_name)) }
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextField(
                value = state.driverNameQuery,
                onValueChange = { value ->
                    viewModel.updateDriverNameQuery(value)
                },
                label = { Text(stringResource(R.string.driver_name)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text(stringResource(R.string.enter_driver_name)) }
            )

            Spacer(modifier = Modifier.height(Spacing.medium))

            OutlinedTextField(
                value = state.minPoints?.toString() ?: "",
                onValueChange = { value ->
                    viewModel.updateMinPoints(value.toIntOrNull())
                },
                label = { Text(stringResource(R.string.min_points)) },
                modifier = Modifier.fillMaxWidth(),
                placeholder = { Text("0") }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    coroutineScope.launch {
                        viewModel.onSaveClick()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.done))
            }
        }
    }
}

