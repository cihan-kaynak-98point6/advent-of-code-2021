fun main() {
    fun part1(input: List<String>): Int {
        var depthIncreaseCount = 0
        var previous = Int.MAX_VALUE
        input.map { it.toInt() }.forEach {
            if(it > previous ){
                depthIncreaseCount++
            }
            previous = it
        }
        return depthIncreaseCount
    }

    fun part2(input: List<String>): Int {

        var sumIncreaseCount = 0
        var previousSum = Int.MAX_VALUE
        input.map { it.toInt() }.windowed(3, 1, true).forEach {
            val sum = it.sum()
            if( sum > previousSum ){
                sumIncreaseCount++
            }
            previousSum = sum
        }
        return sumIncreaseCount
    }

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
