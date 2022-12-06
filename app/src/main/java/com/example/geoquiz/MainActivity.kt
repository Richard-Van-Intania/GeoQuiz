package com.example.geoquiz

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
  //  private lateinit var textQuestion: TextView
  //  private lateinit var buttonTrue: Button
  //  private lateinit var buttonFalse: Button
  //  private lateinit var buttonNext: Button
  //  private lateinit var buttonDebug: Button

  private lateinit var binding: ActivityMainBinding

  /*
  “The viewModels() property delegate works the same way:
  Your QuizViewModel will not be initialized unless you access it.
  By referencing it in a logging message, you can initialize it and log the value on the same line.”
  */
  private val quizViewModel: QuizViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    Log.d(TAG, "onCreate(Bundle?) called: Created")
    // setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    // By referencing it in a logging message, you can initialize it and log the value on the same
    // line
    Log.d(TAG, "Got a QuizViewModel: $quizViewModel")

    //    textQuestion = findViewById(R.id.textQuestion)
    //    buttonTrue = findViewById(R.id.buttonTrue)
    //    buttonFalse = findViewById(R.id.buttonFalse)
    //    buttonNext = findViewById(R.id.buttonNext)
    //    buttonDebug = findViewById(R.id.buttonDebug)

    //    buttonTrue.setOnClickListener { view: View ->
    //      Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
    //    }
    //    buttonFalse.setOnClickListener { view: View ->
    //      Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
    //    }
    //
    //    buttonNext.setOnClickListener { view: View -> }
    //
    //    buttonDebug.setOnClickListener { view: View ->
    //      Snackbar.make(view, "Debug message", Snackbar.LENGTH_INDEFINITE)
    //          .setAction(R.string.ok, {})
    //          .show()
    //    }

    binding.buttonTrue.setOnClickListener { view: View ->
      if (!quizViewModel.currentIsAnswerd) {
        if (quizViewModel.currentAnswer) {
          quizViewModel.currentIsAnswerd = true
          Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.correctCount++
        } else {
          quizViewModel.currentIsAnswerd = true
          Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.incorrectCount++
        }
      } else {
        Snackbar.make(view, R.string.alreadyAnswer, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) {
              quizViewModel.getNextIndex()
              updateQuestion()
            }
            .show()
      }
    }

    binding.buttonFalse.setOnClickListener { view: View ->
      if (!quizViewModel.currentIsAnswerd) {
        if (!quizViewModel.currentAnswer) {
          quizViewModel.currentIsAnswerd = true
          Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.correctCount++
        } else {
          quizViewModel.currentIsAnswerd = true
          Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
              .setAction(R.string.ok) {}
              .show()
          quizViewModel.incorrectCount++
        }
      } else {
        Snackbar.make(view, R.string.alreadyAnswer, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.ok) {
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
              "Score Now: ${quizViewModel.correctCount}/${quizViewModel.incorrectCount}",
              Toast.LENGTH_SHORT)
          .show()
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
