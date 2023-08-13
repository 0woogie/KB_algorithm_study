package algorithm.Team05;
import java.io.*;
import java.util.*;
public class PGS_소수찾기 {
	/*
	 [DFS]
	 1. String을 Integer로 변경 -> 모든 값을 중복없이 리스트 arr에 담기
	 2. 리스트에 담긴 값을 소수인지 판단
	 */
	
	 static ArrayList<Integer> arr = new ArrayList<>();
	    static boolean[] check = new boolean[7]; //numbers는 길이 1 이상 7 이하인 문자열이기 때문
	    
	    public int solution(String numbers) {
	        int answer = 0;
	        for(int i=0; i<numbers.length(); i++){ //numbers의 길이만큼 반복
	            dfs(numbers,"",i+1);
	        }
	        
	        for(int i=0; i<arr.size(); i++){
	            if(prime(arr.get(i))) 
	            	answer++;              
	        }
	        
	        return answer;
	  
	    }
		//백트래킹
		static void dfs(String str, String temp, int m) {
			if(temp.length() == m){
				int num = Integer.parseInt(temp);
	            if(!arr.contains(num)){ //arr에 없으면 소수 판별 -> 추가
	            	arr.add(num);
	            }
	        }
	        
	        for(int i=0; i<str.length(); i++){
	            if(!check[i]){ // 방문했으면 넘어감
	            	check[i] = true; //방문
	                temp += str.charAt(i);
	                dfs(str, temp, m); 
	                check[i] = false; //백트래킹
	                temp = temp.substring(0, temp.length()-1);
	            }
	        }
			
		}
		//소수 판단
		static boolean prime(int n) {
			if(n<2)  // 2보다 작으면 소수 아님
				return false;
			
			for(int i=2; i*i<=n; i++) {
				if(n % i == 0) 
					return false;
			}
			
			return true;
		}

}

