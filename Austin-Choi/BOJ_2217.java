package com.edu.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2217 {
	static int max;
	static int N;
	static int[] arr;
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(arr);
		int cur = 0;
		max = 0;
		//오름차순이니까 뒤에서부터 하나씩 뽑고 
		// 뽑은 로프 각자 무게 * 뽑은 로프 종류 갯수 = 최대 가능 무게
		// 최대 가능 무게 갱신
		for(int i = N-1; i>=0; i--) {
			cur = arr[i] * (n-i);
			if(cur > max)
				max = cur;
		}
		System.out.println(max);
	}
	
}
