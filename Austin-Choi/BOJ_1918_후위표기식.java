
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_1918_후위표기식 {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String str = st.nextToken();
		
		//입력받은 스트링 char형 배열로 변형후 저장
		char[] expr = str.toCharArray();
		
		//연산자 저장할 스택
		Stack<Character> s = new Stack<>();
		
		//연산자 우선순위 저장할 map
		Map<Character, Integer> operatorMap = new HashMap<Character, Integer>();
		//괄호 우선순위 각각 다름 ( 는 가장 낮아야 함, 괄호 표기해주지도 않고 )이 나왔을때 
    //출력 기준을 위한 것
		operatorMap.put('(', 0);
		operatorMap.put('+', 1);
		operatorMap.put('-', 1);
		operatorMap.put('*', 2);
		operatorMap.put('/', 2);
		operatorMap.put(')', 2);
		
		
		//답 string
		StringBuilder answer = new StringBuilder();
		for(int i = 0; i<expr.length; i++) {
			//피연산자면 그냥 붙임
			if(Character.isUpperCase(expr[i]))
				answer.append(expr[i]);
			//연산자면 스택활용
			else
			{
				// ( 이면 스택에 push
				if(expr[i] == '(')
					s.push(expr[i]);
				
				// ) 이면 ( 이 나올때까지 전부 pop
				else if(expr[i] == ')') {
					while(!s.isEmpty()) {
						char c = s.peek();
						s.pop();
						if(c == '(')
							break;
						else
							answer.append(c);
					}
				}
				//그 외 연산자들은
				//스택 안에 있는 우선순위가 더 높거나 같은 것들 전부 
				//pop해서 append해주고 
				//현재 연산자를 스택에 push
				else {
					while(!s.isEmpty() && operatorMap.get(s.peek()) >= operatorMap.get(expr[i])) {
						//괄호는 나오면 안되니까 예외처리
						if(s.peek() != '(' && s.peek() != ')')
							answer.append(s.pop());
						else
							s.pop();
					}
					s.push(expr[i]);
				}
			}
			//System.out.println(i+"번째의 answer : "+answer.toString());
			//System.out.println(i+"번째의 stack 출력 : "+ s);
		}
		//System.out.println("마지막 stack 출력 : "+ s);
		while(!s.isEmpty()) {
			if(s.peek() != '(' && s.peek() != ')')
				answer.append(s.pop());
			else
				s.pop();
		}
		System.out.println(answer.toString());
	}

}
