import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] arr = new String[numbers.length];

        // 배열 내 원소들 문자화 
        for (int i = 0; i < arr.length; i++)
            arr[i] = String.valueOf(numbers[i]);
        
        // x = 17, y = 8 -> 178 vs 817 이런 식으로 비교해서 숫자를 내림차순으로 정렬
        Arrays.sort(arr, (x, y) -> (y + x).compareTo(x + y));

        // [0, 0, 0] -> 0만 출력되게
        if (arr[0].equals("0"))
            return "0";
        
        for (int i = 0; i < arr.length; i++)
            answer += arr[i];
        
        return answer;
    }
}