package com.example.geoquiz

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Button
import android.widget.TextView

const val EXTRA_ANSWER_SHOWN           = "com.example.geoquiz.answer_shown"
private const val EXTRA_ANSWER_IS_TRUE = "com.example.geoquiz.answer_is_true"

class CheatActivity : AppCompatActivity() {

    private var answerIsTrue = false

    private lateinit var answerTextView  : TextView
    private lateinit var showAnswerButton: Button
    private lateinit var sdkVersion:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cheat)


        answerIsTrue = intent.getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false)

        answerTextView   = findViewById(R.id.answer_text_view)
        showAnswerButton = findViewById(R.id.show_answer_button)
        sdkVersion       = findViewById(R.id.sdk_version)

        showAnswerButton.setOnClickListener {
            val answerText = when{
                answerIsTrue -> R.string.true_button
                else         -> R.string.false_button
            }
            answerTextView.setText(answerText)
            setAnswerShownResult(true)
        }

        sdkVersion.setText("API Level " + Build.VERSION.SDK_INT)
    }

private fun setAnswerShownResult(isAnswerShown: Boolean){
    val data= Intent().apply {
        putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown)
    }
    setResult(Activity.RESULT_OK,data)
}

companion object{
    fun newIntent(packageContext: Context, answerIsTrue: Boolean):Intent{
        return Intent(packageContext,CheatActivity::class.java).apply {
            putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue)
        }
    }
}

}