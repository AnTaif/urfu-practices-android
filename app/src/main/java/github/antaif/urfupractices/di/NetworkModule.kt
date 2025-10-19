package github.antaif.urfupractices.di

import de.jensklingenberg.ktorfit.Ktorfit
import github.antaif.urfupractices.leaderboard.data.api.createF1Api
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

const val f1ApiUrl = "https://f1api.dev/api/"

val networkModule = module {

    single {
        Ktorfit.Builder()
            .baseUrl(f1ApiUrl)
            .httpClient(HttpClient {
                install(ContentNegotiation) {
                    json(Json { isLenient = true; ignoreUnknownKeys = true })
                }
            })
            .build()
    }

    single { get<Ktorfit>().createF1Api() }
}