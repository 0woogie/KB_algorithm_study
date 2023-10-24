package study;

import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_17298 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] result = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
        	arr[i] = Integer.parseInt(st.nextToken());
        }
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for (int i = 1; i < n; i++) {
            //스택이 비어 있지 않고 현재 값이 스택 top 인덱스가 가리키는 값보다 큰 경우
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
            	result[stack.pop()] = arr[i]; //정답 배열에 오큰수 저장
            }
            stack.push(i); //현재 값에 대한 오큰수 갱신 끝났으면 해당 값의 인덱스를 stack에 push
        }
        while (!stack.isEmpty()) {
        	result[stack.pop()] = -1;
        }
        StringBuffer sb = new StringBuffer();
        for (int i=0; i<n; i++)
            sb.append(result[i]).append(" ");
        System.out.println(sb);
    }
}
