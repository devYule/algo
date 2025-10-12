import java.io.*;
fun main() {
	BufferedReader(InputStreamReader(System.`in`)).use {

		val spl=it.readLine().split(Regex("\\s"))
		val n=spl[0].toInt()
		val m=spl[1].toInt()

		val edge=Array(m) { IntArray(2) }
		for(i in 0..<m) {
			val spl2=it.readLine().split(Regex("\\s"))
			edge[i][0]=spl2[0].toInt()
			edge[i][1]=spl2[1].toInt()
		}

		it.readLine()

		val fans=it.readLine().split(Regex("\\s")).map(String::toInt).toIntArray()


		BufferedWriter(OutputStreamWriter(System.`out`)).use {
			it.write(resolve(
				n, m, edge, fans
			).toString())
			it.flush()
		}
	}

}

lateinit var adj: Array<MutableList<Int>>
lateinit var exist: BooleanArray
var V=-1


fun resolve(n: Int, m: Int, edge: Array<IntArray>, fans: IntArray): Any {
	init(n+1, edge)
	exist=BooleanArray(V)
	for(loc in fans) {
		exist[loc]=true
	}

	return if(dfs(1)) "yes" else "Yes"
}

fun dfs(cur: Int): Boolean {
	if(exist[cur]) return false
	if(adj[cur].isEmpty()) return true
	var ret=false
	for(next in adj[cur]) {
		if(dfs(next)) ret=true
	}
	return ret
}

fun init(v: Int, edge: Array<IntArray>) {
	V=v
	adj=Array(V) { mutableListOf() }

	for((a, b) in edge) {
		adj[a]+=b
	}

}
