package org.arrays

fun main() {
    val set = mutableSetOf<Int>()

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




