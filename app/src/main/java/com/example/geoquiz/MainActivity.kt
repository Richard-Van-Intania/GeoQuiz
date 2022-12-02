package com.example.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
  //  private lateinit var textQuestion: TextView
  //  private lateinit var buttonTrue: Button
  //  private lateinit var buttonFalse: Button
  //  private lateinit var buttonNext: Button
  //  private lateinit var buttonDebug: Button

  private lateinit var binding: ActivityMainBinding

  private val questionBank =
      listOf(
          Question(R.string.question_australia, true),
          Question(R.string.question_oceans, true),
          Question(R.string.question_mideast, false),
          Question(R.string.question_africa, false),
          Question(R.string.question_americas, true),
          Question(R.string.question_asia, true))

  private var currentIndex = 0
  private var correctCount = 0
  private var incorrectCount = 0

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

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
      if (questionBank[currentIndex].answer) {
        Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok) {}
            .show()
        correctCount++
        currentIndex = (currentIndex + 1) % questionBank.size
        updateText()
      } else {
        Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok) {}
            .show()
        incorrectCount++
        currentIndex = (currentIndex + 1) % questionBank.size
        updateText()
      }
    }

    binding.buttonFalse.setOnClickListener { view: View ->
      if (!questionBank[currentIndex].answer) {
        Snackbar.make(view, R.string.correct_toast, Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok) {}
            .show()
        correctCount++
        currentIndex = (currentIndex + 1) % questionBank.size
        updateText()
      } else {
        Snackbar.make(view, R.string.incorrect_toast, Snackbar.LENGTH_SHORT)
            .setAction(R.string.ok) {}
            .show()
        incorrectCount++
        currentIndex = (currentIndex + 1) % questionBank.size
        updateText()
      }
    }

    binding.buttonNext.setOnClickListener {
      currentIndex = (currentIndex + 1) % questionBank.size
      updateText()
    }

    binding.buttonRandom.setOnClickListener {
      currentIndex = Random.nextInt(questionBank.size)
      updateText()
    }

    binding.buttonDebug.setOnClickListener {
      Toast.makeText(this, "${correctCount}/${incorrectCount}", Toast.LENGTH_SHORT).show()
    }

    updateText()
  }

  private fun updateText() {
    binding.textQuestion.setText(questionBank[currentIndex].textResId)
  }
}
