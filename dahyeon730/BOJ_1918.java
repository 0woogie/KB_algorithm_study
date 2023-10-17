package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BOJ_1918 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) { //문자열 길이만큼 반복
            char now = str.charAt(i); //주어진 문자열에서 인덱스 i에 해당하는 위치에 있는 문자를 now 변수에 할당

            switch (now){ //인덱스 i에 해당하는 위치에 있는 문자를 now 변수 case
                case '+':
                case '-':
                case '*':
                case '/':
                    while (!stack.isEmpty() && priority(stack.peek()) >= priority(now)) {
                        sb.append(stack.pop());
                    } //스택의 맨 위의 연산자 우선순위가 현재 처리 중인 now 연산자의 우선순위보다 크거나 같을 때까지 연산자를 팝하고 sb에 추가
                    stack.add(now);
                    break;
                case '(':
                    stack.add(now);
                    break;
                case ')':
                    while(!stack.isEmpty() && stack.peek() != '('){
                        sb.append(stack.pop());
                    } //스택이 비어 있지 않고 스택의 맨 위에 있는 항목이 '(' 가 아니면 팝하고 sb에 추가
                    stack.pop();
                    break;
                default:
                    sb.append(now);
            }
        }

        while (!stack.isEmpty()) { 
            sb.append(stack.pop());
        } //스택에 남아 있는 모든 항목을 팝하고 sb에 추가

        System.out.println(sb.toString()); //sb를 문자열로 출력
    }

    // 연산자 별 우선순위 리턴
    public static int priority(char operator){

        if(operator=='(' || operator==')'){
            return 0;
        } else if (operator == '+' || operator == '-') {
            return 1;
        } else if (operator == '*' || operator == '/') {
            return 2;
        }
        return -1;
    }
}
