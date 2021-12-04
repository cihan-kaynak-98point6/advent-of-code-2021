data class Cell(val value: Int, var isMarked:Boolean = false)

data class Row(val cells: List<Cell>)

class Board(boardInput: List<String>){

    companion object Factory{
        fun create(input: List<String>): List<Board>{
            val boards = mutableListOf<Board>()
            val lines = input.drop(1).fold(mutableListOf<String>()) { lines, line ->
                if(line.isBlank() && lines.isNotEmpty()){
                    boards.add(Board(lines))
                    mutableListOf<String>()
                }else if( line.isNotBlank()){
                    lines.add(line)
                    lines
                }else{
                    lines
                }
            }
            if(lines.isNotEmpty()) boards.add(Board(lines))
            return boards
        }
    }

    private val rows: List<Row>
    private val columnSize: Int
    init {
        rows = boardInput.map {
            it.trim().split("\\s+".toRegex())
        }.map { row ->
            Row(row.map { Cell(it.toInt()) })
        }
        columnSize = rows.first().cells.size
    }

    fun checkNumber(number: Int): Boolean{
        rows.forEach {  row ->
            row.cells.find { it.value == number }?.let {
                it.isMarked = true
                return@forEach
            }
        }
        return isWinner()
    }

    fun isWinner(): Boolean{
        val hasWinnerRow = rows.any{ row -> row.cells.all { it.isMarked }}

        var hasWinnerColumn = false
        for(columnIndex in 0 until columnSize){
            val column = rows.map { it.cells[columnIndex] }
            if( column.all { it.isMarked } ){
                hasWinnerColumn = true
                break
            }
        }
        return hasWinnerRow || hasWinnerColumn
    }

    fun sumOfUnmarked(): Int{
       return rows.sumOf { row -> row.cells.filter { !it.isMarked }.sumOf { it.value } }
    }
}


fun main() {
    fun part1(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = Board.create(input.drop(1))

        for(number in numbers){
            boards.find { it.checkNumber(number) }?.let {
                return number * it.sumOfUnmarked()
            }
        }

        return -1
    }

    fun part2(input: List<String>): Int {
        val numbers = input[0].split(",").map { it.toInt() }
        val boards = Board.create(input.drop(1))

        for(number in numbers){
            boards.filter { !it.isWinner() && it.checkNumber(number) }.firstOrNull()?.let {
                if( boards.all { board -> board.isWinner() }) return number * it.sumOfUnmarked()
            }
        }

        return -1
    }

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
