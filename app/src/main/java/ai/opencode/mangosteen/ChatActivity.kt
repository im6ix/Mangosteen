package ai.opencode.mangosteen

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import ai.opencode.mangosteen.ui.ChatScreen
import ai.opencode.mangosteen.ui.ChatViewModel
import ai.opencode.mangosteen.ui.theme.MangosteenTheme
import ai.opencode.mangosteen.ui.theme.applyAppTheme

class ChatActivity : ComponentActivity() {

    companion object {
        const val EXTRA_ID = "conversation_id"
    }

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        applyAppTheme()

        val id = intent.getLongExtra(EXTRA_ID, -1L)
        if (id <= 0L) {
            finish()
            return
        }

        setContent {
            MangosteenTheme {
                ChatScreen(
                    conversationId = id,
                    onBack = { finish() },
                    onOpenSettings = {
                        startActivity(Intent(this, SettingsActivity::class.java))
                    },
                    viewModel = viewModel,
                )
            }
        }
    }
}
