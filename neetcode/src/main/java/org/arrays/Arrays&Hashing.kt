package org.arrays

import kotlin.math.max

fun containsDuplicate(nums: IntArray): Boolean = nums.size > nums.toSet().size

fun isAnagram(s: String, t: String): Boolean {
    if (s.length != t.length)
        return false
    val list = MutableList(26){0}
    for (i in s.indices){
        val sPosition = s[i].toInt() - 97
        val tPosition = t[i].toInt() - 97
        list[sPosition]++
        list[tPosition]--
    }
    list.forEach {
        if (it != 0)
            return false
    }
    return true
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    for (i in nums.indices){
        if (map.containsKey(target - nums[i]))
            return intArrayOf(i, map[target - nums[i]]!!)
        map[nums[i]] = i
    }
    return intArrayOf()
}

fun topKFrequent(nums: IntArray, k: Int): IntArray {
    val map = mutableMapOf<Int, Int>()
    val ans = mutableListOf<Int>()
    val frequency = MutableList<MutableList<Int>>(nums.size + 1){
        mutableListOf()
    }

    for (num in nums){
        map.put(num, map.getOrDefault(num, 0) + 1)
    }

    for ((key, v) in map){
        frequency[v].add(key)
    }

    for (i in frequency.size - 1 downTo 0){
        for (j in frequency[i]){
            ans.add(j)
            if (ans.size == k)
                return ans.toIntArray()
        }
    }
    return intArrayOf()
}

fun groupAnagrams(strs: Array<String>): List<List<String>> {
    val map = mutableMapOf<String, List<String>>()
    val ans = mutableListOf<List<String>>()
    for (str in strs){
        val charArray = IntArray(26){0}
        for (c in str){
            charArray[c.toInt() - 97]++
        }
        val key = charArray.joinToString("")
        if (map.containsKey(key)){
            map[key] = map[key]!!.plus(str)
        }
        else{
            map[key] = listOf(str)
        }
    }
    for (v in map.values)
        ans.add(v)
    return ans
}

fun longestConsecutive(nums: IntArray): Int {
    val set = mutableSetOf<Int>()
    var max = Int.MIN_VALUE
    var count = 0
    for (num in nums){
        set.add(num)
    }
    for (num in set){
        if (! set.contains(num - 1)){
            var length = 0
            var seq = num
            while (set.contains(seq +1)){
                length++
                seq++
            }
            max = max(max, length)
        }
    }
    return max
}

fun isValidSudoku(board: Array<CharArray>): Boolean {
    val rowMap = hashMapOf<Int, MutableSet<Char>>()
    val colMap = hashMapOf<String, Set<Int>>()
    val gridMap = hashMapOf<String, Set<Int>>()

    // checking the rows
    for (ri in board.indices){
        val currRow = board[ri]
        val rowSet = hashSetOf<Char>()
        for (c in currRow.indices){
            if (currRow[c] == '.')
                continue
            if (rowSet.contains(currRow[c]))
                return false
            rowSet.add(currRow[c])
        }
    }

    // checking the columns
    for (r in board.indices){
        val colSet = hashSetOf<Char>()
        for (c in board[r].indices){
            if (board[c][r] == '.')
                continue
            if (colSet.contains(board[c][r]))
                return false
            colSet.add(board[c][r])
        }
    }

    for (r in 0..<9 step 3){
        for (c in 0 ..<9 step 3){
            if (!checkGrid(r, c, board))
                return false
        }
    }
    return true
}

fun checkGrid(row: Int, col: Int, board: Array<CharArray>): Boolean{
    val rowBound = row + 3
    val colBound = col + 3
    for (i in row..<rowBound){
        val gridSet = hashSetOf<Char>()
        for (j in col ..<colBound){
            if (board[i][j] == '.')
                continue
            if (gridSet.contains(board[i][j]))
                return false
        }
    }
    return true
}

fun main() {
    groupAnagrams(arrayOf("bdddddddddd","bbbbbbbbbbc"))
}