package com.edu.algo.가장큰수;

import java.util.Arrays;
import java.util.Comparator;

public class PGS_가장큰수 {
	public static String solution(int[] numbers) {
        String answer = "";
        //String으로 변환한 numbers를 담기위한 nums배열 선언 및 초기화
        String[] nums = new String[numbers.length];
        
        int idxCnt = 0;
        for(int i : numbers) {
            //String.valueOf(int)를 사용해서 int를 String으로 parsing
            nums[idxCnt++] = String.valueOf(i);
        }

        //Comparator의 compare 오버라이딩 
        //인접한 두 스트링을 붙였을때 더 큰 값이 나오도록 정렬
        // 6 10 2 에서
        // 6 하고 10을 비교하면
        //610이 106보다 크므로
        // 6 10
        // 이제 10 2를 비교하면
        // 102보다는 210이 크므로
        // 2 10
        // 이제 6 2를 비교하면
        // 62 가 26보다 크므로
        // 최종 정렬 결과는 6 2 10 이 됨
        Arrays.sort(nums, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s2.concat(s1).compareTo(s1.concat(s2));
            }
        });
        
        // 큰 수가 되는 순서로 나열한 문자열을 차례대로 더해주고 리턴
        for(String i : nums) {
            answer += i;
        }
        //만약에 0으로 시작한다면 0000 이거나 00000000 이런식으로 어차피 0일 것이므로
        //"0"을 리턴
        if(answer.startsWith("0"))
            return "0";
        else
            return answer;
    }
}
