package com.edu.algo.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1343 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		String str = st.nextToken();
		//문자열 "XXXX"를 우선으로 찾아서 "AAAA"로 치환
		str = str.replace("XXXX", "AAAA");
		str = str.replace("XX", "BB");
		
		
		//아직 남아있는 X는 치환되지 못한 문자열이 있는 것
		if(str.contains("X"))
			System.out.println(-1);
		else
			System.out.println(str);
	}
}
