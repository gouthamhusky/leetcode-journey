package org.stacks

import java.util.*

fun largestRectangleArea(heights: IntArray): Int {
    var i = 0
    var pseudoIndex = -1
    val left = mutableListOf<Int>()
    var right = mutableListOf<Int>()
    var width = mutableListOf<Int>()
    var area = mutableListOf<Int>()

    var s = Stack<Pair<Int, Int>>();

    // get the left array
    run {
        i = 0
        while (i < heights.size) {
            if (s.isEmpty()) left.add(pseudoIndex)
            else if (!s.isEmpty() && s.peek().first < heights[i]) left.add(s.peek().second)
            else if (!s.isEmpty() && s.peek().first >= heights[i]) {
                while (!s.isEmpty() && s.peek().first >= heights[i]) s.pop()
                if (s.isEmpty()) left.add(pseudoIndex)
                else left.add(s.peek().second)
            }
            s.add(Pair<Int, Int>(heights[i], i))
            i++
        }
    }

    s = Stack()
    pseudoIndex = heights.size

    // get the right array
    run {
        i = heights.size - 1
        while (i >= 0) {
            if (s.isEmpty()) right.add(pseudoIndex)
            else if (!s.isEmpty() && s.peek().first < heights[i]) right.add(s.peek().second)
            else if (!s.isEmpty() && s.peek().first >= heights[i]) {
                while (!s.isEmpty() && s.peek().first >= heights[i]) s.pop()
                if (s.isEmpty()) right.add(pseudoIndex)
                else right.add(s.peek().second)
            }
            s.add(Pair(heights[i], i))
            i--
        }
    }
    right.reverse()


    // calculate the width array
    run {
        i = 0
        while (i < left.size) {
            width.add(right[i] - left[i] - 1)
            i++
        }
    }


    // calculate the area array
    run {
        i = 0
        while (i < width.size) {
            area.add(width[i] * heights[i])
            i++
        }
    }

    // return the max in area array
    return area.max()
}

class MyQueue() {

    val first = Stack<Int>()
    val second = Stack<Int>()

    fun push(x: Int) {
        first.push(x)
    }

    fun pop(): Int {
        while (!first.empty()){
            val element = first.pop()
            second.push(element)
        }
        val popped = second.pop()

        while (!second.empty()){
            first.push(second.pop())
        }

        return popped
    }

    fun peek(): Int {
        while (!first.empty()){
            val element = first.pop()
            second.push(element)
        }

        val peeked = second.peek()

        while (!second.empty()){
            first.push(second.pop())
        }

        return peeked
    }

    fun empty(): Boolean {
        return first.empty()
    }
}

fun isValid(s: String): Boolean {
    val stack = Stack<Char>()
    for (c in s.toCharArray()){
        if (c == '(' || c == '[' || c =='{')
            stack.push(c)
        else{
            when(c){
                ')' -> if (stack.empty() || stack.pop() != '(') return false
                ']' -> if (stack.empty() || stack.pop() != '[') return false
                '}' -> if (stack.empty() || stack.pop() != '{') return false
            }
        }
    }
    return stack.empty()
}

fun minAddToMakeValid(s: String): Int {
    val stack = Stack<Char>()
    for (c in s.toCharArray()){
        if(c == ')'){
            if (!stack.isEmpty() && stack.peek() == '(')
                stack.pop()
            else
                stack.push(c)
        }
        else
            stack.push(c)
    }
    return stack.size
}

class MinStack() {

    val stack = Stack<Pair<Int, Int>>()

    fun push(`val`: Int) {
        if(stack.isEmpty()){
            stack.push(Pair(`val`, `val`))
        }
        else{
            val minimum = if (`val` < stack.peek().second) `val` else stack.peek().second
            stack.push(Pair(`val`, minimum))
        }
    }

    fun pop() {
        stack.pop()
    }

    fun top(): Int {
        return stack.peek().first
    }

    fun getMin(): Int {
        return stack.peek().second
    }
}

fun evalRPN(tokens: Array<String>): Int {
    val stack = Stack<Int>()
    val set = setOf("+", "-", "*", "/")
    for (token in tokens){
        if (token !in set)
            stack.push(token.toInt())
        else{
            val op1 = stack.pop()
            val op2 = stack.pop()
            when(token){
                "+" -> stack.push(op2 + op1)
                "-" -> stack.push(op2 - op1)
                "*" -> stack.push(op2 * op1)
                "/" -> stack.push(op2 / op1)
            }
        }
    }
    return stack.peek()
}

fun generateParenthesis(n: Int): List<String> {
    val result = mutableListOf<String>()
    generateParenthesisHelper(0, 0, n, StringBuilder(), result)
    return result
}

fun generateParenthesisHelper(openN: Int, closedN: Int, n: Int, stack: StringBuilder, result: MutableList<String>){
    if(openN == n && closedN == n){
        result.add(stack.toString())
        return
    }
    if(openN < n){
        stack.append("(")
        generateParenthesisHelper(openN + 1, closedN, n, stack, result)
        stack.deleteCharAt(stack.length - 1)
    }
    if(openN > closedN){
        stack.append(")")
        generateParenthesisHelper(openN, closedN + 1, n, stack, result)
        stack.deleteCharAt(stack.length - 1)
    }
}

fun dailyTemperatures(temperatures: IntArray): IntArray {
    val stack = Stack<Pair<Int, Int>>()
    val hotterDay = mutableListOf<Int>()
    for (i in temperatures.size - 1 downTo 0){
        if (stack.empty())
            hotterDay.add(-1)
        else if (!stack.isEmpty() && stack.peek().first > temperatures[i])
            hotterDay.add(stack.peek().second)
        else if (!stack.isEmpty() && stack.peek().first <= temperatures[i]){
            while (!stack.isEmpty() && stack.peek().first <= temperatures[i])
                stack.pop()
            if (stack.isEmpty())
                hotterDay.add(-1)
            else
                hotterDay.add(stack.peek().second)
        }
        stack.push(Pair(temperatures[i], i))
    }
    hotterDay.reverse()
    for (i in hotterDay.indices){
        if (hotterDay[i] == -1)
            hotterDay[i] = 0
        else hotterDay[i] = hotterDay[i] - i
    }
    return hotterDay.toIntArray()
}


fun main() {
    dailyTemperatures(intArrayOf(73,74,75,71,69,72,76,73))
}