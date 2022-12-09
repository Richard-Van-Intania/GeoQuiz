package com.example.geoquiz

import android.app.Activity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding

  /*
  “The viewModels() property delegate works the same way:
  Your QuizViewModel will not be initialized unless you access it.
  By referencing it in a logging message, you can initialize it and log the value on the same line.”
  */
  private val quizViewModel: QuizViewModel by viewModels()

  private val cheatLauncher =
      registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
          quizViewModel.cheatStatus =
              result.data?.getBooleanExtra(EXTRA_CURRENT_CHEAT_STATUS, false) ?: false
        }
      }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate(Bundle?) called: Created")

    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // By referencing it in a logging message, you can initialize it and log the value on the same
    // line
    Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

    binding.buttonTrue.setOnClickListener { view: View ->
      if (!quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer) {
        if (quizViewModel.currentAnswer) {
          quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer = true
          Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.correctCount++
        } else {
          quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer = true
          Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.incorrectCount++
        }
      } else {
        Snackbar.make(view, R.string.alreadyAnswer, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.next_button) {
              quizViewModel.getNextIndex()
              updateQuestion()
            }
            .show()
      }
    }

    binding.buttonFalse.setOnClickListener { view: View ->
      if (!quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer) {
        if (!quizViewModel.currentAnswer) {
          quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer = true
          Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.correctCount++
        } else {
          quizViewModel.questionBank[quizViewModel.currentIndex].isAnswer = true
          Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.incorrectCount++
        }
      } else {
        Snackbar.make(view, R.string.alreadyAnswer, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.next_button) {
              quizViewModel.getNextIndex()
              updateQuestion()
            }
            .show()
      }
    }

    binding.buttonNext.setOnClickListener {
      quizViewModel.getNextIndex()
      updateQuestion()
    }

    binding.textQuestion.setOnClickListener {
      quizViewModel.getNextIndex()
      updateQuestion()
    }

    binding.buttonPrev.setOnClickListener {
      quizViewModel.getPrevIndex()
      updateQuestion()
    }

    binding.buttonRandom.setOnClickListener {
      quizViewModel.getRandomIndex()
      updateQuestion()
    }

    binding.buttonDebug.setOnClickListener {
      Toast.makeText(
              this,
              "Score Now: ${quizViewModel.correctCount}/${quizViewModel.incorrectCount}, Cheater?: ${quizViewModel.cheatStatus}",
              Toast.LENGTH_SHORT)
          .show()
    }
    binding.buttonCheat.setOnClickListener {
      val intent =
          CheatActivity.newIntent(
              this@MainActivity, quizViewModel.currentIndex, quizViewModel.currentAnswer)
      cheatLauncher.launch(intent)
    }

    updateQuestion()
  }

  override fun onStart() {
    super.onStart()
    Log.d(TAG, "onStart() called: Started")
  }

  override fun onResume() {
    super.onResume()
    Log.d(TAG, "onResume() called: Resumed")
  }

  override fun onPause() {
    super.onPause()
    Log.d(TAG, "onPause() called: Started")
  }

  override fun onStop() {
    super.onStop()
    Log.d(TAG, "onStop() called: Created")
  }

  override fun onDestroy() {
    super.onDestroy()
    Log.d(TAG, "onDestroy() called: destroyed or Nonexistent")
  }

  private fun updateQuestion() {
    binding.textQuestion.setText(quizViewModel.currentQuestionText)
  }
}

/*
3 things do later follow book najaaa



 */
