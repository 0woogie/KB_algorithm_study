package study;

import java.util.Scanner;

public class BOJ_2828 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		int s = 1, e = m; //가장 처음에 바구니는 왼쪽 M칸을 차지 (바구니의 시작위치:1, 끝위치:m)
		int j = sc.nextInt();
		int result = 0;
		for(int i=0; i<j; i++) {
			int pos = sc.nextInt(); //사과가 떨어지는 위치
			if(e<pos) { //사과가 떨어지는 위치가 바구니 오른쪽 밖
				result += pos-e; //바구니를 오른쪽으로 (pos-e)만큼만 이동
				e = pos;
				s = e - m + 1;
			} else if(pos<s) { //사과가 떨어지는 위치가 바구니 왼쪽 밖
				result += s-pos; //바구니를 왼쪽으로 (s-pos)만큼만 이동
				s = pos;
				e = s + m - 1;
			}
		}
		System.out.println(result);
	}

}
