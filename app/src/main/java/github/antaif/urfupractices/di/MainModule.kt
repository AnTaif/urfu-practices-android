package github.antaif.urfupractices.di

import github.antaif.urfupractices.navigation.Route
import github.antaif.urfupractices.navigation.Routes
import github.antaif.urfupractices.navigation.TopLevelBackStack
import org.koin.dsl.module

val mainModule = module {
    single { TopLevelBackStack<Route>(Routes.Leaderboard) }
}