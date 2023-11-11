import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2531_회전초밥 {
	static int N;
	static int d;
	static int k;
	static int c;
	//투포인터
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		int[] sushi = new int[N];
		Set<Integer> s;
		
		for(int i = 0; i<N; i++) {
			int cur = Integer.parseInt(br.readLine());
			sushi[i] = cur;
		}
		// 0 1 2 3 4 
		// 3
		// 3,4,5 -> 3,4,0
		
		// 0 1 2 3  4 5 6 7
		// 7 9 7 30 2 7 9 25
		// 1 2 2 3  4 4 4 5
		// N = 8
		// d = 30
		// k = 4
		// c = 30
		
		int answer = 0;
		for(int i = 0; i<N; i++) {
			s = new HashSet<Integer>();
			int start = i;
			int end = i+k-1;
			if(end>N-1) {
				for(int j = start; j<N; j++) {
					s.add(sushi[j]);
				}
				end = end%N;
				for(int j = 0; j<=end; j++) {
					s.add(sushi[j]);
				}
			}
			else {
				for(int j = start; j<=end; j++) {
					s.add(sushi[j]);
				}
			}
			s.add(c);
			int curKindCount = s.size();
			if(curKindCount > answer)
				answer = curKindCount;
		}
		System.out.println(answer);
	}

}
