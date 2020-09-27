package com.example.geoquiz

import android.app.Activity
import android.app.ProgressDialog.show
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Compiler.enable
import java.util.EnumSet.of

private const val TAG = "MainActivity"
private const val REQUEST_CODE_CHEAT = 0
class MainActivity : AppCompatActivity() {

    private val quizViewModel: QuizViewModel by lazy{
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }


    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: ImageButton
    private lateinit var prevButton: ImageButton
    private lateinit var questionTextView: TextView
    private lateinit var cheatButton: Button


    private var questAnswered=0
    private var trueAnswer=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG,"OnCreate(Bundle?) called")
        setContentView(R.layout.activity_main)

//        val provider: ViewModelProvider = ViewModelProviders.of(this)
//        val quizViewModel=provider.get(QuizViewModel::class.java)
//        Log.d(TAG,"Got a QuizViewModel: $quizViewModel")

        trueButton  = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)
        cheatButton = findViewById(R.id.cheat_button)



        trueButton.setOnClickListener {
                checkAnswer(true)
                quizViewModel.currentQuestionAnswered =true
                enable(false)
                sumScore()
            }
        falseButton.setOnClickListener {
                checkAnswer(false)
                quizViewModel.currentQuestionAnswered=true
                enable(false)
                sumScore()
            }

        nextButton.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }
        prevButton.setOnClickListener {
            quizViewModel.moveToPrev()
            updateQuestion()
        }
        questionTextView.setOnClickListener{
            quizViewModel.moveToNext()
            updateQuestion()
        }
        cheatButton.setOnClickListener {
            // start cheat activity
            // share info
            val answerIsTrue = quizViewModel.currentQuestionAnswer
            val intent = CheatActivity.newIntent(this@MainActivity,answerIsTrue)
            //startActivity(intent)
            startActivityForResult(intent, REQUEST_CODE_CHEAT)
        }

        updateQuestion()
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG,"OnStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG,"OnResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG,"OnPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG,"OnStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG,"OnDestroy() called")
    }

    private fun updateQuestion(){
        val questionTextResID = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResID)
        if(quizViewModel.currentQuestionAnswered == false) {
            enable(true)
        }else{ enable(false)}
    }
    private fun checkAnswer(userAnswer:Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer
        ++questAnswered
        val messageResID = when{
            quizViewModel.isCheater   ->   R.string.judgment_toast
            userAnswer==correctAnswer -> { ++trueAnswer
                                           R.string.correct_toast}
            else                      ->   R.string.incorrect_toast
        }

        Toast.makeText(this, messageResID, Toast.LENGTH_SHORT).show()
    }
    private fun enable(enable_Button:Boolean){
        falseButton.isEnabled=enable_Button
        trueButton.isEnabled=enable_Button
    }
    private fun sumScore(){
        var total = quizViewModel.questionBankSize
        var score = trueAnswer*100/total
        //  var score=trueAnswer
        if(questAnswered==total){
            var msg="You Answered the all question  "
            var scores="Your Score is $score / 100"
            Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
            Toast.makeText(this,scores,Toast.LENGTH_LONG).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode!=Activity.RESULT_OK)
            return
        if (requestCode == REQUEST_CODE_CHEAT){
            quizViewModel.isCheater = data?.getBooleanExtra(EXTRA_ANSWER_SHOWN,false)?:false
        }
    }
}


