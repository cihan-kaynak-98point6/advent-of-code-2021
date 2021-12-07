import kotlin.math.abs

open class FuelCalculator(input: List<String>) {

    protected val points: Map<Int, Int> = input[0].split(",").map { it.trim().toInt() }.groupingBy { it }.eachCount()

    fun min(): Int {
        var min = Int.MAX_VALUE
        for (point in getPoints()) {
            var fuel = 0
            for (otherPoint in points) {
                fuel += distance(point, otherPoint.key) * otherPoint.value
            }
            if (fuel < min) {
                min = fuel
            }
        }
        return min
    }

    protected open fun getPoints(): Collection<Int> {
        return points.keys
    }

    protected open fun distance(point1: Int, point2: Int): Int {
        return abs(point1 - point2)
    }
}

class VariableRateFuelCalculator(input: List<String>) : FuelCalculator(input) {

    override fun getPoints(): Collection<Int> {
        return points.keys.minOf { it }.rangeTo(points.keys.maxOf { it }).toList()
    }

    override fun distance(point1: Int, point2: Int): Int {
        val distance = super.distance(point1, point2)
        return (distance * (distance + 1)) / 2
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        return FuelCalculator(input).min()
    }

    fun part2(input: List<String>): Int {
        return VariableRateFuelCalculator(input).min()
    }

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
