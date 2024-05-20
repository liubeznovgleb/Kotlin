# программа с пользовательским вводом матриц


import java.util.*

fun main() {
    val matrix1 = readMatrixFromUser()
    val matrix2 = readMatrixFromUser()

    val result = computeScalarProduct(matrix1, matrix2)
    printMatrix(result)
}

fun readMatrixFromUser(): List<List<Int>> {
    val scanner = Scanner(System.`in`)
    val rows = mutableListOf<List<Int>>()

    println("Введите количество строк в матрице:")
    val rowCount = scanner.nextInt()

    println("Введите количество столбцов в матрице:")
    val columnCount = scanner.nextInt()

    println("Введите элементы матрицы:")

    for (i in 0 until rowCount) {
        val row = mutableListOf<Int>()
        for (j in 0 until columnCount) {
            val element = scanner.nextInt()
            row.add(element)
        }
        rows.add(row)
    }

    return rows
}

fun computeScalarProduct(matrix1: List<List<Int>>, matrix2: List<List<Int>>): List<List<Int>> {
    val numRows = matrix1.size
    val numCols = matrix2[0].size
    val result = MutableList(numRows) { MutableList(numCols) { 0 } }

    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            var sum = 0L
            for (k in matrix1[i].indices) {
                sum += matrix1[i][k].toLong() * matrix2[k][j].toLong()
            }
            result[i][j] = sum.toInt()
        }
    }

    return result
}

fun printMatrix(matrix: List<List<Int>>) {
    println("Матрица:")
    for (row in matrix) {
        for (element in row) {
            print("$element ")
        }
        println()
    }
}
