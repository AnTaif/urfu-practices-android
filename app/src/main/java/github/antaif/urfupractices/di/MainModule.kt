package github.antaif.urfupractices.di

import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardDriverDetailsViewModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardViewModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Routes.Leaderboard) }

    viewModel { LeaderboardViewModel(get()) }
    viewModel { LeaderboardDriverDetailsViewModel(get(), get()) }
}