package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "QuizViewModel"

class QuizViewModel : ViewModel() {
  init {
    Log.d(TAG, "ViewModel instance created")
  }

  private val questionBank =
      listOf(
          Question(R.string.question_australia, true),
          Question(R.string.question_oceans, true),
          Question(R.string.question_mideast, false),
          Question(R.string.question_africa, false),
          Question(R.string.question_americas, true),
          Question(R.string.question_asia, true))

  private var currentIndex: Int = 0
  private var correctCount: Int = 0
  private var incorrectCount: Int = 0

  val currentQuestionAnswer: Boolean
    get() = questionBank[currentIndex].answer

  val currentQuestionAlreadyAnswer: Boolean
    get() = questionBank[currentIndex].answer

  val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

  val correctAnswerCount: Int
    get() = correctCount
  val incorrectAnswerCount: Int
    get() = incorrectCount

  fun getNextIndex() {
    currentIndex = (currentIndex + 1) % questionBank.size
  }
  fun getPrevIndex() {
    currentIndex =
        if (currentIndex == 0) {
          questionBank.size - 1
        } else {
          (currentIndex - 1) % questionBank.size
        }
  }
  fun getRandomIndex() {
    currentIndex = Random.nextInt(questionBank.size)
  }

  /*
  “The onCleared() function is called just before a ViewModel is destroyed.
    This is a useful place to perform any cleanup, such as un-observing a data source”
  */
  override fun onCleared() {
    super.onCleared()
    Log.d(TAG, "ViewModel instance about to be destroyed")
  }
}
