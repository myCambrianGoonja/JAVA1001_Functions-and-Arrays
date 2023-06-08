fun main() {
    var arrayValue: Array<Int> = arrayOf(1,2,3,4,5)
    val containsValue = arrayContains(arrayValue, 4)
    println("${containsValue} DOES contain")

    val reversedArray = reverseArray(arrayValue)
    println("${reversedArray.asList()} Reverse is")
}

fun averagingArray(inputArray: Array<Int>): Double {
    var sum = 0.0

    for(number in inputArray) {
        sum += number
    }

    return sum/inputArray.size
}

fun arrayContains(inputArray: Array<Int>, valueToSearch: Int): Boolean {
    for(number in inputArray) {
        if(number == valueToSearch) {
            return true
        } 
    }
        return false
}

fun reverseArray(inputArray: Array<Int>): Array<Int> {
    var newArray: Array<Int> = arrayOf()
    for (i in inputArray.size - 1 downTo 0) {
        newArray = newArray.plus(inputArray[i])
    }
    return newArray
}
