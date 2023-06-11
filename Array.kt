import java.util.Stack
/*
    main() is calling the usersChoice() -> gives user the 
    option to choose which operation they want to perform
 */
fun main() {
  usersChoice()
}

/*
    userChoice() -> lets the user select from encoding 
    averaging, testing contains and reversing an intiger array
    once the value is validated with checkValidityInt() method 
    the option is passed to the usersChoiceResults() method
 */
fun usersChoice() {
  println("Bonjour please select your action option from the list below")
  println("1. You want to encode your text through Caesar Cipher Encoding")
  println("2. You want to average on your int array")
  println("3. You want to know wheater an input is contained in your int array")
  println("4. You want to reverse your integer array")

  var optionSelected: Int ? = readLine()?.toIntOrNull()
  var validInt = checkValidityInt(optionSelected)

  if (validInt && optionSelected <= 4) {
    usersChoiceResults(optionSelected)
  } else {
    println("Please select a valid option")
  }
}

/*
    usersChoiceResults() -> in this method we execute the operation selected by the user
    and it is been distributed with switch statement
    1: -> the validity of the string entered is checked and send to the caesarCipherEncoding()
    2: -> the validity of the int array entered is checked and send to the averagingArray() 
    the results are then printed. 
    3: -> the validity of the int array and the value to test it containes is checked and then 
    sent to the arrayContains()
    4: -> the validity of the int array entered and then sent to the 
    reverseArray() 

    setupArrayFromString() -> converts the intArray into an 
    int array 

    averagingArray() -> used for averaging an integer array

    @param SelectOptions = has the option selected by the user

 */
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
      var intStack: Stack < Int > ? = setUpStackFromString()

      if (intStack !== null && intStack.isNotEmpty() && intStack.size > 1) {
        var newStack: Stack < Int > = intStack;
        var reverseArray = reverseArray(newStack)
        println("The reverse of the array you entered is ${reverseArray}")
      } else {
        println("Array value cannot be empty or have just one value")
      }
    }
  }
}

/*
    This method takes the user input in int1,int2,int3,int4 format
    and converts into an interger Array

    @return Array<Int> - converted string to Int array
 */
fun setupArrayFromString(): Array < Int > ? {
  var inputString = readLine()
  var stringInt = inputString.toString().split(",")
  val intArray = mutableListOf < Int > ()

  for (i in stringInt) {
    val intValue = i.toIntOrNull()

    if (intValue == null) {
      println("Please enter a valid value")
    } else {
      intArray.add(intValue)
    }
  }
  //IF USER HAS ENTRED ONLY ONE VALUE THEN IT SHOWS THIS ERROR
  if (intArray.size == 0) {
    println("Please enter more than one value in the format 2,34,54,23")
    return null
  } else {
    return intArray.toTypedArray()
  }
}

/*
    This method takes the user input in int1,int2,int3,int4 format
    and converts into an interger Stack

   @return Stack<Int> - converted string to Stack array
 */
fun setUpStackFromString(): Stack < Int > ? {
  var inputString = readLine()
  var stringInt = inputString.toString().split(",")
  var intStack: Stack < Int > = Stack < Int > ()
  for (string in stringInt) {
    val intValue: Int ? = string.toIntOrNull()
    if (intValue != null) {
      intStack.push(intValue)
    } else {
      println("Invalid integer value: $string")
    }
  }

  return intStack
}

/*
    Whenever the user inters a single interger value its 
    validity is checked in this method

    @param value = containes the single integer 
    value that needs to be validated
    @return Boolean = validity results 
 */
fun checkValidityInt(value: Int ? ): Boolean {
  if (value == null) {
    println("Please enter a valid numeric value")
    return false
  } else {
    return true
  }
}

/*
    This is a method that implemets the encoding method 
    first invented by Caesar Cipher, 
    in this method we use 
    
    * shift = to shift the value of the entred character 
    from 3 digits, ie, if the value is A it will be converted to 
    D , B -> F etc 
    
    * balancing = if the value entered by the user contains 'X', whose code is 88 + 3 = 91,
    which is represented by '['. However, in the original encoding method, once it reaches 'X',
    it is brought back to 'A' by subtracting the code by 26 (because there are 26 characters),
    resulting in 'X' mapping to 'A', 'Y' mapping to 'B', 'Z' mapping to 'C', and so on.
    The if condition checks for the character codes between 88 to 90 for 'X' and 120 to 122 for 'x'.

   @param  normalString = containes the String that needs to be changed
   @return Array<Char> - containes the encoded string
 */
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

/*
    averagingArray() does the average of the array entered

    @param inputArray = takes the array that needs to be averaged
    @return Double = returned the averaged value
 */
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

/*
    arrayContains() -> method to check whether the value entred
    exists in an array. 

    @param inputArray = containes the array entred by the user
    @param valueToSearch = containes the value that needs to be 
    checked in the array

    @return Boolean = if the value exists then we return true if not we return false
 */
fun arrayContains(inputArray: Array < Int > , valueToSearch: Int ? ): Boolean {
  for (number in inputArray) {
    if (number == valueToSearch) {
      return true
    }
  }
  return false
}

/*
    reverseArray() -> method to reverse the Stack of Integeres entred by the user

    @param inputStack = array entred by the user  

    @return String = string that is seperates each integer by a 
    ',' so that there are no issues in printing the results to 
    the user.
 */
fun reverseArray(inputStack: Stack < Int > ): String {
  var newArray: Array < Int > = arrayOf()
  while (!inputStack.isEmpty()) {
    newArray = newArray.plus(inputStack.pop())
  }
  return newArray.joinToString(",")
}