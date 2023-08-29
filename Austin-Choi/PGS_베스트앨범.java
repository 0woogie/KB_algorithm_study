import java.util.*;

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        Map<String, Integer> m = new HashMap<>();
        // hashmap에 장르별 play횟수를 중첩해서 더해줌
        // 키값은 genre value는 plays
        // 빈 set 준비 장르별 총 play수 저장할 것.
        //1. 키값을 순회하면서 만약 set에 있으면 
        for(int i = 0; i<genres.length; i++){
            //getOrDefault(k,default) 찾는 키가 존재한다면 키 값을 반환하고 아니면 default값을 반환함.
            m.put(genres[i], m.getOrDefault(genres[i], 0) + plays[i]);
        }
        //1450 3100 1450 1450 3100 
        // for(String s : genres){
        //     System.out.print(m.get(s)+" ");
        // }
        // System.out.println();
        
        //2. 위에서 구한 keyset으로 이뤄진 앨범 장르 순서인 arraylist 만들기
        ArrayList<String> genreOrder = new ArrayList<>();
        for(String s : m.keySet()){
            genreOrder.add(s);
        }
        //현재 m에는 1450 3100 1450 1450 3100 이므로 String형 key값으로 접근해서
        //y-x, 내림차순으로 정렬한다.
        Collections.sort(genreOrder, (x,y)->m.get(y)-m.get(x));
        
        //노래마다 고유 인덱스 정답처리하는 ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0; i < genreOrder.size(); i++) {
            String g = genreOrder.get(i);
            
            //해당 장르의 음악 중에서 play횟수가 가장 큰 인덱스를 찾는다
            int max = 0;
            int firstIdx = -1; //인덱스로 나올수 없는 수로 초기화
            for(int j = 0; j < genres.length; j++) {
                //현재 인덱스에 있는 장르와 같은 장르이며
                if(g.equals(genres[j]) & max < plays[j]) {
                    max = plays[j];
                    firstIdx = j;
                }
            }
            
            //해당 장르의 음악 중에서 play횟수가 두번째로 큰 인덱스를 찾는다.
            max = 0;
            int secondIdx = -1;//인덱스로 나올수 없는 수로 초기화
            for(int j = 0; j < genres.length; j++) {
                //첫번째랑 인덱스 같으면 안됨
                if(g.equals(genres[j]) & max < plays[j] & j != firstIdx) { 
                    max = plays[j];
                    secondIdx = j;
                }
            }
            
            list.add(firstIdx);
            if(secondIdx >= 0) //초기화 값이 -1이었으므로
                list.add(secondIdx); //secondIdx는 존재 할수도, 안할수도 있다.
        }
        
        int[] result = new int[list.size()];
        for(int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        
        return result;
    }
}
