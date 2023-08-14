import java.util.Arrays;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        String[] arr = new String[numbers.length];
        for(int i=0; i<numbers.length; i++){
            arr[i] = String.valueOf(numbers[i]);
        }
        
        Arrays.sort(arr, (o1,o2)->(o2+o1).compareTo(o1+o2)); //내림차순으로 정렬 
        
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            sb.append(s);
        }
        
        answer = sb.toString();
        
        if(answer.charAt(0)=='0') return "0"; //결과가 000...과 같은 경우 -> 0
        return answer;
    }
}