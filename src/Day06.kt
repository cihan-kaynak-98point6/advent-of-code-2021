class Simulator(private val input: List<String>) {

    fun simulate(days: Int): Long {
        var fish =
            input[0].split(",").map { it.trim() }.groupingBy { it.toInt() }.eachCount().mapValues { it.value.toLong() }
        for (day in 1..days) {
            val newFish = mutableMapOf<Int, Long>()
            fish.forEach {
                if (it.key == 0) {
                    newFish[8] = it.value
                    newFish[6] = newFish.getOrDefault(6, 0) + it.value
                } else {
                    newFish[it.key - 1] = newFish.getOrDefault(it.key - 1, 0) + it.value
                }
            }
            fish = newFish
            println("After day ${day}: " + fish.values.sum())
        }
        return fish.values.sum()
    }
}

fun main() {
    fun part1(input: List<String>): Long {
        return Simulator(input).simulate(80)
    }

    fun part2(input: List<String>): Long {
        return Simulator(input).simulate(256)
    }

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
