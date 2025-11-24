package github.antaif.urfupractices.leaderboard.di

import github.antaif.urfupractices.leaderboard.data.mapper.LeaderboardMapper
import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardFilterPreferencesRepository
import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardRepository
import github.antaif.urfupractices.leaderboard.domain.interactor.LeaderboardInteractor
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardDriverDetailsViewModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardFilterViewModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val leaderboardModule = module {

    factory { LeaderboardMapper() }
    single { LeaderboardRepository(get(), get()) }
    single { LeaderboardFilterPreferencesRepository(androidContext()) }

    single { LeaderboardInteractor(get()) }

    viewModel { LeaderboardViewModel(get(), get(), get()) }
    viewModel { LeaderboardDriverDetailsViewModel(get(), get()) }
    viewModel { LeaderboardFilterViewModel(get(), get()) }
}