package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17140_이차열배열과연산 {
	static int r, c, k; 
	static int[][] arrayA;
	static int[][] arrayResult; //정렬된 결과를 배열에 다시 넣는 곳
	
	public static void main(String[] args) throws IOException{
		   BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		   
		   //입력받기
		   r = Integer.parseInt(st.nextToken());
		   c = Integer.parseInt(st.nextToken());
		   k = Integer.parseInt(st.nextToken());
		   
		   //배열 A에 들어있는 수
		   arrayA = new int[3][3];
		   for(int i=0; i<3; i++) {
			   st = new StringTokenizer(br.readLine(), " ");
			   for(int j=0; j<3; j++) {
				   arrayA[i][j] = Integer.parseInt(st.nextToken());
			   }
		   }
	}
	
	//R 연산
	static void rCal() {
		arrayResult = new int[300][300]; // 연산 후의 상태를 기록할 배열(자동으로 0을 채워줌)

		for(int i=0; i<arrayA.length; i++) {
		}
	}
	
	//C 연산
	static void cCal() {
		arrayResult = new int[300][300]; // 연산 후의 상태를 기록할 배열(자동으로 0을 채워줌)
			   
		for(int i=0; i<arrayA.length; i++) {
		}
	}
	//행, 열이 100을 넘어가면 처음 100개를 제외한 나머지는 버린다
	
	//수의 갯수, 크기를 비교하여 정렬
}
