import java.util.*;
class Solution {
    public int solution(int[] citations) {
        Arrays.sort(citations);
        for(int i=0;i<citations.length;i++){
            int h_index = citations.length-i;
            if(h_index <=citations[i])return h_index;
        }
        return 0;
    }
}