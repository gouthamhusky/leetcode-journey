package org.twopointers

import java.lang.StringBuilder


fun main() {
    threeSum(intArrayOf(0, 0, 0, 0))
}

// https://leetcode.com/problems/valid-palindrome/
fun isPalindrome(s: String): Boolean {
    val parsedString = StringBuilder()
    for (c in s.toCharArray()){
        when {
            c.isUpperCase() -> parsedString.append(c.toLowerCase())
            !c.isLetterOrDigit() -> continue
            else -> parsedString.append(c)
        }
    }
    var i = 0
    var j = parsedString.length - 1
    while (i < j){
        if (parsedString[i] != parsedString[j])
            return false
        i++
        j--
    }
    return true
}

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var left = 0
    var right = numbers.size - 1
    while (left < right){
        if (numbers[left] + numbers[right] == target)
            return intArrayOf(left + 1, right + 1)
        when{
            numbers[left] + numbers[right] == target -> return intArrayOf(left + 1, right + 1)
            numbers[left] + numbers[right] > target -> right--
            else -> left++
        }
    }
    return intArrayOf()
}

// https://leetcode.com/problems/3sum/
fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()
    val solution = mutableSetOf<List<Int>>()
    for (i in nums.indices) {
        if (i > 0 && nums[i] == nums[i - 1])
            continue
        val current = nums[i]
        val map = mutableMapOf<Int, Int>()
        for (j in i + 1 until nums.size) {
            if (map.containsKey(0 - (current + nums[j]))){
                solution.add(listOf(current, nums[j], map.get(0 - (current + nums[j]))!!))
            }
            map.put(nums[j], nums[j])
        }
    }
    return solution.toList()
}

// https://leetcode.com/problems/container-with-most-water/
fun maxArea(height: IntArray): Int {
    var left = 0
    var right = height.size - 1
    var maxArea = Int.MIN_VALUE
    while (left < right){
        val area = (right - left) * (minOf(height[left], height[right]))
        maxArea = maxOf(maxArea, area)
        when{
            height[left] <= height[right] -> left++
            else -> right--
        }
    }
    return maxArea
}