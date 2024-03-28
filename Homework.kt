import java.io.File
import java.math.BigDecimal
import java.math.RoundingMode


val filePath = "C://Kotlin_1.txt"
val outputFileName = "Kotlin_1.txt"


class City(val name: String, val measurement: Double)


fun writeFile(cityToMeasures: Map<String, List<Double>>) {
    try {
        val writer = File(outputFileName).bufferedWriter()
        for ((key, cityList) in cityToMeasures) {
            val average = BigDecimal(cityList.average()).setScale(1, RoundingMode.HALF_EVEN)
            val line = "$key=${cityList.minOrNull()}/${average}/${cityList.maxOrNull()}\n"
            writer.write(line)
        }
        writer.close()
    } catch (ex: Exception) {
        println("Ошибка записи: ${ex.message}")
    }
}


fun readFile(cityList: MutableList<City>) {
    val file = File(filePath)
    try {
        file.forEachLine { line ->
            val (name, measurement) = line.split(";")
            val city = City(name, measurement.toDouble())
            cityList.add(city)
        }
    } catch (ex: Exception) {
        println("Ошибка чтения: ${ex.message}")
    }
}

fun main() {
    val cityList: MutableList<City> = mutableListOf()
    readFile(cityList)
    val cityToMeasures: Map<String, List<Double>> = cityList.groupBy({ it.name }) { it.measurement }
    writeFile(cityToMeasures)
}
