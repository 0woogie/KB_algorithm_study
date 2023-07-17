package study;

import java.util.Scanner;

public class BOJ_1343 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		str = str.replaceAll("XXXX", "AAAA"); //XXXX를 우선적으로 대체(사전순으므로 A부터)
		str = str.replaceAll("XX", "BB");
		
		if(str.contains("X")) System.out.println(-1); //폴리오미노로 덮을 수 없는 경우
		else System.out.println(str);
	}

}
