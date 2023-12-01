fun main() {
    fun part1(input: List<String>): Int {
        return input.size
    }

    fun getFirstAndLastDigit(string: String): String {
        val bufferString = string.replace(regex = "[a-zA-Z]".toRegex(), "")
        val first: Char = bufferString.first()
        val last: Char = bufferString.last()
        return first.toString().plus(last)
    }

    fun getCalibrationDocumentSum(input: List<String>): Int{
        val digits : MutableList<Int> = mutableListOf<Int>()
        input.forEach {
           digits.add(getFirstAndLastDigit(it).toInt())
        }
        return digits.sum()
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 4)
    check(getFirstAndLastDigit(testInput[0]) == "12")
    check(getFirstAndLastDigit(testInput[1]) == "38")
    check(getFirstAndLastDigit(testInput[2]) == "15")
    check(getFirstAndLastDigit(testInput[3]) == "77")
    check(getCalibrationDocumentSum(testInput) == 142)

    val input = readInput("Day01")
    part1(input).println()
    getCalibrationDocumentSum(input).println()
}
