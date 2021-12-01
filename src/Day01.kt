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

        fun sum(input: List<Int>, start: Int, window: Int): Int{
            val end = (start + window - 1).coerceAtMost(input.size - 1)
            return input.slice(start..end).sum()
        }

        var sumIncreaseCount = 0
        var previousSum = Int.MAX_VALUE
        val measurements = input.map { it.toInt() }
        measurements.forEachIndexed {
                index, _ ->
            val sum = sum(measurements, index, 3)
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
