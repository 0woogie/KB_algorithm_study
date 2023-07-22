package study;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2217 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for(int i=0; i<n; i++) {
			arr[i] = sc.nextInt();
		}
		Arrays.sort(arr); //가장 약한 로프부터 오름차순으로 정렬
		int maxNum = 0;
		for(int i=0; i<n; i++) {
			int tmp = arr[i]*(n-i); //i번째 로프가 포함된 경우 최대 중량은 arr[i]*(n-i)가 됨
			if(tmp>maxNum) maxNum = tmp; //최대 중량 갱신
		}
		System.out.println(maxNum);
	}

}
