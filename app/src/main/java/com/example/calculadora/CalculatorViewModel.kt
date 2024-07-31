package com.example.calculadora

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import net.objecthunter.exp4j.ExpressionBuilder
import java.text.DecimalFormat

class CalculatorViewModel : ViewModel() {

    private val _display = MutableLiveData<String>().apply { value = "" }
    val display: LiveData<String> = _display

    private var lastNumeric = false
    private var stateError = false
    private var lastDot = false

    private val decimalFormat = DecimalFormat("#.##")

    fun updateDisplay(value: String) {
        if (stateError) {
            _display.value = value
            stateError = false
        } else {
            val currentText = _display.value ?: ""
            if (isOperator(value) && (currentText.isEmpty() || isOperator(currentText.last().toString()))) {
                // Do nothing if operator is entered consecutively or without a number first
                return
            }

            if (value == "." && (lastDot || currentText.isEmpty() || isOperator(currentText.last().toString()))) {
                // Do nothing if dot is entered consecutively, first, or after an operator
                return
            }

            _display.value = currentText + value
            lastNumeric = value != "." && !isOperator(value)
            lastDot = value == "."
        }
    }

    fun calculate() {
        try {
            val expression = ExpressionBuilder(_display.value).build()
            val result = expression.evaluate()
            _display.value = decimalFormat.format(result)
            lastDot = false // Reset dot flag after calculation
        } catch (e: Exception) {
            _display.value = "Error"
            stateError = true
            lastNumeric = false
        }
    }

    fun calculateTrigonometric(function: String) {
        try {
            val expressionStr = _display.value ?: return
            val value = ExpressionBuilder(expressionStr).build().evaluate()
            val result = when (function) {
                "tan" -> kotlin.math.tan(Math.toRadians(value))
                "cos" -> kotlin.math.cos(Math.toRadians(value))
                "sin" -> kotlin.math.sin(Math.toRadians(value))
                else -> throw IllegalArgumentException("Invalid function")
            }
            _display.value = decimalFormat.format(result)
            lastDot = false // Reset dot flag after calculation
        } catch (e: Exception) {
            _display.value = "Error"
            stateError = true
            lastNumeric = false
        }
    }

    fun clear() {
        _display.value = ""
        lastNumeric = false
        lastDot = false
        stateError = false
    }

    private fun isOperator(value: String): Boolean {
        return value == "+" || value == "-" || value == "*" || value == "/"
    }
}
