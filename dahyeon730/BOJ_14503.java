package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14503 {

	static int N, M, count;
	static int room[][];
	static int dy[] = {-1,0,1,0};  // 북동남서
	static int dx[] = {0,1,0,-1};
	public static void main(String[] args) throws IOException {
 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer stk = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(stk.nextToken()); //N 입력받기
		M = Integer.parseInt(stk.nextToken()); //M 입력받기
		room = new int[N][M]; //방의 크기
		
		stk = new StringTokenizer(br.readLine(), " ");
		r = Integer.parseInt(stk.nextToken()); //방의 x좌표
		c = Integer.parseInt(stk.nextToken()); //방의 y좌표
		d = Integer.parseInt(stk.nextToken()); // 방향: 0북 , 1동, 2남, 3서
		
		for(int i=0; i<N; i++) { //N번 반복
			stk = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<M; j++) { //M번 반복
				room[i][j] = Integer.parseInt(stk.nextToken()); //각 장소의 상태
			}
		}
 
		dfs(r,c,d);
		System.out.println(count); //청소하는 칸의 개수
	}
	
	public static void dfs(int r, int c, int direction) {
		//여긴 모르겠음
	}
	
}
