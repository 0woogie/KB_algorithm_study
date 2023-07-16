package study;

import java.util.Scanner;

public class B14916 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		while(n>0) {
			if(n%5==0) { //거스름돈이 5의 배수인지 우선적으로 확인
				cnt += n/5;
				n = 0;
			} else { //거스름돈이 5의 배수가 아니면
				n -= 2; //2원을 하나 거슬러 주고 다시 5의 배수 체크
				cnt++;
			}
		}
		if(n==0) System.out.println(cnt);
		else System.out.println(-1); //거슬러 줄 수 없는 경우
	}
}