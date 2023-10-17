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
				//stack에 (가 나올 때까지 꺼내서 결과값에 append
				while(true) {
					char now = stack.pop();
					if(now=='(') break;
					else sb.append(now);
				}
			}
			else {
				//연산자인 경우
				if(x=='+' || x=='-') {
					//stack이 빌 때까지 꺼내서 결과값에 append
					//단, 괄호 만나면 그만둘 수 있게 주의하기
					while(!stack.isEmpty()) {
						if(stack.peek()=='(') break;
						sb.append(stack.pop());
					}
				} else { // * or /
					//peek 했을 때 *나 /면 꺼내서 결과값에 append
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
