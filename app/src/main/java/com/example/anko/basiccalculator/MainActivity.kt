package com.example.anko.basiccalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    lateinit var firstEditText: EditText
    lateinit var secondEditText: EditText
    lateinit var radioOperations: RadioGroup
    lateinit var buttonCalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        findViews()
        attachListeners()
    }

    fun findViews() {
        firstEditText = find<EditText>(ENTER_FIRST_NUMBER)
        secondEditText = find<EditText>(ENTER_SECOND_NUMBER)
        radioOperations = find(RADIO_OPERATIONS)
        buttonCalculate = find(BUTTON_CALCULATE)
    }

    fun attachListeners() {
        //Set second editText's imeOptions to press the calculate button
        secondEditText.setOnEditorActionListener { textView, i, keyEvent ->
            buttonCalculate.performClick()
        }

        //Set calculate button's on click method
        buttonCalculate.onClick {
            val firstNumber = firstEditText.text.toString().toFloat()
            val secondNumber = secondEditText.text.toString().toFloat()

            calculate(firstNumber, secondNumber)
        }
    }

    fun calculate(firstNum: Float, secondNum: Float) {
        val checkedButton = radioOperations.checkedRadioButtonId

        when(checkedButton) {
            RADIO_BUTTON_ADD      -> toast("${firstNum + secondNum}")
            RADIO_BUTTON_SUBTRACT -> toast("${firstNum - secondNum}")
            RADIO_BUTTON_MULTIPLY -> toast("${firstNum * secondNum}")
            RADIO_BUTTON_DIVIDE   -> toast("${firstNum / secondNum}")
            else -> toast("You have selected the void. The answer is infinity.")
        }
    }
}