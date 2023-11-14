package study;

import java.util.Scanner;

public class BOJ_2531 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int d = sc.nextInt();
		int k = sc.nextInt();
		int c = sc.nextInt();
		
		//초기 세팅
		int[] belt = new int[N];
		int[] visited = new int[d+1];
		int result = 0;
		for(int i=0; i<N; i++) {
			belt[i] = sc.nextInt();
		}
		
		int count = 0;
		//1. k 접시 먹기 && 쿠폰 적용
		for(int i=0; i<k; i++) {
			if(visited[belt[i]]==0)
				count++; //처음 먹는 종류면 카운트
			visited[belt[i]]++;
		}
		if(visited[c]==0)
			count ++;
		result = count;
		
		//2. 투포인터 사용한 순회
		for(int i=1; i<N; i++) {
			int s = i;
			int e = (i+k-1) % N;
			//2-1. 앞에 하나 빼기
			if(belt[s-1]!=c && visited[belt[s-1]]==1)
				count--;
			visited[belt[s-1]]--;
			//2-2. 뒤에 하나 더하기
			if(belt[e]!=c && visited[belt[e]]==0)
				count++;
			visited[belt[e]]++;
			//2-3. result 갱신
			if(result<count)
				result = count;
		}
		
		System.out.println(result);
	}
}
