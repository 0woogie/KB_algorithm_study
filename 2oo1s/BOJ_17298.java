package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_17298 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		int A = Integer.parseInt(br.readLine());

		int[] arr = new int[A];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < A; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		Stack<Integer> stack = new Stack<>(); // 탐색 idx 저장용

		for (int i = 0; i < A; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) // 현재 arr[i]의 값이 i보다 작은 idx들 중 누군가의 오큰수인지 확인
				arr[stack.pop()] = arr[i]; // 오른쪽 큰 수로 값 저장
			stack.push(i);
		}

		while (!stack.isEmpty())
			arr[stack.pop()] = -1; // 오른쪽에 큰 수가 없는 idx 애들은 stack에 남아있으므로, 오큰수 -1로 저장

		for (int i : arr)
			sb.append(i).append(" ");

		System.out.print(sb);
	}
}