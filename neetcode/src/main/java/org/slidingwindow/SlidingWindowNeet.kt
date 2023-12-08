package org.slidingwindow

import kotlin.math.abs


// https://leetcode.com/problems/minimum-size-subarray-sum/
fun minSubArrayLen(target: Int, nums: IntArray): Int {
    var i = 0; var j = 0; var sum = 0; var min = Int.MAX_VALUE
    while (j < nums.size){
        sum += nums[j]
        if (sum >= target){
            while (sum >= target){
                min = Math.min(min, j - i + 1)
                sum -= nums[i]
                i++
            }
        }
        j++
    }
    return  if (min == Int.MAX_VALUE) 0 else min
}

fun findClosestElements(arr: IntArray, k: Int, x: Int): List<Int> {
    var i = 0; var j =  0
    // create map with key as diff and value as list of elements
    val map = sortedMapOf<Int, MutableList<Int>>()
    while(j < arr.size){
        val diff = Math.abs(arr[j] - x)
        if (map.containsKey(diff)){
            map[diff]?.add(arr[j])
        }
        else{
            map.put(diff, mutableListOf(arr[j]))
        }
        j++
    }
    var rem = k
    val ans = mutableListOf<Int>()
    for (key in map.keys){
        while (rem > 0){
            map[key]?.let { it ->
                if (it.size > 1){
                    ans.add(it.removeAt(0))
                    rem--
                }
            }
        }
    }

    return ans.sorted()
}
fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
    var i = 0; var j = 0
    val set = mutableSetOf<Int>()
    while (j < nums.size){
        if (set.contains(nums[j]))
            return true
        set.add(nums[j])
        if (j - i + 1> k){
            set.remove(nums[i])
            i++
        }
        j++
    }
    return false
}



fun main() {
    print(containsNearbyDuplicate(intArrayOf(1,2,3,1), 3))
}