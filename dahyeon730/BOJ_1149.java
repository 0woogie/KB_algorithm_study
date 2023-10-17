package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1149 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

        int n = Integer.parseInt(br.readLine()); // 첫째줄 입력받기

        int[][] dp = new int[n + 1][3]; //2차 배열 만들기

        for (int i = 1; i <= n; i++) { //집 개수만큼 반복
            StringTokenizer st = new StringTokenizer(br.readLine()); //한 줄 띄기
            int red = Integer.parseInt(st.nextToken()); //빨강 비용
            int green = Integer.parseInt(st.nextToken()); //초록 비용
            int blue = Integer.parseInt(st.nextToken()); //파랑 비용

            dp[i][0] = Math.min(dp[i - 1][1], dp[i - 1][2]) + red; //빨강일 때: i-1번째 집이 초록/파랑 중 최솟값과 빨강일 때의 비용값을 더하기
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][2]) + green; //초록일 때: i-1번째 집이 빨강/파랑 중 최솟값과 초록일 때의 비용값을 더하기
            dp[i][2] = Math.min(dp[i - 1][0], dp[i - 1][1]) + blue; //파랑일 때: i-1번째 집이 초록/빨강 중 최솟값과 파랑일 때의 비용값을 더하기
        }
        System.out.println(Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]))); //dp[n][0], dp[n][1], dp[n][2] 중 최솟값 출력
    }
}
