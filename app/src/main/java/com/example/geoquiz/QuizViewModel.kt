package com.example.geoquiz

import android.util.Log
import androidx.lifecycle.ViewModel

private const val TAG = "QuizViewModel"
class QuizViewModel : ViewModel(){

    var currentIndex = 0
    var isCheater = false
    private val questionBank = listOf(
        Question(R.string.question_sana,true,false),
        Question(R.string.question_alquds,true,false),
        Question(R.string.question_damascus,true,false),
        Question(R.string.question_baghdad,true,false),
        Question(R.string.question_algiers,true,false),
        Question(R.string.question_tunis,true,false)
    )


    val currentQuestionText: Int
    get() = questionBank[currentIndex].textResID

    val currentQuestionAnswer: Boolean
    get()=questionBank[currentIndex].answer

    var currentQuestionAnswered:Boolean? =null
    get() = questionBank[currentIndex].Answered

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