import java.util.*;
class Solution {
    public ArrayList<Integer> solution(int[] answers) {
        int []one = {1, 2, 3, 4, 5};
        int [] two = {2, 1, 2, 3, 2, 4, 2, 5};
        int [] three = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
        
        int a=0, b=0, c=0;
        for(int i = 0;i<answers.length;i++){
            if(answers[i] == one[i % one.length])a++;
            if(answers[i] == two[i % two.length])b++;
            if(answers[i] == three[i % three.length])c++;
        }
        
        ArrayList<Integer>list = new ArrayList<>();
        if(a > b && a>c)list.add(1);
        else if(b > a && b > c)list.add(2);
        else if(c > a && c > b)list.add(3);
        else if(a == b && a > c){
            list.add(1);
            list.add(2);
        }
        else if(a == c && a > b){
            list.add(1);
            list.add(3);
        }
        else if(b == c && b > a){
            list.add(2);
            list.add(3);
        }
        else {
            list.add(1);
            list.add(2);
            list.add(3);
        }
        
        return list;
    }
}