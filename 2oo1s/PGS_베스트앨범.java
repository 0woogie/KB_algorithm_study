import java.util.*;

class Solution {
	public int[] solution(String[] genres, int[] plays) {
		ArrayList<Integer> answer = new ArrayList<>();
		HashMap<String, Integer> genreAndTotPlays = new HashMap<>(); // 장르별 총 재생횟수
		HashMap<String, HashMap<Integer, Integer>> music = new HashMap<>(); // 장르에 속하는 노래 및 재생횟수

		for (int i = 0; i < plays.length; i++) {
			if (!genreAndTotPlays.containsKey(genres[i])) { // 처음 저장하는 장르의 노래일 경우
				HashMap<Integer, Integer> temp = new HashMap<>();

				temp.put(i, plays[i]); // 노래 고유번호랑 재생횟수를 세트로 저장
				music.put(genres[i], temp); // 현재 장르를 key 값으로 넣고, temp를 value 값으로 저장
				genreAndTotPlays.put(genres[i], plays[i]);

			} else { // 이미 저장한 장르의 노래일 경우
				// System.out.println(music.get(genres[i]));
				music.get(genres[i]).put(i, plays[i]);

				// genreAndPlayNum에서 현재 장르의 총 재생 횟수를 가져와서
				// 현재 노래의 재생 횟수 plays[i]를 더하여 업데이트
				genreAndTotPlays.put(genres[i], genreAndTotPlays.get(genres[i]) + plays[i]);
			}
		}

		List<String> genreSet = new ArrayList<>(genreAndTotPlays.keySet()); // classic, pop 저장

		// 장르별 총 재생횟수 내림차순 정렬
		Collections.sort(genreSet, (s1, s2) -> genreAndTotPlays.get(s2) - genreAndTotPlays.get(s1));

		for (String genre : genreSet) {
			// System.out.println(music.get(genre)); // {1=600, 4=2500}
			HashMap<Integer, Integer> map = music.get(genre); // 장르, 재생횟수 정보
			List<Integer> songIdx = new ArrayList<>(map.keySet()); // 고유번호(인덱스) 저장

			// 노래별 각 재생횟수 내림차순
			Collections.sort(songIdx, (s1, s2) -> map.get(s2) - map.get(s1));

			answer.add(songIdx.get(0)); // 가장 많이 재생된 노래 고유번호 저장

			if (songIdx.size() > 1) // 현재 장르에 노래가 2곡 이상일 경우
				answer.add(songIdx.get(1));
		}
		// 리스트에 저장된 결과를 정수 배열로 변환
		return answer.stream().mapToInt(i -> i).toArray();
	}
}
