import java.util.*;
class Solution {
    public String solution(int[] numbers) {
        String[] nums = new String[numbers.length];
        String answer = "";
        boolean zero=true;
        for(int i=0;i<numbers.length;i++){
            nums[i] = String.valueOf(numbers[i]);
            if(numbers[i]!=0)zero=false;
        }
        Arrays.sort(nums, new Comparator<String>(){
            @Override
            public int compare(String s1, String s2){
                return (s2+s1).compareTo(s1+s2);
            }
        });
        for(int i=0;i<nums.length;i++)answer+=nums[i];
        if(zero)answer="0";
        return answer;
    }
}
