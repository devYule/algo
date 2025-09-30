import java.util.*;
import java.io.*;
public class Main {
	public static void main(String[] args) throws IOException {
		try(BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(System.out))) {

			bw.write(
				String.valueOf(
					new Main().resolve(
						br.readLine().split("\\s"),
						br.readLine().split("\\s")
					)
				)
			);
			bw.flush();
		}
	}

	String resolve(String[] a, String[] b) {
		Set<String> colorSet=new HashSet<>();
		
		colorSet.add(a[0]);
		colorSet.add(a[1]);
		colorSet.add(b[0]);
		colorSet.add(b[1]);

		List<String> color=new ArrayList<>(colorSet);

		List<String[]> ret=new ArrayList<>();
		for(int i=0; i<color.size(); i++) {
			for(int j=0; j<color.size(); j++) {
				ret.add(new String[] {color.get(i), color.get(j)});
			}
		}

		return ret.stream().map(arr->arr[0]+" "+arr[1]).sorted(Comparator.naturalOrder()).collect(java.util.stream.Collectors.joining("\n"));
	}
}
