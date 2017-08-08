package com.example.anko.basiccalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.DigitsKeyListener
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import org.jetbrains.anko.*

class MainActivity : AppCompatActivity() {

    private val firstEditText by lazy { find<EditText>(R.id.edit_first_number) }
    private val secondEditText by lazy { find<EditText>(R.id.edit_second_number) }
    private val radioOperations by lazy { find<RadioGroup>(R.id.radio_group) }
    private val buttonCalculate by lazy { find<Button>(R.id.btn_calculate) }

    private val firstVal: String
        get() = firstEditText.text.toString()

    private val secondVal: String
        get() = secondEditText.text.toString()

    private val answer: String
        get() {
            val firstNum = firstVal.toDouble()
            val secondNum = secondVal.toDouble()
            val checkedButton = radioOperations.checkedRadioButtonId

            return when (checkedButton) {
                R.id.radio_btn_add -> "${firstNum + secondNum}"
                R.id.radio_btn_subtract -> "${firstNum - secondNum}"
                R.id.radio_btn_multiply -> "${firstNum * secondNum}"
                R.id.radio_btn_divide -> {
                    if (secondNum.isZero()) "Cannot divide by 0"
                    else "${firstNum.div(secondNum)}"
                }
                else -> "Please select an operation"
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MainActivityUI().setContentView(this)
        restrictInput()
        makeButtonClickable()
    }

    private fun restrictInput() {
        //Ensure only one decimal point and plus/minus sign can be written
        for (editText in listOf(firstEditText, secondEditText)) {
            editText.keyListener = DigitsKeyListener.getInstance(true, true)
        }
    }

    private fun makeButtonClickable() {
        //Set second editText's imeOptions to press the answer button
        secondEditText.setOnEditorActionListener { _, _, _ ->
            buttonCalculate.performClick()
        }

        //Set answer button's on click method
        buttonCalculate.onClick {
            if (firstVal.isEmpty() || secondVal.isEmpty()) toast("Please Enter 2 values")
            else toast(answer)
        }
    }
}

//Extension function fun!
//By adding 0 to a Double, we can convert all zero values (including -0) to 0.0 for easier zero value checking.
fun Double.isZero() : Boolean = this + 0 == 0.0