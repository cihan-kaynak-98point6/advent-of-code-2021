class Command(input: String) {
    val direction:String
    val position: Int

    init {
        val command = input.trim().split(" ")
        direction = command[0]
        position = command[1].toInt()
    }
}

fun main() {
    fun part1(input: List<String>): Int {
        var x = 0
        var y = 0
        input.forEach {
            val command = Command(it)
            when(command.direction){
                "forward" -> x += command.position
                "down" -> y+= command.position
                "up" -> y = 0.coerceAtLeast(y - command.position)
            }
        }
        return x * y
    }

    fun part2(input: List<String>): Int {
        var x = 0
        var y = 0
        var z = 0
        input.forEach {
            val command = Command(it)
            when(command.direction){
                "forward" -> {
                    x += command.position
                    y = 0.coerceAtLeast(y + z * command.position)
                }
                "down" -> z+= command.position
                "up" -> z -= command.position
            }
        }
        return x * y
    }

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
