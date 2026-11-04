package ai.opencode.mangosteen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ai.opencode.mangosteen.ui.SettingsScreen
import ai.opencode.mangosteen.ui.SettingsViewModel
import ai.opencode.mangosteen.ui.theme.MangosteenTheme
import ai.opencode.mangosteen.ui.theme.applyAppTheme

class SettingsActivity : ComponentActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyAppTheme()
        setContent {
            MangosteenTheme {
                SettingsScreen(
                    onBack = { finish() },
                    viewModel = viewModel,
                )
            }
        }
    }
}
