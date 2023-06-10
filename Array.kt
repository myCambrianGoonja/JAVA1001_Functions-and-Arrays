fun main() {
  usersChoice()
}

fun usersChoice() {
  println("Bonjour please select your action option from the list below")
  println("1. You want to encode your text through Caesar Cipher Encoding")
  println("2. You want to average on your int array")
  println("3. You want to know wheater an input is contained in your int array")
  println("4. You want to reverse your integer array")

  var optionSelected: Int ? = readLine()?.toIntOrNull()
  var validInt = checkValidityInt(optionSelected)

  if (validInt) {
    usersChoiceResults(optionSelected)
  }
}

fun usersChoiceResults(selectedOption: Int ? ) {

  when(selectedOption) {
    1 -> {
      println("please enter the characters you want to encode")
      var originalCode = readLine()
      if (originalCode.isNullOrBlank() == false) {
        var encodedCharacters = caesarCipherEncoding(originalCode)
        for (letter in encodedCharacters) {
          print(letter)
        }
      } else {
        println("The value entred cannot be blank")
      }
    }
    2 -> {
      println("Pleaase enter the list of Int in the format 1,2,3,45,67")

      var intArray: Array < Int > ? = setupArrayFromString()
      var averageResult: Double ? = averagingArray(intArray)

      println("Average result of your entred array is: ${averageResult}")
    }
    3 -> {
      println("Pleaase enter the list of Int in the format 1,2,3,45,67")
      var intArray: Array < Int > ? = setupArrayFromString()

      if (intArray !== null && intArray.isNotEmpty()) {
        println(
          "Now that you have entered an array please enter another single integer you want to see exists in the array"
        )
        var containsValue: Int ? = readLine()?.toIntOrNull()
        var valid = checkValidityInt(containsValue)
        if (valid) {
          var doesContain = arrayContains(intArray, containsValue)
          if (doesContain)
            println("Yes value ${containsValue} does exisit in the array")
          else
            println("Sorry the value ${containsValue} does not exisit in the array")
        }
      }
    }
    4 -> {
      println("Enter the int array you want to reverse")
      var intArray: Array < Int > ? = setupArrayFromString()

      if (intArray !== null && intArray.isNotEmpty()) {
        var reverseArray = reverseArray(intArray)
        println("The reverse of the array you entered is ${reverseArray}")
      } else {
        println("Array value cannot be empty")
      }
    }
  }
}

fun setupArrayFromString(): Array < Int > ? {
  var inputString = readLine()
  var stringInt = inputString.toString().split(",")
  val intArray = mutableListOf < Int > ()

  for (i in stringInt) {
    val intValue = i?.toIntOrNull()

    if (intValue == null) {
      println("Please enter a valid value")
    } else {
      intArray.add(intValue)
    }
  }

  if (intArray.size == 0) {
    println("Please enter more than one value in the format 2,34,54,23")
    return null
  } else {
    return intArray.toTypedArray()
  }
}

fun checkValidityInt(value: Int ? ): Boolean {
  if (value == null || value >= 5) {
    println("Please enter a valid numeric value")
    return false
  } else {
    return true
  }
}

fun caesarCipherEncoding(normalString: String): Array < Char > {
  var encodedString: Array < Char > = arrayOf()
  val shift = 3
  val balancing = 26
  for (letter in normalString) {
    var l = letter
    // Condition to get back to A if it reaches X
    if (
      (letter.code >= 88 && letter.code <= 90) || (letter.code >= 120 && letter.code <= 122)
    ) {
      l = (letter.code - balancing).toChar()
    }
    encodedString = encodedString.plus((l.code + shift).toChar())
  }

  return encodedString
}

fun averagingArray(inputArray: Array < Int > ? ): Double ? {
  if (inputArray !== null && inputArray.isNotEmpty()) {
    var sum = 0.0

    for (number in inputArray) {
      sum += number
    }

    return sum / inputArray.size
  } else {
    return null
  }
}

fun arrayContains(inputArray: Array < Int > , valueToSearch: Int ? ): Boolean {
  for (number in inputArray) {
    if (number == valueToSearch) {
      return true
    }
  }
  return false
}

fun reverseArray(inputArray: Array < Int > ): String {
  var newArray: Array < Int > = arrayOf()
  for (i in inputArray.size - 1 downTo 0) {
    newArray = newArray.plus(inputArray[i])
  }
  return newArray.joinToString(",")
}