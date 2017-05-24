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
                id = R.id.edit_first_number
            }
            radioGroup {
                id = R.id.radio_group
                radioButton {
                    id = R.id.radio_btn_add
                    textResource = R.string.add
                }
                radioButton {
                    id = R.id.radio_btn_subtract
                    textResource = R.string.subtract
                }
                radioButton {
                    id = R.id.radio_btn_multiply
                    textResource = R.string.multiply
                }
                radioButton {
                    id = R.id.radio_btn_divide
                    textResource = R.string.divide
                }
            }.lparams {
                gravity = Gravity.CENTER_HORIZONTAL
                verticalMargin = dip(25)
            }
            editText {
                id = R.id.edit_second_number
            }
            button {
                textResource = R.string.calculate
                id = R.id.btn_calculate
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