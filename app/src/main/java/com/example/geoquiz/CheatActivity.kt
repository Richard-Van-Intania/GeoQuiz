package com.example.geoquiz

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityCheatBinding

private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"
private var answerIsTrue = false

class CheatActivity : AppCompatActivity() {
  private lateinit var binding: ActivityCheatBinding
  companion object {
    fun newIntent(packageContext: Context, answerIsTrue: Boolean): Intent {
      return Intent(packageContext, CheatActivity::class.java).apply {
        putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
      }
    }
  }
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityCheatBinding.inflate(layoutInflater)
    setContentView(binding.root)
    answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false)

    binding.buttonShowAnswer.setOnClickListener {
      val answerText =
          when {
            answerIsTrue -> R.string.true_button
            else -> R.string.false_button
          }
      binding.textAnswer.setText(answerText)
    }
  }
}
