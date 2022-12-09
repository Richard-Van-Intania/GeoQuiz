package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import kotlin.random.Random

private const val TAG = "QuizViewModel"

/*
One way to do this is to store data in saved instance state.
Saved instance state is data the OS temporarily stores outside of the activity.
You can add values to saved instance state by using a SavedStateHandle
save activity when user don't use app for long time
 */
const val CURRENT_INDEX_KEY = "CURRENT_INDEX_KEY"
const val CHEAT_STATUS_KEY = "CHEAT_STATUS_KEY"

class QuizViewModel(private val savedStateHandle: SavedStateHandle) : ViewModel() {
  init {
    Log.d(TAG, "ViewModel instance created")
  }

  val questionBank =
      listOf(
          Question(R.string.question_australia, true),
          Question(R.string.question_oceans, true),
          Question(R.string.question_mideast, false),
          Question(R.string.question_africa, false),
          Question(R.string.question_americas, true),
          Question(R.string.question_asia, true))

  var currentIndex: Int
    get() = savedStateHandle[CURRENT_INDEX_KEY] ?: 0
    set(value) = savedStateHandle.set(CURRENT_INDEX_KEY, value)

  var cheatStatus: Boolean
    get() = savedStateHandle[CHEAT_STATUS_KEY] ?: false
    set(value) = savedStateHandle.set(CHEAT_STATUS_KEY, value)

  var correctCount: Int = 0
  var incorrectCount: Int = 0

  val currentAnswer: Boolean
    get() = questionBank[currentIndex].answer

  val currentQuestionText: Int
    get() = questionBank[currentIndex].textResId

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
/*
3 things do later follow book



 */