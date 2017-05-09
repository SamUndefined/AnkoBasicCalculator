package com.example.anko.basiccalculator

import android.text.InputType
import android.view.Gravity
import android.widget.EditText
import org.jetbrains.anko.*

class MainActivityUI : AnkoComponent<MainActivity> {
    override fun createView(ui: AnkoContext<MainActivity>) = with(ui) {
        verticalLayout {
            padding = dip(50)
            editText {
                id = ENTER_FIRST_NUMBER
            }
            radioGroup {
                id = RADIO_OPERATIONS
                radioButton {
                    id = RADIO_BUTTON_ADD
                    textResource = R.string.add
                }
                radioButton {
                    id = RADIO_BUTTON_SUBTRACT
                    textResource = R.string.subtract
                }
                radioButton {
                    id = RADIO_BUTTON_MULTIPLY
                    textResource = R.string.multiply
                }
                radioButton {
                    id = RADIO_BUTTON_DIVIDE
                    textResource = R.string.divide
                }
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
                verticalMargin = dip(25)
            }
            editText {
                id = ENTER_SECOND_NUMBER
            }
            button {
                textResource = R.string.calculate
                id = BUTTON_CALCULATE
            }
        }.applyRecursively { view ->
            when (view) {
                is EditText -> {
                    view.hint = "Enter Number"
                    view.singleLine = true
                    view.setRawInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL)
                }
            }
        }
    }
}

//Package protected View IDs
@JvmField val ENTER_FIRST_NUMBER = 1
@JvmField val ENTER_SECOND_NUMBER = 2
@JvmField val RADIO_OPERATIONS = 3
@JvmField val RADIO_BUTTON_ADD = 4
@JvmField val RADIO_BUTTON_SUBTRACT = 5
@JvmField val RADIO_BUTTON_MULTIPLY = 6
@JvmField val RADIO_BUTTON_DIVIDE = 7
@JvmField val BUTTON_CALCULATE = 8