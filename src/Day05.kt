import java.util.function.BiPredicate
import java.util.function.Predicate

data class Point(val x: Int, val y: Int){
    companion object Factory{
        fun createPoints(input: String, pointPredicate: BiPredicate<Point, Point>): List<Point>{
            val points = mutableListOf<Point>()

            val range = input.split("->").map {
                val coordinates = it.trim().split(",")
                Point(coordinates[0].toInt(), coordinates[1].toInt())
            }

            val start = range[0]
            val end = range[1]

            if( !pointPredicate.test(start, end) ){
                return emptyList()
            }

            val xRange = if(start.x < end.x) start.x.rangeTo(end.x) else start.x.downTo(end.x)
            val yRange = if(start.y < end.y) start.y.rangeTo(end.y) else start.y.downTo(end.y)

            if( start.x == end.x || start.y == end.y){
                // horizontal and vertical
                for ( x in xRange ){
                    for( y in yRange ){
                        points.add( Point(x, y))
                    }
                }
            }else{
                // diagonal
                val xIt = xRange.iterator()
                val yIt = yRange.iterator()
                while (xIt.hasNext() && yIt.hasNext()){
                    points.add( Point(xIt.next(), yIt.next()))
                }
            }

            return points
        }
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        val points = input.map { Point.createPoints(it) { start, end -> start.x == end.x || start.y == end.y } }.flatten()
        val pointGrouping = points.groupingBy { it }.eachCount()
        return pointGrouping.values.count { it >=2 }
    }

    fun part2(input: List<String>): Int {
        val points = input.map { Point.createPoints(it) { _, _ -> true } }.flatten()
        val pointGrouping = points.groupingBy { it }.eachCount()
        return pointGrouping.values.count { it >=2 }
    }

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
