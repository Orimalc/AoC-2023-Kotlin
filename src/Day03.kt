fun main() {

    val input = readInput("Day03")
    val test = readInput("Day03_test")

    val symbolChars = listOf(
        '#', '*', '/', '\$', '@', '%', '=', '&', '+',
        '-', '?', '!', '~', '§', '\"', ';', ',', ':', '\\',
        '<', '>', '^', '\'', '(', ')', '|', '{', '}', '[', ']', '_'
    )

    fun getNumber(j: Int, len: Int, input: String): Int {
        var number: Int = 0
        for (k in j..<j + len) {
            number = 10 * number + input[k].digitToInt()
        }
        return number

    }

    fun checkForSymbol(input: List<String>, i: Int, j: Int): Pair<Boolean, Int> {
        var len: Int = 1
        while (len + j < input[i].length && input[i][j + len].isDigit()) {
            len++
        }
        var upper: Int = i
        var lower: Int = i
        var right: Int = j
        var left: Int = j
        if (upper > 0) upper -= 1
        if (lower < input.size - 1) lower += 1
        if (right < input[i].length - len) right += len
        if (left > 0) left -= 1


        for (p in upper..lower) {
            for (q in left..right) {

                if (symbolChars.contains(input[p][q])) return Pair(true, len)
            }
        }
        return Pair(false, len)
    }

    fun getAllPartNumbers(input: List<String>): List<Int> {
        val numberList = mutableListOf<Int>()

        for (i in input.indices) {
            val toSkip = mutableListOf<Int>()
            for (j in 0..<input[0].length) {

                //skip this character if it is not a digit
                if (toSkip.contains(j) || !input[i][j].isDigit()) {
                    toSkip.removeFirstOrNull()
                    continue
                }
                val isPairNumber = checkForSymbol(input, i, j)
                toSkip.addAll(j + 1..<j + isPairNumber.second)
                if (isPairNumber.first) numberList.add(getNumber(j = j, len = isPairNumber.second, input = input[i]))
            }
        }
        return numberList
    }

    fun partOne() {
        check(getAllPartNumbers(test).sum() == 4361)
        getAllPartNumbers(input).sum().println()
    }


    fun getGearRatio(starCoordinate: Pair<Int, Int>, input: List<String>): Int {

        //find second number if it exits
        var upper: Int = starCoordinate.first
        var lower: Int = starCoordinate.first
        var right: Int = starCoordinate.second
        var left: Int = starCoordinate.second
        if (upper > 0) upper -= 1
        if (lower < input.size - 1) lower += 1
        if (right < input[starCoordinate.first].length - 1) right += 1
        if (left > 0) left -= 1

        val numberCoord = mutableListOf<Pair<Int, Int>>()

        for (p in upper..lower) {
            for (q in left..right) {

                if (input[p][q].isDigit()) {

                    // to not add the same number multiple times
                    if (q > left && input[p][q - 1].isDigit()) {
                        continue
                    }
                    numberCoord.add(Pair(p, q))
                }
            }
        }
        val numbers = mutableListOf<Int>()
        for (co in numberCoord) {

            var numberStart = co.second
            var numberEnd = co.second
            while (numberStart > 0 && input[co.first][numberStart - 1].isDigit()) {
                numberStart -= 1
            }
            while (numberEnd < input[0].length - 1 && input[co.first][numberEnd + 1].isDigit()) {
                numberEnd += 1
            }

            numbers.add(
                input[co.first].subSequence(startIndex = numberStart, endIndex = numberEnd + 1)
                    .toString()
                    .toInt()
            )
        }
        var gearRatio: Int = 0
        if (numbers.size > 1) gearRatio = 1
        for (num in numbers) gearRatio *= num

        return gearRatio
    }

    fun partTwo(input: List<String>): Int {
        val starCoord = mutableListOf<Pair<Int, Int>>()
        for (i in input.indices) {
            for (j in 0..<input[i].length)
                if (input[i][j] == '*') starCoord.add(Pair(i,j))
        }
        val gearRatios = mutableListOf<Int>()
        for (star in starCoord) {
            gearRatios.add(getGearRatio(star, input))
        }
        return gearRatios.sum()
    }
    partTwo(test).println()
    check(partTwo(test) == 467835)
    //partOne()
    partTwo(input).println()
}