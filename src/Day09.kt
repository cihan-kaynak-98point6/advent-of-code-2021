open class HeightMap(input: List<String>) {

    protected val points: List<List<Int>> = input.map { it.map { digit -> digit.digitToInt() } }

    fun findLowPoints(): List<Int> {

        val lowPoints = mutableListOf<Int>()
        for (row in points.indices) {
            for (col in points[row].indices) {
                val point = points[row][col]
                val adjecentLocations = mutableListOf<Int>()

                // left
                if (col - 1 in points[row].indices) {
                    adjecentLocations.add(points[row][col - 1])
                }

                // right
                if (col + 1 in points[row].indices) {
                    adjecentLocations.add(points[row][col + 1])
                }

                // top
                if (row - 1 in points.indices) {
                    adjecentLocations.add(points[row - 1][col])
                }

                // bottom
                if (row + 1 in points.indices) {
                    adjecentLocations.add(points[row + 1][col])
                }

                if (adjecentLocations.all { it > point }) {
                    println("Row:$row Column:$col Point:$point")
                    lowPoints.add(point)
                }

            }
        }

        return lowPoints
    }

    fun sumLowPoints(): Int {
        return findLowPoints().sumOf { it + 1 }
    }

}


fun main() {
    fun part1(input: List<String>): Int {
        return HeightMap(input).sumLowPoints()
    }

    fun part2(input: List<String>): Int {
        return 0
    }

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
