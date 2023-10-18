import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 연습용 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()); //첫번째 줄 입력


        String sol = st.nextToken(); //식을 받아줌
        Stack<Character>stack = new Stack<>();

        String answer = ""; //정답이 되는 후위 표기식
        for(int i=0;i<sol.length();i++){
            char c = sol.charAt(i); //현재 문자
            if(c>='A' && c<='Z') //피연산자일 경우
                answer+=c;
            else{ //연산자일 경우
                if(c=='(')stack.push(c); // ( 일경우
                else if(c==')'){ // )일 경우
                    // ( 를 만날 때 까지 모두 빼주기
                    while(!stack.isEmpty() && stack.peek() != '(')answer+=stack.pop();
                    if(!stack.isEmpty())stack.pop(); //남아있는 ( 빼주기
                }
                else{ //일반적인 연산자일 경우
                    //현재 연산자 보다 우선순위가 같거나 큰 것은 먼저 출력해줘야 함
                    while(!stack.isEmpty() && precedence(stack.peek())>= precedence(c))
                        answer+=stack.pop();
                    stack.push(c); //다 빼고 현재 연산자 들어감
                }
            }
        }
        while(!stack.isEmpty())answer+=stack.pop(); //스택에 남아있는거 모두 빼주기
        System.out.println(answer);
    }
    public static int precedence(char c){ //연산자의 우선순위를 계산하는 메서드
        if(c=='+' || c=='-')return 1;
        else if(c=='*' || c=='/')return 2;
        else return 0; // ( 도 들어갈 수 있음
    }

}

