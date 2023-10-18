import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_11501_주식 {

	public static void main(String[] args) throws IOException {
		/*
		 * N이 백만이라 O(N)알고리즘 생각하기
		 * 
		 * 입력배열 마지막 원소를 max라 하고 뒤에서부터 탐색하기
		 * 새 max로 갱신되기 전까지 stack에 현재 max보다 작은 것들 담기
		 * 더 큰 값이 발견되서 max가 갱신되려하면
		 * stack size 저장하고 현재 max값에 곱하고 - A
		 * stack에 있는거 다 pop하고 다 더해서 초기투자금 - B
		 * answer += A-B;
		 * max갱신
		 * index가 0될때까지 반복
		 * 
		 * 빠져 나와서 stack에 뭐가 남아있으면 아까의 A-B해주기
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int i = 0; i<T; i++) {
			//System.out.println((i+1) +"번째 테스트케이스 시작");
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int[] arr = new int[N];
			
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				arr[j] = Integer.parseInt(st.nextToken());
			}
			
			int answer = 0;
			//입력배열 마지막 원소를 max라 하고
			int max = arr[N-1];
			int profit = 0;
			Stack<Integer> s = new Stack<Integer>();
			
			//뒤에서부터 탐색하기
			for(int j = N-2; j>=0; j--) {
				//더 큰 값이 발견되서 max가 갱신되려하면
				if(arr[j]>max) {
					//System.out.println("현재 스택 상태 : "+s);
					//stack size 저장하고 현재 max값에 곱하고 - A
					profit = max * s.size();
					while(!s.isEmpty()) {
						//stack에 있는거 다 pop하고 다 더해서 초기투자금 A에서 빼줌
						profit -= s.pop();
					}
					answer += profit;
					max = arr[j];
				}
				else {
					//새 max로 갱신되기 전까지 stack에 현재 max보다 작은 것들 담기
					s.add(arr[j]);
				}
			}
			
			//빠져 나와서 stack에 뭐가 남아있으면 아까의 A-B해주기
			profit = max * s.size();
			while(!s.isEmpty()) {
				profit -= s.pop();
			}
			answer += profit;
			
			System.out.println(answer);
			
		}
	}

}
