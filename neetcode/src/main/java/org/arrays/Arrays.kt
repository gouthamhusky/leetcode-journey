package org.arrays

import kotlin.text.StringBuilder

fun main() {
    val nums = intArrayOf(1, 2, 3, 4)
    val answer = productExceptSelf(nums)
    print(answer.contentToString())
}


/*
https://leetcode.com/problems/contains-duplicate/description/
Clever way: nums.size == nums.toSet().size (size of array equals size of its converted set)
 */
fun containsDuplicate(nums: IntArray): Boolean {
    val set = mutableSetOf<Int>()
    for (num in nums){
        if (set.contains(num))
            return false
        set.add(num)
    }
    return true
}

/*
https://leetcode.com/problems/valid-anagram/description/
 */
fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length) return false

    val array = IntArray(26) {0}
    for (i in s.indices){
        val posForS = s[i].code - 97
        val posForT = t[i].code - 97
        array[posForS]++
        array[posForT]--
    }

    for (i in array){
        if(i != 0)
            return false
    }
    return true
}

/*
https://leetcode.com/problems/two-sum/
 */
fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices){
        if (map.contains(target - nums[i]))
            return intArrayOf(map[target - nums[i]]!!, i)
        map.put(nums[i], i)
    }
    return intArrayOf()
}

// https://leetcode.com/problems/group-anagrams/
fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val ans = mutableListOf<List<String>>()
    val map = mutableMapOf<String, MutableList<String>>()

    for (s in strs){
        val count = IntArray(26) {0}
        for (c in s.toCharArray()){
            count[c.code - 97]++
        }
        if (map.containsKey(count.joinToString()))
            map.get(count.joinToString())?.add(s)
        else
            map.put(count.joinToString(), mutableListOf(s))
    }

    for (v in map.values)
        ans.add(v)

    return ans
}


// https://leetcode.com/problems/top-k-frequent-elements/description/
fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val res = mutableListOf<Int>()
    val map: HashMap<Int, Int> = hashMapOf()
    val frequency = MutableList<MutableList<Int>>(nums.size + 1){
        mutableListOf()
    }

    for (num in nums){
        map.put(num, map.getOrDefault(num, 0) + 1)
    }

    for ((key, v) in map){
        frequency[v].add(key)
    }

    for (i in frequency.size -1 downTo 0){
        for (n in frequency[i]){
            res.add(n)
            if (res.size == k)
                return res.toIntArray()
        }
    }

    return intArrayOf()
}

// https://leetcode.com/problems/product-of-array-except-self/
fun productExceptSelf(nums: IntArray): IntArray {
    // create a new array called productSoFar
    val prefix = IntArray(nums.size)
    var prodSoFar = 1
    for (i in nums.indices){
        prodSoFar *= nums[i]
        prefix[i] = prodSoFar
    }

    prodSoFar = 1
    val postfix = IntArray(nums.size)
    for (i in nums.size-1 downTo 0){
        prodSoFar *= nums[i]
        postfix[i] = prodSoFar
    }

    var answer = IntArray(nums.size)
    for (i in 1..nums.size-2){
        answer[i] = prefix[i-1] * postfix[i + 1]
    }
    answer[0] = postfix[1]
    answer[nums.size - 1] = prefix[nums.size - 2]

    return  answer
}

class Codec {
    // Encodes a list of strings to a single string.
    fun encode(strs: List<String>): String {
        val encodedString = StringBuilder()
        for(str in strs){
            val len = str.length
            encodedString.append(len).append('#').append(str)
        }
        return encodedString.toString()
    }

    // Decodes a single string to a list of strings.
    fun decode(s: String): List<String> {
        val decodedList = mutableListOf<String>()
        var i = 0
        while (i < s.length){
            var j = i
            while (s[j] != '#'){
                j++
            }
            val length = s.substring(i, j + 1).toInt()
            i = j + 1 + length
            decodedList.add(s.substring(j +1, i))
        }
        return decodedList
    }

    // https://leetcode.com/problems/longest-consecutive-sequence/
    fun longestConsecutive(nums: IntArray): Int {
        val inputSet = nums.toSet()
        var longestSeq = 0
        for (num in inputSet){
            if (!inputSet.contains(num - 1)){
                var currentSeq = 1
                var seq = num
                while (inputSet.contains(seq + 1)){
                    currentSeq++
                    seq += 1
                }
                longestSeq = maxOf(longestSeq, currentSeq)
            }

        }
        return longestSeq
    }
}