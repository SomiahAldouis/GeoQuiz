package com.example.geoquiz

import android.app.ProgressDialog.show
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var trueButton: Button
    private lateinit var falseButton: Button
    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    private val questionBank = listOf(
        Question(R.string.question_sana,true),
        Question(R.string.question_alquds,true),
        Question(R.string.question_damascus,true),
        Question(R.string.question_baghdad,true),
        Question(R.string.question_algiers,true),
        Question(R.string.question_tunis,true)
    )

    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton  = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)
        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
              checkAnswer(true)
        }
        falseButton.setOnClickListener {
              checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }
        prevButton.setOnClickListener {
            if(currentIndex == 0){
                currentIndex = (currentIndex + questionBank.size -1)
            }else{
            currentIndex = (currentIndex - 1) % questionBank.size}
            updateQuestion()
        }
        questionTextView.setOnClickListener{
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()

    }
    private fun updateQuestion(){
        val questionTextResID = questionBank[currentIndex].textResID
        questionTextView.setText(questionTextResID)
    }
    private fun checkAnswer(userAnswer:Boolean){
        val correctAnswer= questionBank[currentIndex].answer
        val messageResID = if( userAnswer == correctAnswer){
            R.string.correct_toast
        }else {
            R.string.incorrect_toast
        }
        Toast.makeText(this, messageResID,Toast.LENGTH_SHORT).show()
    }
}