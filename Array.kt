fun main() {
    var arrayValue: Array<Int> = arrayOf(1,2,3,4,5)
   print(averagingArray(arrayValue))
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
        return false
    }
}