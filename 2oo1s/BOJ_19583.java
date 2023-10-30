package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_19583 {

	// 32분
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int answer = 0;

		String S = st.nextToken();
		String E = st.nextToken();
		String Q = st.nextToken();

		HashSet<String> sCheck = new HashSet<>(); // 개총 전에 출석한 학생 저장용
		HashSet<String> eCheck = new HashSet<>(); // 개총 끝나고 스트리밍 종료 전까지 있었전 학생 저장용

		String check = br.readLine();

		while (check != null && !check.isEmpty()) {
			st = new StringTokenizer(check, " ");
			String time = st.nextToken();
			String name = st.nextToken();

			if (S.compareTo(time) >= 0)
				sCheck.add(name);
			else if (E.compareTo(time) <= 0 && Q.compareTo(time) >= 0)
				eCheck.add(name);

			check = br.readLine();
		}

		for (String s : sCheck) { // 출석 확인된 학생 수 카운트
			if (eCheck.contains(s))
				answer++;
		}

		System.out.println(answer);
	}
}