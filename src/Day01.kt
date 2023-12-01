fun main() {

    //created for part two
    fun getStringNumbers(input: String): String {
        var mutatedString: String = input
        val numbers = listOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
        val numberMap: Map<String, Int> = mapOf(
            "one" to 1, "two" to 2, "three" to 3, "four" to 4, "five" to 5,
            "six" to 6, "seven" to 7, "eight" to 8, "nine" to 9
        )

        val firstNumber = mutatedString.findAnyOf(numbers)

        val lastNumber = mutatedString.findLastAnyOf(numbers)

        // was important to first change the last number due to index changing length of mutatedString
        if (lastNumber != null) {
            mutatedString = mutatedString.replaceRange(
                lastNumber.first + 1, lastNumber.first + 1,
                numberMap[lastNumber.second].toString()
            )
        }

        if (firstNumber != null) {
            mutatedString = mutatedString.replaceFirst(
                firstNumber.second,
                numberMap[firstNumber.second].toString()
            )
        }

        return mutatedString
    }

    // created for part one, altered for part two
    fun getFirstAndLastDigit(string: String, isPartTwo: Boolean = false): String {
        var bufferString = string

        if (isPartTwo) {
            bufferString = getStringNumbers(bufferString)
        }

        bufferString = bufferString.replace(regex = "[a-zA-Z]".toRegex(), "")
        val first: Char = bufferString.first()
        val last: Char = bufferString.last()
        return first.toString().plus(last)
    }

    //created for part one
    fun getCalibrationDocumentSum(input: List<String>, isPartTwo: Boolean = false): Int {
        val digits: MutableList<Int> = mutableListOf<Int>()
        input.forEach {
            digits.add(getFirstAndLastDigit(it, isPartTwo).toInt())
        }
        return digits.sum()
    }

    //part one !
    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(getFirstAndLastDigit(testInput[0]) == "12")
    check(getFirstAndLastDigit(testInput[1]) == "38")
    check(getFirstAndLastDigit(testInput[2]) == "15")
    check(getFirstAndLastDigit(testInput[3]) == "77")
    check(getCalibrationDocumentSum(testInput) == 142)

    val input = readInput("Day01")
    // solution part one
    getCalibrationDocumentSum(input).println()

    //part two
    val testInput2 = readInput("Day01_test2")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[0])) == "29")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[1])) == "83")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[2])) == "13")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[3])) == "24")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[4])) == "42")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[5])) == "14")
    check(getFirstAndLastDigit(getStringNumbers(testInput2[6])) == "76")
    check(getCalibrationDocumentSum(testInput2, isPartTwo = true) == 281)

    getCalibrationDocumentSum(input, isPartTwo = true).println()
}
