package algorithm.Team01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_14916_거스름돈 {
	/*
	 [while(true) 사용]
	 1. 5로 나누어짐 -> break -> count++
	 2. 5로 나누어지지 않음 -> 순차적으로 2를 뺌 -> count++
	     ex. 13 > 11 > 9 > 7 > 5!
	 */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		//거스름돈 입력받기
		int N = Integer.parseInt(br.readLine());
		
		int count = 0;
		int temp = 0;
		
		while(true) {
			if(N%5 == 0) { //거스름돈이 5로 나누어지면
				count += N/5; //5로 나눈 몫을 count변수로 출력
				System.out.println(count);
				break; //나감
			}
			else { //거스름돈이 5로 안 나누어지면
				N-=2; // 거스름돈에서 2를 뺌
				count++; //count 1번 추가
				
				if(N<0) { //거스름돈이 음수가 되면
					System.out.println(-1); //-1 출력
					break;
				}
			}
		}
	}
}
