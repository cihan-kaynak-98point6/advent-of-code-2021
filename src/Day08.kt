class SegmentDisplay(private val testInputs: List<String>) {

    private val oneLength = 2
    private val fourLength = 4
    private val sevenLength = 3
    private val eightLength = 7

    fun countUniques(): Int {
        val uniqueLengths = listOf(oneLength, fourLength, sevenLength, eightLength)
        var count = 0
        for (testInput in testInputs) {
            val outputs = parse(testInput).second
            count += outputs.count { uniqueLengths.contains(it.length) }
        }
        return count
    }

    private fun parse(input: String): Pair<List<String>, List<String>> {
        val inputOutput = input.split("|")
        val inputs = inputOutput[0].split("\\s+".toRegex()).map { it.trim() }.filter { it.isNotBlank() }
        val outputs = inputOutput[1].split("\\s+".toRegex()).map { it.trim() }.filter { it.isNotBlank() }

        return Pair(inputs, outputs)
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return SegmentDisplay(input).countUniques()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
