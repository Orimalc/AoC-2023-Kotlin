fun main() {

    val input = readInput("Day03")
    val test = readInput("Day03_test")

    val symbolChars = listOf('#', '*', '/', '\$', '@', '%', '=', '&', '+',
                            '-', '?', '!', '~', '§', '\"', ';', ',', ':', '\\',
                    '<', '>', '^', '\'',  '(', ')' , '|', '{', '}', '[' , ']', '_')

    fun getNumber(j: Int, len :Int, input: String) : Int{
        var number : Int = 0
        for (k in j..< j + len) {
            number = 10 * number + input[k].digitToInt()
        }
        return number

    }

    fun checkForSymbol(input : List<String>, i : Int , j : Int) : Pair<Boolean, Int>{
        var len : Int = 1
        while (len + j < input[i].length && input[i][j + len].isDigit()){
            len ++
        }
        var upper:Int= i
        var lower:Int= i
        var right:Int= j
        var left :Int= j
        if (upper > 0) upper -= 1
        if (lower < input.size - 1) lower += 1
        if (right < input[i].length - len ) right += len
        if (left > 0) left -= 1


        for(p in upper .. lower) {
           for(q in left ..right){

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
                toSkip.addAll(j + 1..<j+isPairNumber.second)
                if (isPairNumber.first) numberList.add(getNumber(j=j, len = isPairNumber.second, input = input[i]))
            }
            println(numberList)
        }
        return numberList
    }

    check(getAllPartNumbers(test).sum() == 4361)
    getAllPartNumbers(input).sum().println()
    // check(test == 2)
    // partOne()
    //
    // partTwo()
}