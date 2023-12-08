import kotlin.math.max
import kotlin.math.min

fun main() {
    trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1))
}

fun isPalindrome(s: String): Boolean {
    val stringBuilder = StringBuilder()
    for (c in s){
        if (!c.isLetterOrDigit())
            continue
        else if (c.isUpperCase())
            stringBuilder.append(c.lowercaseChar())
        else
            stringBuilder.append(c)
    }
    var start = 0
    var end = stringBuilder.length - 1
    while (start < end){
        if (stringBuilder[start] != stringBuilder[end])
            return false
        start++
        end--
    }
    return true
}

fun twoSum(numbers: IntArray, target: Int): IntArray {
    var start = 0
    var end = numbers.size - 1
    while ( start < end){
        val currentElement = numbers[start] + numbers[end]
        if (currentElement == target)
            return intArrayOf(start + 1 , end + 1)
        else if (currentElement > target)
            end--
        else start++
    }
    return intArrayOf()
}

fun threeSum(nums: IntArray): List<List<Int>> {
    nums.sort()
    val ans = mutableListOf<List<Int>>()
    for (i in nums.indices){
        val current = nums[i]
        if (i > 0 && current == nums[i-1])
            continue
        var l = i +1
        var r = nums.size - 1
        while (l < r){
            when{
                nums[i] + nums[l] + nums[r] == 0 -> {
                    ans.add(mutableListOf(i, l, r))
                    l++
                    while (nums[l] == nums[l+1] && l < r)
                        l++
                }
                nums[i] + nums[l] + nums[r] < 0 -> {
                    l++
                }
                else -> r--
            }
        }
    }
    return ans
}

fun maxArea(height: IntArray): Int {
    var max = Int.MIN_VALUE
    var l = 0
    var r = height.size - 1
    while (l < r){
        val area = minOf(height[l], height[r]) * (r - l)
        max = maxOf(max, area)
        if (height[l] < height[r]) l++ else r--
    }
    return max
}

fun trap(height: IntArray): Int {
    val maxLeft = IntArray(height.size)
    val maxRight = IntArray(height.size)
    val ans = IntArray(height.size)

    for (i in height.indices){
       if (i == 0)
           maxLeft[i] = 0
        else maxLeft[i] = maxOf(maxLeft[i-1], height[i-1])
    }
    for (i in height.size -1 downTo 0){
        if (i == height.size-1)
            maxRight[i] = 0
        else maxRight[i] = maxOf(maxRight[i+1], height[i+1])
    }

    for (i in height.indices){
        val storage = minOf(maxRight[i], maxLeft[i]) - height[i]
        if (storage > 0)
            ans[i] = storage
    }

    return ans.sum()
}

fun maxProfit(prices: IntArray): Int {
    var left = 0
    var right = 1
    var max = Int.MIN_VALUE
    while (left < prices.size - 1){
        if (prices[right] - prices[right] <= 0){
            left = right
            right++
        }
        else {
            val sp = prices[right] - prices[left]
            max = maxOf(max, sp)
            right++
        }
    }
    return max
}