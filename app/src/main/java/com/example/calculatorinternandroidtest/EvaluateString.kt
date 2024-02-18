package com.example.calculatorinternandroidtest

import java.util.*

object EvaluateString {
    fun evaluate(expression: String): Double {
        val chars = expression.toCharArray()
        val numbers = Stack<Double>()
        val ops = Stack<Char>()

        var i = 0
        while (i < chars.size) {
            if (chars[i] == ' ') {
                i++
                continue
            }
            if (chars[i] in '0'..'9' || chars[i] == '.') {
                val stringBuilder = StringBuilder()

                while (i < chars.size && (chars[i] in '0'..'9' || chars[i] == '.')) {
                    stringBuilder.append(chars[i])
                    i++
                }
                numbers.push(stringBuilder.toString().toDouble())

                i--
            } else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*' || chars[i] == '/') {
                while (!ops.empty() && hasPrecedence(chars[i], ops.peek()))
                    numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()))

                ops.push(chars[i])
            }
            i++
        }

        while (!ops.empty())
            numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()))

        return numbers.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        return !(op1 == '*' || op1 == '/' && (op2 == '+' || op2 == '-'))
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> if (b == 0.0) throw UnsupportedOperationException("Cannot divide by zero") else a / b
            else -> 0.0
        }
    }
}