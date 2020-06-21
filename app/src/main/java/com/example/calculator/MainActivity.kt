package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {

//    private val txv_value =  txv_result as TextView

    //numbers
    private val ZERO : String = "0"
    private val ONE : String = "1"
    private val TWO : String = "2"
    private val THREE : String = "3"
    private val FOUR : String = "4"
    private val FIVE : String = "5"
    private val SIX : String = "6"
    private val SEVEN : String = "7"
    private val EIGHT : String = "8"
    private val NINE : String = "9"

    private var INIT = ""

    private var currentOperation = INIT


//    operators
    private  val PLUS : String = " + "
    private  val MINUS : String = " - "
    private val DIVIDE : String = " / "
    private  val MULTIPLY : String = " * "

//    variables
    private var currentNumber : Double = 0.00
    private var currentResult : Double = 0.00

    private var isOperatorButtonClicked = false
    private var isEqualButtonClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initNumberButtonAction()
        txv_result.text = ZERO

        btn_equals.setOnClickListener {
        }

        txv_result.setOnTouchListener { view, motionEvent ->
            val action = motionEvent.action
            when(action){

            }
        }

        txv_result.setOnTouchListener(object : OnSwipeTouchListener(){
            override fun onSwipeLeft() {
                Log.e("ViewSwipe", "Left")
                backSpace()
            }

            override fun onSwipeRight() {
                Log.e("ViewSwipe", "Right")
                backSpace()
            }
        })
    }

    private fun backSpace(){
        var currentValue = txv_result.text.toString()

        val charsLimit = if (currentValue.first().isDigit()) 1 else 2

        if (currentValue.length > charsLimit)
            currentValue = currentValue.substring(0, currentValue.length - 1)
        else
            currentValue = ZERO

        currentNumber = convertStringToDouble(currentValue)
        txv_result.text = currentValue
    }

    private fun onNumberClicked(number : String){

//        get the value already the screen
        var currentValue = txv_result.text.toString()

//        check if the current value is zero that is empty


        if (currentValue == ZERO || isEqualButtonClicked || isOperatorButtonClicked){
            currentValue = number
        }else{
            currentValue = StringBuilder().append(currentValue).append(number).toString()
        }


        currentValue = (if (currentValue != ZERO) StringBuilder().append(currentValue).append(number).toString() else number)

//        convert the current value to double for mathematical calculation
        currentNumber = convertStringToDouble(currentValue)

        txv_result.text = currentValue
    }

    private fun onOperatorClicked(operator : String){

    }

    private fun initNumberButtonAction(){

        btn_zero.setOnClickListener {  onNumberClicked(ZERO)}
        btn_one.setOnClickListener { onNumberClicked(ONE) }
        btn_two.setOnClickListener { onNumberClicked(TWO) }
        btn_three.setOnClickListener {  onNumberClicked(THREE)}
        btn_four.setOnClickListener {  onNumberClicked(FOUR)}
        btn_five.setOnClickListener {  onNumberClicked(FIVE)}
        btn_six.setOnClickListener {  onNumberClicked(SIX)}
        btn_seven.setOnClickListener {  onNumberClicked(SEVEN)}
        btn_eight.setOnClickListener {  onNumberClicked(EIGHT)}
        btn_nine.setOnClickListener {  onNumberClicked(NINE)}

    }

    private fun convertStringToDouble(value : String) : Double{
        return  value.toDouble()
    }
}
