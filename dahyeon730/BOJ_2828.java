package algorithm.Team02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2828 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력받기
		int N = Integer.parseInt(st.nextToken()); //스크린 칸 수
		int M = Integer.parseInt(st.nextToken()); //바구니가 차지하는 칸 수 
		
		//사과 수 입력받기
		st = new StringTokenizer(br.readLine());
		int count = Integer.parseInt(st.nextToken());
		
		
		int left = 1; //바구니 왼쪽 위치
		int right = M; //바구니 오른쪽 위치
		
		
		
		//사과 위치 입력받기
		int distance = 0; // 바구니의 이동 거리
		for(int i=0; i<count; i++) { //사과 수 만큼
			st = new StringTokenizer(br.readLine());
			int apple = Integer.parseInt(st.nextToken()); //사과 위치 입력받기
			
			if(apple<left) {//사과가 바구니의 왼쪽에 있는 경우
				distance += left - apple; //사과와 바구니 사이의 거리만큼 바구니를 왼쪽으로 이동
				left = apple; //사과가 있는 위치까지 바구니의 왼쪽 위치 이동
				right = apple + (M-1); //바구니의 오른쪽 위치 이동
			}
			else if(apple>right) {//사과가 바구니의 오른쪽에 있는 경우
				distance += apple - right; //사과와 바구니 사이의 거리만큼 바구니를 오른쪽으로 이동
				right = apple; //사과가 있는 위치까지 바구니의 오른쪽 위치 이동
				left = apple - (M-1); //바구니의 왼쪽 위치 변경
			}
		}
		//출력하기
		System.out.println(distance);
	}
}
