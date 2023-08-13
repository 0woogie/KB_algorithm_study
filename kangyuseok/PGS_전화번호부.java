import java.util.*;
class Solution {
    public boolean solution(String[] phone_book) {
        Map<String, Integer>m = new HashMap<>();
        for(int i=0;i<phone_book.length;i++)
            m.put(phone_book[i], 1);
        for(int i=0;i<phone_book.length;i++){
            for(int j=1;j<phone_book[i].length();j++){
                if(m.containsKey(phone_book[i].substring(0, j))){
                    System.out.println(phone_book[i]);
                    System.out.println(phone_book[i].substring(0, j));
                    return false;
                }
            }
        }
        return true;
    }
}