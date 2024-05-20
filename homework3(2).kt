# матрицы передаются извне в виде командной строки (входных аргументов программы) или http сервис.


import kotlin.system.exitProcess

fun main(args: Array<String>) {
    if (args.size != 2) {
        println("Ошибка! Пожалуйста, укажите две матрицы в качестве аргументов командной строки.")
        exitProcess(1)
    }

    val matrix1 = parseMatrix(args[0])
    val matrix2 = parseMatrix(args[1])

    if (matrix1.isEmpty() || matrix2.isEmpty()) {
        println("Ошибка! Некорректный формат матриц. Пожалуйста, укажите матрицы в правильном формате.")
        exitProcess(1)
    }

    if (matrix1[0].size != matrix2.size) {
        println("Ошибка! Количество столбцов первой матрицы должно быть равно количеству строк второй матрицы.")
        exitProcess(1)
    }

    val result = computeScalarProduct(matrix1, matrix2)
    println("Скалярное произведение матриц: $result")
}

fun parseMatrix(input: String): List<List<Int>> {
    return input.split(";").map { row ->
        row.trim().split(" ").mapNotNull { element ->
            element.toIntOrNull()
        }
    }
}

fun computeScalarProduct(matrix1: List<List<Int>>, matrix2: List<List<Int>>): Int {
    val numRows = matrix1.size
    val numCols = matrix2[0].size
    val result = Array(numRows) { IntArray(numCols) }

    for (i in 0 until numRows) {
        for (j in 0 until numCols) {
            var sum = 0L
            for (k in matrix1[i].indices) {
                sum += matrix1[i][k] * matrix2[k][j]
            }
            result[i][j] = sum.toInt()
        }
    }

    return result[0][0]
}
