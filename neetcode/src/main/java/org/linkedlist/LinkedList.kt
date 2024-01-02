package org.linkedlist
class ListNode(var `val`: Int) {
     var next: ListNode? = null
}

fun deleteDuplicates(head: ListNode?): ListNode? {
    var node = head ?: return null
    while (node!!.next != null){
        if(node.`val` == node.next!!.`val`)
            node.next = node.next!!.next
        else
            node = node.next!!
    }
    return head
}

fun mergeTwoLists(list1: ListNode?, list2: ListNode?): ListNode? {
    var f = list1
    var s = list2
    var tail: ListNode? = null
    var mergedLL: ListNode? = null
    while (f != null && s != null){
        if (f.`val` < s.`val`){
            if (mergedLL == null){
                mergedLL = ListNode(f.`val`)
                tail = mergedLL
            }
            else{
                tail!!.next = ListNode(f.`val`)
                tail = tail.next
            }
            f = f.next
        }
        else{
            if (mergedLL == null){
                mergedLL = ListNode(s.`val`)
                tail = mergedLL
            }
            else{
                tail!!.next = ListNode(s.`val`)
                tail = tail.next
            }
            s = s.next
        }
    }
    if (f != null){
        while (f != null){
            if(tail == null){
                mergedLL = ListNode(f.`val`)
                tail = mergedLL
            }
            else{
                tail.next = ListNode(f.`val`)
                tail = tail.next
            }
            f = f.next
        }
    }
    if (s != null){
        while (s != null){
            if(tail == null){
                mergedLL = ListNode(s.`val`)
                tail = mergedLL
            }
            else{
                tail.next = ListNode(s.`val`)
                tail = tail.next
            }
            s = s.next
        }
    }
    return mergedLL
}

/* https://leetcode.com/problems/linked-list-cycle/
 */
fun hasCycle(head: ListNode?): Boolean {
    var fast = head
    var slow = head
    while (fast?.next != null){
        slow = slow!!.next
        fast = fast.next?.next
        if (fast == slow)
            return true
    }
    return false
}


// find length of cycle
fun lengthCycle(head: ListNode?): Int {
    var fast = head
    var slow = head
    while (fast?.next != null){
        slow = slow!!.next
        fast = fast.next?.next
        if (fast == slow){
            var length = 0
            do {
                length++
                slow = slow?.next
            }while (slow != fast)
        }
    }
    return 0
}

// https://leetcode.com/problems/linked-list-cycle-ii/
fun detectCycle(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    var length = 0
    while (fast?.next != null){
        slow = slow?.next
        fast = fast.next!!.next
        if (fast == slow){
            do {
                length++
                fast = fast?.next
            }while (fast != slow)
            break;
        }
    }

    if (length == 0)
        return null

    var first = head
    var second = head

    while (length > 0){
        second = second?.next
        length--
    }

    while (first != second){
        first = first?.next
        second = second?.next
    }

    return second
}

fun isHappy(n: Int): Boolean {
    var slow = n
    var fast = n
    do {
        slow = findSquare(slow)
        fast = findSquare(findSquare(fast))
    }while (fast != slow)

    return slow == 1
}

private fun findSquare(n: Int): Int{
    var ans = 0
    var num = n
    while  (num > 0){
        var rem = num % 10
        ans += rem * rem
        num /= 10
    }
    return ans
}

fun middleNode(head: ListNode?): ListNode? {
    var fast = head
    var slow = head
    while (fast?.next != null){
        slow = slow?.next
        fast = fast.next?.next
    }
    return slow
}

/*
https://leetcode.com/problems/reverse-linked-list/
in reversal
 */
fun reverseList(head: ListNode?): ListNode? {
    var prev: ListNode? = null
    var pres = head
    var next = head?.next
    while (pres != null){
        pres.next = prev
        prev = pres
        pres = next
        next = next?.next
    }
    return prev
}

// https://leetcode.com/problems/reverse-linked-list-ii/
fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
    var h = head
    if (left == right)
        return head

    // skip the first left - 1 nodes
    var current = head
    var prev: ListNode? = null
    for (i in 0..<left - 1){
        prev = current
        current = current?.next
    }

    val last = prev
    val newEnd = current

    // reverse between left and right
    var next = current?.next
    for (i in 0 ..< right - left +1){
        current?.next = prev
        prev = current
        current = next
        next = next?.next
    }

    if (last != null)
        last.next = prev
    else
        h = prev

    newEnd?.next = current
    return h
}

fun isPalindrome(head: ListNode?): Boolean {

    var node = head
    // find middle
    var mid = middleNode(head)

    // reverse second half
    var secondHead = reverseList(mid)
    var reReverseHead = secondHead

    // compare
    while (node != null && secondHead != null){
        if (node.`val` != secondHead.`val`)
            break

        node = node.next
        secondHead = secondHead.next
    }

    // re-reverse
    reverseList(reReverseHead)

    return node == null || secondHead == null
}

fun reorderList(head: ListNode?): Unit {
    if(head?.next == null)
        return

    val mid = middleNode(head)
    var hs = reverseList(mid)
    var hf = head

    while (hf != null && hs != null){
        var temp = hf.next
        hf.next = hs
        hf = temp

        temp = hs.next
        hs.next = hf
        hs = temp
    }

    if (hf != null)
        hf.next = null
}
