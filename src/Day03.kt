fun main() {
    fun part1(input: List<String>): Int {

        // assign points to each index: 1 is 1 0 is -1
        val points = mutableListOf<Int>()

        var gamma = ""
        var epsilon = ""

        input.forEach {
            it.forEachIndexed {index, bit ->
                if( points.size == index ){
                    points.add(index, 0)
                }
                val frequency = points[index]
                points[index] = if( bit == '1') frequency + 1 else frequency - 1
            }
        }

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
       return 0
    }

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}
