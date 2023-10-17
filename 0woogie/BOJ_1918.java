package study;

import java.util.Scanner;
import java.util.Stack;

public class BOJ_1918 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		char[] charArray = sc.next().toCharArray();
		Stack<Character> stack = new Stack<>();
		StringBuffer sb = new StringBuffer();
		
		for(int i=0; i<charArray.length; i++) {
			char x = charArray[i];
			if(x>='A' && x<='Z') sb.append(x);
			else if(x=='(') stack.push(x);
			else if(x==')') {
				//stack�� (�� ���� ������ ������ ������� append
				while(true) {
					char now = stack.pop();
					if(now=='(') break;
					else sb.append(now);
				}
			}
			else {
				//�������� ���
				if(x=='+' || x=='-') {
					//stack�� �� ������ ������ ������� append
					//��, ��ȣ ������ �׸��� �� �ְ� �����ϱ�
					while(!stack.isEmpty()) {
						if(stack.peek()=='(') break;
						sb.append(stack.pop());
					}
				} else { // * or /
					//peek ���� �� *�� /�� ������ ������� append
					while(!stack.isEmpty()) {
						if(stack.peek()!='*' && stack.peek()!='/') break;
						sb.append(stack.pop());
					}
				}
				stack.push(x);
			}
		}
		while(!stack.isEmpty()) sb.append(stack.pop());
		
		System.out.println(sb.toString());
	}

}
