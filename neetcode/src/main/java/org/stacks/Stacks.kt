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

fun main() {
    val heights = intArrayOf(2, 1, 5, 6, 2, 3)
    println(largestRectangleArea(heights))
}