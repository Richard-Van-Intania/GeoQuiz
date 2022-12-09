package com.example.geoquiz

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityCheatBinding
import com.google.android.material.snackbar.Snackbar

private const val EXTRA_CURRENT_ANSWER = "com.example.geoquiz.currentAnswer"
private const val EXTRA_CURRENT_INDEX = "com.example.geoquiz.currentIndex"
const val EXTRA_CURRENT_CHEAT_STATUS = "com.example.geoquiz.currentCheatStatus"
private var currentAnswer = false

class CheatActivity : AppCompatActivity() {
  private lateinit var binding: ActivityCheatBinding
  companion object {
    fun newIntent(packageContext: Context, currentIndex: Int, currentAnswer: Boolean): Intent {
      return Intent(packageContext, CheatActivity::class.java).apply {
        putExtra(EXTRA_CURRENT_ANSWER, currentAnswer)
        putExtra(EXTRA_CURRENT_INDEX, currentIndex)
      }
    }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCheatBinding.inflate(layoutInflater)
    setContentView(binding.root)
    currentAnswer = intent.getBooleanExtra(EXTRA_CURRENT_ANSWER, false)

    binding.buttonShowAnswer.setOnClickListener {
      val answerText =
          when {
            currentAnswer -> R.string.true_button
            else -> R.string.false_button
          }
      binding.textAnswer.setText(answerText)
      setCheatStatus(true)
    }
    binding.buttonDebugCheat.setOnClickListener { view: View ->
      Snackbar.make(
              view, intent.getIntExtra(EXTRA_CURRENT_INDEX, 0).toString(), Snackbar.LENGTH_SHORT)
          .show()
    }
  }

  private fun setCheatStatus(status: Boolean) {
    val data = Intent().apply { putExtra(EXTRA_CURRENT_CHEAT_STATUS, status) }
    setResult(Activity.RESULT_OK, data)
  }
}
