class Points(input: List<String>){
    // assign points to each index: 1 is 1 0 is -1
    val points = mutableListOf<Int>()
    init {
        input.forEach {
            it.forEachIndexed {index, bit ->
                if( points.size == index ){
                    points.add(index, 0)
                }
                val frequency = points[index]
                points[index] = if( bit == '1') frequency + 1 else frequency - 1
            }
        }
    }
}

abstract class LifeSupportRating(private val input: List<String>){

    fun getRating(): String{

        val mutableInput = input.toMutableList()
        var pointIndex = 0
        while( mutableInput.size > 1){
            val points = Points(mutableInput).points
            val bitToRemove = getBitToRemove(points[pointIndex])
            mutableInput.removeIf { it[pointIndex] == bitToRemove }
            pointIndex++
        }
        return mutableInput[0]
    }

    abstract fun getBitToRemove(points: Int): Char
}

class OxygenRating(input: List<String>): LifeSupportRating(input){

    override fun getBitToRemove(points: Int): Char {
        return if( points >= 0 ){
            '0'
        }else{
            '1'
        }
    }
}

class CO2Rating(input: List<String>): LifeSupportRating(input){

    override fun getBitToRemove(points: Int): Char {
        return if( points >= 0 ){
            '1'
        }else{
            '0'
        }
    }
}


fun main() {
    fun part1(input: List<String>): Int {

        val points = Points(input).points
        var gamma = ""
        var epsilon = ""

        points.forEach {
            if( it > 0 ){
                gamma += "1"
                epsilon += "0"
            }else{
                gamma += "0"
                epsilon += "1"
            }
        }

        return gamma.toInt(2) * epsilon.toInt(2)
    }

    fun part2(input: List<String>): Int {

        val oxygenRating = OxygenRating(input)
        val co2Rating = CO2Rating(input)
        return oxygenRating.getRating().toInt(2) * co2Rating.getRating().toInt(2)
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
