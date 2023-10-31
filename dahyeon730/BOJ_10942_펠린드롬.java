package com.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_10942_펠린드롬 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		//입력받기
		int N = Integer.parseInt(br.readLine());
		int[] arr = new int[N + 1];

		//N번 반복하기
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		//펠린드롬인지 true/false 체크하기
		boolean[][] check = new boolean[N + 1][N + 1];

		//1. 길이가 1일 경우 -> 항상 펠린드롬
		for (int i = 0; i < N + 1; i++) {
			check[i][i] = true;
		}

		//2. 길이가 2일 경우 -> 2개의 수의 값이 같을 때만 펠린드롬
		for (int i = 1; i < N; i++) {
			if (arr[i] == arr[i + 1]) {
				check[i][i + 1] = true;
			}
		}

		//3. 길이가 3이상일 경우
        ////-> (시작점 + 1) ~ (끝 - 1) 인덱스 까지 펠린드롬
        ////-> 시작점의 값 = 끝의 값 이면 펠린드롬
		for (int i = 2; i < N; i++) {
			for (int j = 1; i + j <= N; j++) {
				if(check[j+1][j+i-1] && arr[j]==arr[j+i])
					check[j][j+i] = true;
			}
		}

		//팰린드롬인 경우에는 1, 아닌 경우에는 0을 출력
		int M = Integer.parseInt(br.readLine());
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			bw.append(check[start][end] ? 1 + "\n" : 0 + "\n");
		}
		bw.flush();
	}
}
