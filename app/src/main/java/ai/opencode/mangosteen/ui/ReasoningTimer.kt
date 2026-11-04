package ai.opencode.mangosteen.ui

import android.os.SystemClock

/**
 * Measures how long a reply spends reasoning. The clock starts when the
 * request is sent, so a provider that delivers reasoning in one chunk still
 * reports the first-token latency, and freezes at the first content chunk.
 */
internal class ReasoningTimer(
    private val clock: () -> Long = SystemClock::elapsedRealtime,
) {
    private var startedAt = 0L
    private var finishedAt = 0L
    private var sawReasoning = false

    fun start() {
        startedAt = clock()
        finishedAt = 0L
        sawReasoning = false
    }

    fun onReasoning() {
        sawReasoning = true
    }

    /** Freezes the clock on the first call and returns the duration. */
    fun finish(): Long? {
        if (startedAt <= 0L) return null
        if (finishedAt <= 0L) finishedAt = clock()
        return if (sawReasoning) (finishedAt - startedAt).coerceAtLeast(0L) else null
    }
}
