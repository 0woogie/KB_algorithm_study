package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수 {
	public static void main(String[] args) throws IOException {
		 
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력
		Stack<Integer> stack = new Stack<Integer>(); //스택 선언
		StringBuilder sb = new StringBuilder();
		
		int N = Integer.parseInt(br.readLine()); //수열 A의 크기
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int[] array = new int[N]; //배열 선언
		for(int i = 0; i < N; i++) { //N번 반복
			array[i] = Integer.parseInt(st.nextToken());
		}
 
		for(int i = 0; i < N; i++) {
			//스택이 비어있지 않고, 스택의 맨 위에 있는 원소가 현재 원소보다 작을 때까지 반복
			while(!stack.isEmpty() && array[stack.peek()] < array[i]) {
				array[stack.pop()] = array[i]; //해당 위치의 값을 현재 값으로 업데이트
			}
			
			stack.push(i); //스택에 추가
		}

		while(!stack.isEmpty()) { //스택이 비어질 때까지 반복
			array[stack.pop()] = -1; //스택의 모든 값을 꺼내고, 해당 위치의 값을 -1로 초기화
		}
		
		for(int i = 0; i < N; i++) {
			sb.append(array[i]).append(' '); //seq 배열의 각 원소를 문자열로 변환하고 추가
		}
		
		System.out.println(sb); //출력
	}
}
