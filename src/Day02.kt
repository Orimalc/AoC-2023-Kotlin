import kotlin.math.max

fun main() {

    val partOne: Array<Int> = arrayOf(12, 13, 14) // r, g, b
    val testInput = readInput("Day02_test")
    val input = readInput("Day02")

    fun preprocessString(input: String): String {
        return input.replace(" ", "").replace("""Game\d{1,3}:""".toRegex(), "").replace(";", "").replace(",", "")
            .replace("red", "r").replace("green", "g").replace("blue", "b")
    }

    fun getRGBValue(input: String): Array<Int> {
        val maxValues = arrayOf(0, 0, 0)
        var currentValue = 0
        for (i in 0..input.length - 1) {
            when (input[i]) {
                'r' -> {
                    maxValues[0] = max(maxValues[0], currentValue)
                    currentValue = 0
                }

                'g' -> {
                    maxValues[1] = max(maxValues[1], currentValue)
                    currentValue = 0
                }

                'b' -> {
                    maxValues[2] = max(maxValues[2], currentValue)
                    currentValue = 0
                }

                else -> if (input[i].isDigit()) {
                    if (currentValue > 0) {
                        currentValue *= 10
                    }
                    currentValue += input[i].digitToInt()
                }
            }

        }
        return maxValues
    }

    fun compareToRules(maxValues: Array<Int>): Boolean {
        return maxValues[0] <= partOne[0] && maxValues[1] <= partOne[1] && maxValues[2] <= partOne[2]
    }

    fun getPowerOfCubes(array: Array<Int>): Int {
        return array[0] * array[1] * array[2]
    }


    fun partOne() {

        getRGBValue(preprocessString(testInput[0])).contentDeepToString()
        check(testInput.sumOf {
            if (compareToRules(getRGBValue(preprocessString(it)))) {
                testInput.indexOf(it) + 1
            } else 0
        } == 8)

        input.sumOf {
            if (compareToRules(getRGBValue(preprocessString(it)))) {
                input.indexOf(it) + 1
            } else 0
        }.println()
    }

    fun partTwo() {
        check(testInput.sumOf { getPowerOfCubes(getRGBValue(preprocessString(it))) } == 2286)

        input.sumOf { getPowerOfCubes(getRGBValue(preprocessString(it))) }.println()
    }
    partOne()
    partTwo()


}