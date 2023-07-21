package com.edu.algo.test;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2828 {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int start = 0;
		int end = start + M - 1;
		
		st = new StringTokenizer(br.readLine());
		int J = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		
		int temp = 0;
		int count = 0;
		for(int i = 0; i<J; i++) {
			st = new StringTokenizer(br.readLine());
			temp = Integer.parseInt(st.nextToken()) - 1;
			while(true) {
				if(temp >= start && temp <= end) {
					break;
				}
				else {
					if(temp < start) {
						start--;
						end--;
						count++;
					}
					if(temp > end) {
						start++;
						end++;
						count++;
					}
				}
			}
		}
		System.out.println(count);
		
	}

}
