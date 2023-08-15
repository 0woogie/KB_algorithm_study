import java.util.*;

//h번 이상 인용된 논문이 h이상
//문제 이해하는데 오래걸림
class Solution {
    public int solution(int[] cit) {
        //Comparator 쓰려면 primitive 타입이면 안됨
        ArrayList<Integer> l = new ArrayList<>();
        for(int i : cit){
            l.add(i);
        }
        Collections.sort(l, Collections.reverseOrder());

        for(int i = 0; i<l.size(); i++){
            //0 1 2 3 4 
            //8 7 6 4 0
            //h번 이하 인용된건 고려할 필요 없음.
            //내림차순 정렬이므로
            //해당 값이 인덱스+1값보다 작거나 같으면 인용횟수와 인용값의 h-index를 만족함
            if(l.get(i)<=i+1)
                return i;
        }
        return l.size();
    }
}
