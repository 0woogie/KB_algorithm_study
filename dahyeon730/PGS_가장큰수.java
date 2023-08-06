package algorithm.Team04;

import java.util.Arrays;
import java.util.stream.Collectors;
 
class Solution {
    public String solution(int[] numbers) {
    	String answer = "";
    	
        String[] nums = new String[numbers.length]; //numbers 배열 길이만큼 string 배열 만들어주기
        for (int i = 0; i < numbers.length; i++) //numbers 배열 길이 동안
            nums[i] = numbers[i] + ""; //string 배열에다가 numbers 배열 원소 삽입
 
        Arrays.sort(nums, (n1, n2) -> (n2 + n1).compareTo(n1 + n2)); //앞뒤 문자열 원소를 이어주고 내림차순으로 정렬
        
        //만약 내림차순으로 정리했는데 0이 맨 앞에 오면 0을 출력    
        answer =  nums[0].equals("0") ? "0" : Arrays.stream(nums).collect(Collectors.joining()); // nums 배열을 문자열로 바꿔주고 연결 
        return answer;
    }
}
 