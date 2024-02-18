package com.example.calculatorinternandroidtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.calculatorinternandroidtest.databinding.ActivityMainBinding
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.input.text = "0"

        binding.buttonClear.setOnClickListener {
            handleClear()
        }
        binding.buttonRemove.setOnClickListener {
            binding.input.text = removeLast(binding.input.text.toString())
        }
        binding.button0.setOnClickListener {
            binding.input.text = addToInputText("0")
        }
        binding.button1.setOnClickListener {
            binding.input.text = addToInputText("1")
        }
        binding.button2.setOnClickListener {
            binding.input.text = addToInputText("2")
        }
        binding.button3.setOnClickListener {
            binding.input.text = addToInputText("3")
        }
        binding.button4.setOnClickListener {
            binding.input.text = addToInputText("4")
        }
        binding.button5.setOnClickListener {
            binding.input.text = addToInputText("5")
        }
        binding.button6.setOnClickListener {
            binding.input.text = addToInputText("6")
        }
        binding.button7.setOnClickListener {
            binding.input.text = addToInputText("7")
        }
        binding.button8.setOnClickListener {
            binding.input.text = addToInputText("8")
        }
        binding.button9.setOnClickListener {
            binding.input.text = addToInputText("9")
        }
        binding.buttonDivide.setOnClickListener {
            binding.input.text = addToInputText("÷")
        }
        binding.buttonMultiplication.setOnClickListener {
            binding.input.text = addToInputText("×")
        }
        binding.buttonSubtraction.setOnClickListener {
            binding.input.text = addToInputText("-")
        }
        binding.buttonAdd.setOnClickListener {
            binding.input.text = addToInputText("+")
        }
        binding.buttonDot.setOnClickListener{
            binding.input.text = addToInputText(".")
        }
        binding.buttonEqual.setOnClickListener {
            showResult()
        }
    }
    private fun handleClear(){
        binding.input.text = "0"
        binding.output.text = ""
    }
    private fun removeLast(inputValue: String): String {
        binding.output.text = ""
        if (inputValue.length <= 1) return "0"
        return inputValue.substring(0, inputValue.length - 1)
    }

    private fun addToInputText(buttonValue: String): String {
        binding.output.text = ""
        if (binding.input.text.toString().last() == '+' ||
            binding.input.text.toString().last() == '-' ||
            binding.input.text.toString().last() == '×' ||
            binding.input.text.toString().last() == '÷'
        ) {
            if (buttonValue == "+" || buttonValue == "-" || buttonValue == "×" || buttonValue == "÷") {
                return binding.input.text.toString()
            }
        }
        if (binding.input.text.toString() == "0") binding.input.text = ""
        return "${binding.input.text}$buttonValue"
    }

    private fun getInputExpression(): String {
        var expression = binding.input.text.replace(Regex("÷"), "/")
        expression = expression.replace(Regex("×"), "*")
        return expression
    }

    private fun showResult() {
        try {
            val expression = getInputExpression()
            val result = EvaluateString.evaluate(expression)
            binding.output.text = DecimalFormat("0.######").format(result).toString()
        } catch (e: Exception) {
            Toast.makeText(this, "Định dạng đã dùng không hợp lệ", Toast.LENGTH_SHORT).show()
        }
    }
}