package org.nov

fun defangIPaddr(address: String): String {
    val sb = StringBuilder()
    for (char in address){
        if (char == '.')
            sb.append("[.]")
        else
            sb.append(char)
    }
    return sb.toString()
}

fun finalValueAfterOperations(operations: Array<String>): Int {
    var x = 0
    for (operation in operations){
        when(operation){
            "++X", "X++" -> x++
            "--X", "X--" -> x--
        }
    }
    return x
}

fun numJewelsInStones(jewels: String, stones: String): Int {
    var count = 0
    var jewelsArray = jewels.toSet()
    for (stone in stones){
        if (stone in jewelsArray)
            count++
    }
    return count
}

fun interpret(command: String): String {
    val output = StringBuilder()
    for (i in command.indices){
        when(command[i]){
            'G' -> output.append('G')
            '(' -> {
                if (command[i+1].equals('a'))
                    output.append("al")
                else
                    output.append('o')
            }
        }
    }
    return output.toString()
}

fun mostWordsFound(sentences: Array<String>): Int {
    var mostWords = Integer.MIN_VALUE
    for (sentence in sentences){
        val words = wordsInSentence(sentence)
        if (words > mostWords)
            mostWords = words
    }
    return mostWords
}
private fun wordsInSentence(sentence: String): Int {
    var words = 0
    for (c in sentence){
        when(c){
            ' ' -> words++
        }
    }
    return words + 1
}

fun restoreString(s: String, indices: IntArray): String {
    val output = CharArray(s.length)
    for (i in s.indices){
        output[indices[i]] = s[i]
    }
    return output.joinToString("")
}

fun countMatches(items: List<List<String>>, ruleKey: String, ruleValue: String): Int {
    var matches = 0
    for (item in items){
        when(ruleKey){
            "type" -> {
                if (item[0] == ruleValue) matches++
            }
            "color" ->{
                if (item[1] == ruleValue) matches++
            }
            "name" ->{
                if (item[2] == ruleValue) matches++
            }
        }
    }
    return matches
}

fun strStr(haystack: String, needle: String): Int {
    return haystack.indexOf(needle)
}

fun main() {
    val address = "192.168.0.1"
    val answer = defangIPaddr(address)
    print(answer)
}