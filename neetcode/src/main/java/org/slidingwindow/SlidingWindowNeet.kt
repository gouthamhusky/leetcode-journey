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

fun checkInclusion(s1: String, s2: String): Boolean {
    val mapForS1 = hashMapOf<Char, Int>()
    val mapForS2 = hashMapOf<Char, Int>()
    val k = s1.length
    for (c in s1.toCharArray())
        mapForS1[c] = mapForS1.getOrDefault(c, 0) + 1

    var count = mapForS1.size
    var i = 0
    var j = 0

    while (j < s2.length){
        val current = s2[j]
        mapForS2.put(current, mapForS2.getOrDefault(current, 0) + 1)

        if (j -i + 1 < k)
            j++

        else if (j - i + 1 == k){
            if(compareMaps(mapForS1, mapForS2))
                return true
            else{
                mapForS2.put(s2[i], mapForS2.get(s2[i])!! - 1)
                if(mapForS2.get(s2[i]) == 0)
                    mapForS2.remove(s2[i])
                i++
                j++
            }
        }
    }
    return false
}

private fun compareMaps(map1 : Map<Char, Int>, map2: Map<Char, Int>): Boolean {
    for (key in map1.keys){
        if (! (map2.containsKey(key) && map2.get(key) == map1.get(key)))
            return false
    }
    return true
}



fun main() {
    checkInclusion("adc", "dcda")
}