package github.antaif.urfupractices

import android.app.Application
import github.antaif.urfupractices.di.mainModule
import github.antaif.urfupractices.di.networkModule
import github.antaif.urfupractices.leaderboard.di.leaderboardModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(mainModule, networkModule, leaderboardModule)
        }
    }
}