package study;

import java.util.Scanner;

public class BOJ_1343 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str = sc.next();

		str = str.replaceAll("XXXX", "AAAA"); //XXXX�� �켱������ ��ü(���������Ƿ� A����)
		str = str.replaceAll("XX", "BB");
		
		if(str.contains("X")) System.out.println(-1); //�������̳�� ���� �� ���� ���
		else System.out.println(str);
	}

}
