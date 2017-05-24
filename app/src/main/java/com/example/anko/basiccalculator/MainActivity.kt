package com.example.anko.basiccalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.DigitsKeyListener
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
        firstEditText = find(R.id.edit_first_number)
        secondEditText = find(R.id.edit_second_number)
        radioOperations = find(R.id.radio_group)
        buttonCalculate = find(R.id.btn_calculate)
    }

    fun attachListeners() {
        //Ensure only one decimal point and plus/minus sign can be written
        for (editText in listOf(firstEditText, secondEditText)) {
            editText.keyListener = DigitsKeyListener.getInstance(true,true)
        }

        //Set second editText's imeOptions to press the calculate button
        secondEditText.setOnEditorActionListener { textView, i, keyEvent ->
            buttonCalculate.performClick()
        }

        //Set calculate button's on click method
        buttonCalculate.onClick {
            val firstVal = firstEditText.text.toString()
            val secondVal = secondEditText.text.toString()

            if (firstVal.isEmpty() || secondVal.isEmpty()) {
                toast("Please Enter 2 values")
            }
            else {
                calculate(firstVal, secondVal)
            }
        }
    }

    fun calculate(firstVal: String, secondVal: String) {
        val firstNum = firstVal.toDouble()
        val secondNum = secondVal.toDouble()
        val checkedButton = radioOperations.checkedRadioButtonId

        when(checkedButton) {
            R.id.radio_btn_add      -> toast("${firstNum + secondNum}")
            R.id.radio_btn_subtract -> toast("${firstNum - secondNum}")
            R.id.radio_btn_multiply -> toast("${firstNum * secondNum}")
            R.id.radio_btn_divide   -> {
                if(secondNum.equals(0.0)){
                    toast("Cannot divide by 0")
                }
                else {
                    toast("${firstNum.div(secondNum)}")
                }
            }
            else -> toast("Please select an operation")
        }
    }
}