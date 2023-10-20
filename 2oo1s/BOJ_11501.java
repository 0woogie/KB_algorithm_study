package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_11501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine()); // 테케 수
		long[] profit = new long[T]; // 테케별 이익 ... 자료형 주의

		for (int tc = 0; tc < T; tc++) {
			int N = Integer.parseInt(br.readLine()); // 날의 수
			int[] stocks = new int[N];
			int max = 0;

			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++)
				stocks[i] = Integer.parseInt(st.nextToken());

			for (int i = N - 1; i >= 0; i--) {
				if (stocks[i] > max) // 다음날 주가가 떨어진 경우
					max = stocks[i];
				else // 다음날 주가가 오른 경우
					profit[tc] += (max - stocks[i]);
			}
		}
		for (long p : profit)
			System.out.println(p);
	}
}