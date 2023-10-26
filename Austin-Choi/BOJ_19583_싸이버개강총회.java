import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583_싸이버개강총회 {
	public static int timeToInt(String time) {
		StringBuilder sb = new StringBuilder();
		String[] arr = time.split("");
		for(int i = 0; i<5; i++) {
			if(i == 2)
				continue;
			else
				sb.append(arr[i]);
		}
		return Integer.parseInt(sb.toString());
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//이름 종류만 저장하니까 셋
		// 스타트 셋, end 셋 저장되있는 이름 count해서 출력하기
		Set<String> start = new HashSet<String>();
		Set<String> end = new HashSet<String>();
		// 0 <= start <= S, E <= end <= Q
		int S, E, Q;
		S = timeToInt(st.nextToken());
		E = timeToInt(st.nextToken());
		Q = timeToInt(st.nextToken());
		
		int curTime = 0;
		String curName;
		while(true) {
			try {
				st = new StringTokenizer(br.readLine());
				curTime = timeToInt(st.nextToken());
				curName = st.nextToken();
			}
			catch (IOException e){
				break;
			}
			catch (NoSuchElementException e) {
				break;
			}	
			
			if(curTime <= S && curTime >= 0)
				start.add(curName);
			if(curTime <= Q && curTime >= E)
				end.add(curName);
		}
		int total = 0;
		for(String s : end) {
			if(start.contains(s))
				total++;
		}
		System.out.println(total);
	}
}
