package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.random.Random.Default.nextInt

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel(){

//    var randomValues = Random()
    val randomValues = (0..9).shuffled().last()
    var currentIndex = 0
    var isCheater = false

    private val questionBank = arrayOf(
        arrayOf(Question(R.string.question_beginner_one,true,false),
                Question(R.string.question_beginner_two,true,false),
                Question(R.string.question_beginner_three,true,false),
                Question(R.string.question_beginner_four,true,false),
                Question(R.string.question_beginner_five,true,false),
                Question(R.string.question_beginner_six,false,false),
                Question(R.string.question_beginner_seven,false,false),
                Question(R.string.question_beginner_eight,false,false),
                Question(R.string.question_beginner_nine,false,false),
                Question(R.string.question_beginner_ten,false,false)),
        arrayOf(Question(R.string.question_middle_one,true,false),
                Question(R.string.question_middle_two,true,false),
                Question(R.string.question_middle_three,true,false),
                Question(R.string.question_middle_four,true,false),
                Question(R.string.question_middle_five,true,false),
                Question(R.string.question_middle_six,false,false),
                Question(R.string.question_middle_seven,false,false),
                Question(R.string.question_middle_eight,false,false),
                Question(R.string.question_middle_nine,false,false),
                Question(R.string.question_middle_ten,false,false)),
        arrayOf(Question(R.string.question_advanced_one,true,false),
                Question(R.string.question_advanced_two,true,false),
                Question(R.string.question_advanced_three,true,false),
                Question(R.string.question_advanced_four,true,false),
                Question(R.string.question_advanced_five,true,false),
                Question(R.string.question_advanced_six,false,false),
                Question(R.string.question_advanced_seven,false,false),
                Question(R.string.question_advanced_eight,false,false),
                Question(R.string.question_advanced_nine,false,false),
                Question(R.string.question_advanced_ten,false,false))
    )

//    private val questionBank = listOf(
//        Question(R.string.question_sana,true,false),
//        Question(R.string.question_alquds,true,false),
//        Question(R.string.question_damascus,true,false),
//        Question(R.string.question_baghdad,true,false),
//        Question(R.string.question_algiers,true,false),
//        Question(R.string.question_tunis,true,false)
//    )


    val currentQuestionText: Int
    get() = questionBank[currentIndex][randomValues].textResID

    val currentQuestionAnswer: Boolean
    get()=questionBank[currentIndex][randomValues].answer

    var currentQuestionAnswered:Boolean? =null
    get() = questionBank[currentIndex][randomValues].Answered

    val questionBankSize : Int
    get() = questionBank.size

    fun moveToNext(){
        currentIndex = (currentIndex + 1) % questionBank.size

    }
    fun moveToPrev(){
        if(currentIndex == 0){
            currentIndex = (currentIndex + questionBank.size -1)
        }else{
            currentIndex = (currentIndex - 1) % questionBank.size}
    }



//    init {
//        Log.d(TAG,"ViewModel instant created")
//    }
//
//    override fun onCleared() {
//        super.onCleared()
//        Log.d(TAG,"ViewModel instant about to be destroyed")
//    }
}