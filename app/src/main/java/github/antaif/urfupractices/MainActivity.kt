package github.antaif.urfupractices

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import github.antaif.urfupractices.ui.theme.UrfuPracticesTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrfuPracticesTheme {
                MainScreen()
            }
        }
    }
}