import java.io.*;
fun main() {
	BufferedReader(InputStreamReader(System.`in`)).use {

		val str=it.readLine().split(Regex("\\s"))

		BufferedWriter(OutputStreamWriter(System.`out`)).use {
			it.write(resolve(
				str[0].toLong(), str[1].toLong()
			).toString())
			it.flush()
		}
	}

}

fun resolve(n: Long, m: Long): Any {
	return xor(m) xor xor(n-1)
}

fun xor(n: Long): Long {
	return when((n%4).toInt()) {
		0 -> n
		1 -> 1L
		2 -> n+1
		else -> 0L
	}
}
