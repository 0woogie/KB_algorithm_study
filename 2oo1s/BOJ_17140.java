package algorithm.boj;

import java.io.*;
import java.util.*;

public class BOJ_17140 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		int r = Integer.parseInt(st.nextToken()); // 행
		int c = Integer.parseInt(st.nextToken()); // 열
		int k = Integer.parseInt(st.nextToken()); // 목표 수

		int[][] arr = new int[101][101]; // 행과 열의 최대 크기는 100

		for (int i = 1; i < 4; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 1; j < 4; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		// System.out.println(Arrays.deepToString(arr));

		int time = 0;
		int rowCnt = 3;
		int colCnt = 3;

		while (true) {
			if (arr[r][c] == k) // while문 종료 조건 1 : arr[r][c] 값이 k와 같을 때
				break;

			if (time == 100) { // while문 종료 조건 2 : 시간이 100초가 될 때
				time = -1;
				break;
			}

			time++; // 연산 수행할 때마다, 시간 1초 증가

			if (rowCnt >= colCnt) { // R 연산
				int curColCount = colCnt; // 현재 열의 개수 저장
				colCnt = 0; // 연산 종료 후, 열의 최대 길이 저장용

				for (int i = 1; i <= rowCnt; i++) {
					HashMap<Integer, Integer> frequency = new HashMap<>(); // (숫자 : 등장한 횟수) 저장용
					List<Node> list = new ArrayList<>(); // 등장한 횟수, 수의 크기에 따라 정렬하기 위한 리스트

					for (int j = 1; j <= curColCount; j++) { // 숫자랑 등장횟수 저장
						if (arr[i][j] != 0)
							frequency.put(arr[i][j], frequency.getOrDefault(arr[i][j], 0) + 1);
					}

					for (Map.Entry<Integer, Integer> e : frequency.entrySet()) // frequency에 담긴 요소들 리스트에 저장...정렬하려고
						list.add(new Node(e.getKey(), e.getValue()));

					Collections.sort(list); // 등장횟수 오름차순, 등장 횟수가 같으면 숫자 오름차순

					int idx = 0; // 연산 후, 열의 길이를 저장용

					for (int j = 0; j < list.size(); j++) {
						// 행열 최대 길이가 100인데, j가 51 이상이면 list에 있는 원소들 각각의 key, value 저장하기 위해서
						// j = 51 이상부터 최소 102만큼의 길이가 필요
						if (j == 50)
							break;

						// list에는 숫자랑 등장횟수가 하나로 묶여있으니까, 각각 따로 저장하려면 배열 2칸 차지
						int length = (j + 1) * 2; // 값을 저장할 배열의 인덱스 계산
						idx = length; // 인덱스가 결국 열의 최대 길이가 되므로 해당 값으로 갱신

						Node node = list.get(j);

						arr[i][(j + 1) * 2 - 1] = node.value;
						arr[i][(j + 1) * 2] = node.count;
					}

					/*
					 * 여기서부터 이해 불가
					 * 0으로 메꿔주는 부분인 듯?
					 */
					
					colCnt = Math.max(colCnt, idx); // 하나의 행을 연산 후에 얻은 열의 길이와 현재까지 갱신된 열의길이 중 더 큰 값으로 갱신
					// System.out.println(idx + " : " + curColCount);

					// 연산 후 열의 길이가 줄어들었다면 이전에 저장된 값을 0으로 바꿔주기 위해 반복문 수행
					// 앞서 계산한 연산 후 열의 길이+1번 인덱스부터 연산 전 열의길이까지 반복문 수행
					for (int j = idx + 1; j <= curColCount; j++) {
						// System.out.println("for문 집입 !! " + idx + " : " + j + " : " + curColCount);
						if (arr[i][j] != 0) // 0으로 메꿔주기
							arr[i][j] = 0;
					}
				}
			} else { // C 연산 ... R연산이랑 똑같은 로직
				int curRowCount = rowCnt;
				rowCnt = 0;

				for (int j = 1; j <= colCnt; j++) {
					HashMap<Integer, Integer> frequency = new HashMap<>();
					List<Node> list = new ArrayList<>();

					for (int i = 1; i <= curRowCount; i++) {
						if (arr[i][j] != 0)
							frequency.put(arr[i][j], frequency.getOrDefault(arr[i][j], 0) + 1);
					}

					for (Map.Entry<Integer, Integer> e : frequency.entrySet())
						list.add(new Node(e.getKey(), e.getValue()));

					Collections.sort(list);

					int idx = 0;
					for (int i = 0; i < list.size(); i++) {
						if (i == 50)
							break;

						int length = (i + 1) * 2;
						idx = length;

						Node node = list.get(i);

						arr[length - 1][j] = node.value;
						arr[length][j] = node.count;
					}

					rowCnt = Math.max(rowCnt, idx);

					for (int i = idx + 1; i <= curRowCount; i++) {
						if (arr[i][j] != 0)
							arr[i][j] = 0;
					}
				}
			} // else문
		} // while문
		System.out.println(time);
	}

	static class Node implements Comparable<Node> { // 정렬에 사용하기 위한 노드 클래스
		int value; // 숫자를 저장
		int count; // 숫자가 등장한 횟수를 저장

		public Node(int value, int count) {
			this.value = value;
			this.count = count;
		}

		@Override
		public int compareTo(Node n) {
			if (this.count > n.count) // 등장횟수가 우선순위
				return 1;
			else if (this.count == n.count) {
				if (this.value > n.value) // 등장횟수가 같으면, 숫자 크기가 다음 정렬 조건
					return 1;
				else
					return -1;
			} else
				return -1;
		}
	}
}