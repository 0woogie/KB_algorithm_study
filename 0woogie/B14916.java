package study;

import java.util.Scanner;

public class B14916 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int cnt = 0;
		while(n>0) {
			if(n%5==0) { //�Ž������� 5�� ������� �켱������ Ȯ��
				cnt += n/5;
				n = 0;
			} else { //�Ž������� 5�� ����� �ƴϸ�
				n -= 2; //2���� �ϳ� �Ž��� �ְ� �ٽ� 5�� ��� üũ
				cnt++;
			}
		}
		if(n==0) System.out.println(cnt);
		else System.out.println(-1); //�Ž��� �� �� ���� ���
	}
}