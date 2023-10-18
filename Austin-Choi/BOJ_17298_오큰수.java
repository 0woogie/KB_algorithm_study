package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298_오큰수 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		
		//N이 백만이므로 단순히 이중 for문 돌리면 시간초과 뜰 것
		//O(N) 또는 O(NlogN) 방법 생각해보기
		int N = Integer.parseInt(st.nextToken());
		Stack<Integer> s = new Stack<Integer>();
		
		st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for(int i = 0; i<N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		/*
		 * stack에 입력배열의 인덱스 넣기
		 * 인접한 두 입력배열 원소들끼리 비교해서 쭉 탐색해나감
		 */	
		
		for(int i = 0; i<N; i++) {
			//현재 원소가 stack의 top을 인덱스로 하는 입력배열의 원소보다 크면 (오큰수이면)
			while(!s.isEmpty() && arr[s.peek()] < arr[i])
				//pop하고 그 인덱스가 가르키는 곳을 현재 원소로 채워준다.
				arr[s.pop()] = arr[i];
			//현재 원소가 이전의 원소보다 작을 때까지 stack에 입력배열의 idx를 add
			s.add(i);
		}
		
		//stack에 남아있는 인덱스는 결국 
		//끝까지 탐색했는데도 더 큰 값이 발견되지 않은 값들이므로 -1로 치환해줌
		while(!s.isEmpty())
			arr[s.pop()] = -1;
		
		for(int i = 0 ; i<N; i++) {
			sb.append(arr[i]).append(" ");
		}
		
		System.out.println(sb.toString());
		
	}

}
