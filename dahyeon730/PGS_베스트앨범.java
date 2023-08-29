package algorithm.Team07;

import java.util.*;

public class Solution {
	/*
	 1. 전체 재생 횟수 -> 장르(내림차순)
	 2. 장르 내 재생 횟수 -> 노래(내림차순)
	 */

    static class Music{
        String genre;
        int play;
        int idx;

        public Music(String genre, int play, int idx) {
            this.genre = genre;
            this.play = play;
            this.idx = idx;
        }
    }

    public static int[] solution(String[] genres, int[] plays) {

        HashMap<String, Integer> map = new HashMap<>(); //count를 하기 위해서 hashmap 사용
        for(int i=0; i<genres.length; i++){ //노래 개수만큼 반복
            map.put(genres[i], map.getOrDefault(genres[i], 0)+plays[i]);
            //map에 genres[i]값이 있다면 그 값에 + plays[i]를 하고, 없다면 그 값을 0이라고 생각하고 + plays[i]
        }

        // 1. 장르 선정
        ArrayList<String> genres_ordered = new ArrayList<>();
        while(map.size()!=0){
            int max = -1;
            String max_key = "";
            for(String key : map.keySet()){ //map에서 key만을 추출하는 함수, iterator메소드를 사용하여 가져온다.
                int tmp_cnt = map.get(key);
                if(tmp_cnt>max){
                    max = tmp_cnt;
                    max_key = key;
                }
            }
            genres_ordered.add(max_key);
            map.remove(max_key);
        }
		
        // 2. 장르 내 노래 선정
        ArrayList<Music> result = new ArrayList<>();
        for(String gern : genres_ordered){
            ArrayList<Music> list = new ArrayList<>();
            for(int i=0; i<genres.length; i++){ //노래 개수만큼 반복
                if(genres[i].equals(gern)){ 
                    list.add(new Music(gern, plays[i], i));
                }
            }
            Collections.sort(list, (o1, o2) -> o2.play - o1.play); // 내림차순 정렬
            result.add(list.get(0)); 	// 1개는 무조건 수록
            if(list.size()!=1){ 	// 더 수록할 곡이 있으면(==장르 내의 노래가 1개보다 많으면) 수록
                result.add(list.get(1));
            }
        }
        
        // print result
        int[] answer = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            answer[i] = result.get(i).idx;
        }
        return answer;
    }
}