package github.antaif.urfupractices.di

import github.antaif.urfupractices.leaderboard.data.mapper.LeaderboardMapper
import github.antaif.urfupractices.leaderboard.data.repository.LeaderboardRepository
import github.antaif.urfupractices.leaderboard.domain.interactor.LeaderboardInteractor
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardDriverDetailsViewModel
import github.antaif.urfupractices.leaderboard.presentation.viewModel.LeaderboardViewModel
import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Routes.Leaderboard) }

    factory { LeaderboardMapper() }
    single { LeaderboardRepository(get(), get()) }

    single { LeaderboardInteractor(get()) }

    viewModel { LeaderboardViewModel(get(), get()) }
    viewModel { LeaderboardDriverDetailsViewModel(get(), get()) }
}