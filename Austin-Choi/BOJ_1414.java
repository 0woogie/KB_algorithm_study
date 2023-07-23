package com.edu.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.*;

public class BOJ_1414 {
	static int[] p;
	static int[][] board; 
	static PriorityQueue<int[]> edgeInfos;
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		board = new int[N+1][N+1];
		// 0: i, 1: j, 2: board[i][j]
		//어차피 정렬해야하고 이 문제는 edge 값만 들어오는게 아니므로 
		//priorityQueue 자료구조를 활용해서 [2]기준으로 정렬해서 저장함.
		edgeInfos = new PriorityQueue<int[]>((x,y)->x[2]-y[2]);
		
		//루트노드 배열 초기화
		p = new int[N+1];
		for(int i = 1; i<=N; i++) {
			p[i]=i;
		}
		int totalLan = 0;
		
		for(int i = 1; i<=N; i++) {
			String str = br.readLine();
			for(int j = 1; j<=N; j++) {
				char curC = str.charAt(j-1);
				//0은 연결 없다는거니까 스킵
				if(curC == '0')
					continue;
				else {
					if(Character.isUpperCase(curC))
						board[i][j] = curC - 'A'+27;
					if(Character.isLowerCase(curC))
						board[i][j] = curC - 'a' + 1;
				}
				totalLan += board[i][j];
//				System.out.println("totalLAN: "+totalLan);
				edgeInfos.add(new int[] {i,j,board[i][j]});
			}
		}
//		System.out.println("final totalLAN: "+totalLan);
		
		
		int[] edgeInfo;
		int edgeCount = 0; //vortex = N일때 edge = N-1이면 mst 성립
		while(!edgeInfos.isEmpty()) {
			edgeInfo = edgeInfos.poll();
			
			if(!isSet(edgeInfo[0],edgeInfo[1])) {
				makeSet(edgeInfo[0], edgeInfo[1]);
				totalLan -= edgeInfo[2];
//				System.out.println("processingEdge = "+edgeInfo[0]+","+edgeInfo[1]+": "+edgeInfo[2]);
				edgeCount++;
			}
			if(edgeCount == N-1)
				break;
		}
		//mst를 못 이룬 경우
		if(edgeCount < N-1)
			System.out.println(-1);
		else
			System.out.println(totalLan);
	}
	
	public static void makeSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y)
			return;
		if(x>y)
			p[x] = y;
		else
			p[y] = x;
	}
	
	public static boolean isSet(int x, int y) {
		x = findSet(x);
		y = findSet(y);
		if(x==y)
			return true;
		return false;
	}
	
	public static int findSet(int x) {
		if(p[x]==x)
			return x;
		return p[x] = findSet(p[x]);
	}

}
