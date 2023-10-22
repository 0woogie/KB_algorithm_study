package com.algorithm;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/*
 1) 계속 감소 추세 :: 아무것도 안 하는 것이 이득! (사지도 팔지도 않을 것)
 2) 증가 추세 :: 매일 구매하다가 주가가 떨어지기 전 날 판매
 
 => 역방향으로 탐색하다가 (더 큰 값 발견할 때까지)
    최대 이익 구하기!
 */

public class BOJ_11501 {
	 public static void main(String[] args) throws IOException {

	     BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력값 처리
	     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out)); //결과값 출력
	     
	     int T = Integer.parseInt(br.readLine()); //테스트 케이스 입력받기
	     int[] num;
	     
	     //테스트 케이스 진행~~
	     for(int i=0;i<T;i++){ //테스트 케이스만큼 반복
	    	 int N = Integer.parseInt(br.readLine()); //날의 수 입력받기
	         long answer = 0;
	         
	         StringTokenizer st;
	         st = new StringTokenizer(br.readLine()," ");
	         num = new int[N];
	         
	         //주가 정보 확인~~
	         for(int j=0;j<N;j++) //날의 수만큼 반복
	        	 num[j] = Integer.parseInt(st.nextToken()); //날 별 주가 입력받기
	         	 int max = num[N-1];	//마지막 값을 가장 큰 주가로 설정
	         	 
	             //역방향으로 탐색 진행~~
	             for(int j=N-2; j>=0; j--) {
	                if(num[j] <= max)	//가장 큰 주가보다 작으면
	                    answer += max - num[j]; //작은 주가인 날에는 사고, 큰 주가인 날에는 판매한 이익 계산
	                else		//가장 큰 주가보다 큰 주가 발견하면 max 값 바꾸기
	                    max = num[j];
	            }
	            bw.write(answer + "\n");	//주가 차익의 합 저장
	     }
	     //결과 출력
	     bw.flush();		
	     bw.close();
	     br.close();
	 }
}
