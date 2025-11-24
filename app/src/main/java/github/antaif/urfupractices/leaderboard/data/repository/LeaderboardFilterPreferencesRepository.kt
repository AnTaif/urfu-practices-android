package github.antaif.urfupractices.leaderboard.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import github.antaif.urfupractices.leaderboard.data.model.LeaderboardFilterSettings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "filter_settings")

class LeaderboardFilterPreferencesRepository(private val context: Context) {

    private val driverNameQueryKey = stringPreferencesKey("driver_name_query")
    private val teamNameQueryKey = stringPreferencesKey("team_name_query")
    private val minPointsKey = intPreferencesKey("min_points")

    val filterSettings: Flow<LeaderboardFilterSettings> =
        context.dataStore.data.map { preferences ->
            LeaderboardFilterSettings(
                driverNameQuery = preferences[driverNameQueryKey] ?: "",
                teamNameQuery = preferences[teamNameQueryKey] ?: "",
                minPoints = preferences[minPointsKey],
            )
        }

    suspend fun saveFilterSettings(settings: LeaderboardFilterSettings) {
        context.dataStore.edit { preferences ->
            if (settings.driverNameQuery.isNotEmpty()) {
                preferences[driverNameQueryKey] = settings.driverNameQuery
            } else {
                preferences.remove(driverNameQueryKey)
            }

            if (settings.teamNameQuery.isNotEmpty()) {
                preferences[teamNameQueryKey] = settings.teamNameQuery
            } else {
                preferences.remove(teamNameQueryKey)
            }

            if (settings.minPoints != null) {
                preferences[minPointsKey] = settings.minPoints
            } else {
                preferences.remove(minPointsKey)
            }
        }
    }
}

