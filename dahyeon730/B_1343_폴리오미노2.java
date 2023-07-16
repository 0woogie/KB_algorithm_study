package algorithm.Team01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class B_1343_폴리오미노2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//.으로 구간 나누기
		String[] poliomino = br.readLine().split("\\.");
		int len = 0;
		
		//홀/짝 구분하기
		String check = "true";
		
		//X의 개수가 홀수이면 -> false
		for(int i=0; i<poliomino.length; i++) { //.으로 나누어진 구간마다
			if(poliomino[i].length() % 2 != 0) {
				check = "false";
				break;
			}
		}
		
		//X의 개수가 짝수이면 -> true
		if (check == "true") {
			for(int i=0; i<poliomino.length; i++) { 
				len = poliomino[i].length(); //X의 길이
				
				while(true) { //true(홀수)일때
					if(len >= 4) { //4칸 이상이면 'AAAA'출력
						bw.write("AAAA");
						len = len - 4; //X개수에서 4를 뺌 -> 반복
					}
					else if(len>=2) {
						bw.write("BB"); //2~3칸이면 'BB'출력
						len = len -2; //X개수에서 2를 뺌 -> 반복
					}
					else { //이외는 종료
						break;
					}
				}
				if(i<poliomino.length -1) { //마지막을 제외하고 .출력
					bw.write(".");
				}
			}
		}	
		else { //위의 상황에 포함되지 않으면 '-1'출력
			bw.write("-1");
		}
		bw.close();
		br.close();
	}

}
