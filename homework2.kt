import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.util.regex.Pattern

fun main() {
    val wikipediaURL = "https://ru.wikipedia.org/wiki/%D0%A4%D1%83%D0%BD%D0%B4%D0%B0%D0%BC%D0%B5%D0%BD%D1%82%D0%B0%D0%BB%D1%8C%D0%BD%D0%B0%D1%8F_%D0%BF%D0%BE%D1%81%D0%BB%D0%B5%D0%B4%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D0%BD%D0%BE%D1%81%D1%82%D1%8C"

    val htmlContent = getHTMLContent(wikipediaURL)

    val links = extractLinks(htmlContent)

    links.forEach { println(it) }
}

fun getHTMLContent(urlString: String): String {
    val url = URL(urlString)
    val connection = url.openConnection() as HttpURLConnection
    connection.requestMethod = "GET"
    val reader = BufferedReader(InputStreamReader(connection.inputStream))
    val content = StringBuilder()
    var line: String?
    while (reader.readLine().also { line = it } != null) {
        content.append(line)
    }
    reader.close()
    return content.toString()
}

fun extractLinks(htmlContent: String): List<String> {
    val pattern = Pattern.compile("<a\\s+(?:[^>]*?\\s+)?href=\"(https?://[^\"]*)\"")
    val matcher = pattern.matcher(htmlContent)
    val links = mutableListOf<String>()
    while (matcher.find()) {
        links.add(matcher.group(1))
    }
    return links
}
