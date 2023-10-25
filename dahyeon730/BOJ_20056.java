package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1. 입력된 정보 저장
//2. K번 이동 명령 진행
//3. 남아있는 파이어볼 질량의 합을 결과로 출력
public class BOJ_20056 {
	
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //방향을 변경하는 값을 표현하는 배열
        int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1}; //방향 r의 변경값
        int[] dy = {0, 1, 1, 1, 0, -1, -1, -1}; //방향 c의 변경값
        
        int N = Integer.parseInt(st.nextToken()); //크기가 N인 격자 입력받기
        int M = Integer.parseInt(st.nextToken()); //M개의 줄 입력받기
        int K = Integer.parseInt(st.nextToken()); //명령 개수 입력받기
    
        //입력되는 파이어볼 정보 저장
        for (int i = 0; i < M; i++) { //M만큼 반복
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
        }
        
        //K번 이동 명령
        for (int i = 0; i < K; i++) {
        	
        }
        
        //파이어볼 이동...어렵당
        
        //남아있는 파이어볼 질량의 합을 결과로 출력
    }

}
