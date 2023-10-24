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
            //������ ��� ���� �ʰ� ���� ���� ���� top �ε����� ����Ű�� ������ ū ���
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i]) {
            	result[stack.pop()] = arr[i]; //���� �迭�� ��ū�� ����
            }
            stack.push(i); //���� ���� ���� ��ū�� ���� �������� �ش� ���� �ε����� stack�� push
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
