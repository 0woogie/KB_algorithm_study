package algorithm.Team05;

import java.util.Arrays;

public class PGS_Hindex {
	 public int solution(int[] citations) {
	        int answer = 0;
	        
	        Arrays.sort(citations); //논문 배열 정렬
	        
	        for(int i=0; i<citations.length; i++) { //배열 길이만큼 반복
	        	int h = citations.length - 1; //h정의
	        	
	        	if(citations[i]>=h) { //논문 원소가 h보다 크면
	        		answer = h; //h는 정답
	        		break;
	        	}
	        }
	        
	        return answer;
	    }
	}