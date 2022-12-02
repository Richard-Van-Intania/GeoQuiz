package com.example.geoquiz

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.geoquiz.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
  //  private lateinit var text1: TextView
  //  private lateinit var buttonTrue: Button
  //  private lateinit var buttonFalse: Button
  //  private lateinit var buttonNext: Button
  //  private lateinit var buttonDebug: Button

  private val questionBank =
      listOf(
          Question(R.string.question_australia, true),
          Question(R.string.question_oceans, true),
          Question(R.string.question_mideast, false),
          Question(R.string.question_africa, false),
          Question(R.string.question_americas, true),
          Question(R.string.question_asia, true))

  private var currentIndex = 0

  private lateinit var binding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    // setContentView(R.layout.activity_main)
    binding = ActivityMainBinding.inflate(layoutInflater)
    setContentView(binding.root)

    //    text1 = findViewById(R.id.text1)
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
      Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_SHORT).show()
    }

    binding.buttonFalse.setOnClickListener { view: View ->
      Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show()
    }

    binding.buttonNext.setOnClickListener { view: View ->
      Snackbar.make(view, "Next", Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, {}).show()
    }

    binding.buttonDebug.setOnClickListener { view: View ->
      Snackbar.make(view, "Debug message", Snackbar.LENGTH_INDEFINITE)
          .setAction(R.string.ok, {})
          .show()
    }

    val questionTextResId = questionBank[currentIndex].textResId
    binding.text1.setText(questionTextResId)
  }
}
