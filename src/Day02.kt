import kotlin.math.max

fun main(){

    val partOne: Array<Int> = arrayOf(12, 13, 14) // r, g, b

    fun preprocessString(input : String) : String{
        return input.replace(" ", "")
            .replace("""Game\d{1,3}:""".toRegex(), "")
            .replace(";", "")
            .replace(",", "")
            .replace("red", "r")
            .replace("green", "g")
            .replace("blue", "b")
    }

    fun getRGBValue(input:String) :Array<Int>{
        val maxValues = arrayOf(0, 0 , 0)
        var currentValue = 0
        for (i in 0..input.length - 1) {
            when (input[i]) {
                'r' -> {maxValues[0] = max(maxValues[0], currentValue)
                        currentValue = 0}
                'g' -> {maxValues[1] = max(maxValues[1], currentValue)
                    currentValue = 0}
                'b' -> {maxValues[2] = max(maxValues[2], currentValue)
                    currentValue = 0}
                else -> if(input[i].isDigit()) {
                    if (currentValue > 0) {
                        currentValue *= 10
                        }
                        currentValue += input[i].digitToInt()
                }
            }

        }
        return maxValues
    }

    fun compareToRules(maxValues : Array<Int>) : Boolean {
        return maxValues[0] <= partOne[0] && maxValues[1] <= partOne[1]
                && maxValues[2] <= partOne[2]
    }



    fun partOne(){
        val test_input = readInput("Day02_test")
        val input = readInput("Day02")
        getRGBValue(preprocessString(test_input[0])).contentDeepToString()
        test_input.sumOf{
           if ( compareToRules(getRGBValue(preprocessString(it))) )
                { test_input.indexOf(it) + 1}
           else 0}.println()

        input.sumOf{
            if ( compareToRules(getRGBValue(preprocessString(it))) )
            { input.indexOf(it) + 1}
            else 0}.println()
    }

    partOne()














}