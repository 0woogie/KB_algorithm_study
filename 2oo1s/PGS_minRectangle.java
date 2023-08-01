import java.util.*;

class Solution {
	public int solution(int[][] sizes) {
		int answer = 0;
		int posW = 0;
		int posH = 0;
		int temp = 0;

		for (int i = 0; i < sizes.length; i++) {
			posW = Math.max(posW, sizes[i][0]);
			posH = Math.max(posH, sizes[i][1]);
		}

		if (posW >= posH) {
			Arrays.sort(sizes, (x, y) -> (x[1] == y[1] ? x[0] - y[0] : x[1] - y[1]));
			temp = sizes[0][1];
		} else {
			Arrays.sort(sizes, (x, y) -> (x[0] == y[0] ? x[1] - y[1] : x[0] - y[0]));
			temp = sizes[0][0];
		}

		for (int i = 0; i < sizes.length; i++) {
			if (posW >= posH) { // 가장 긴 변이 가로일 때
				if (sizes[i][0] >= sizes[i][1]) { // 들어오는 명함의 가로가 더 길 때,
					if (sizes[i][1] >= temp) {
						temp = sizes[i][1];
					}
				} else {
					if (sizes[i][0] > temp)
						temp = sizes[i][0];
				}
				answer = posW * temp;
			} else { // 가장 긴 변이 세로일 때
				if (sizes[i][1] >= sizes[i][0]) { // 들어오는 명함의 세로가 더 길 때,
					if (sizes[i][0] >= temp) {
						temp = sizes[i][0];
					}
				} else {
					if (sizes[i][1] > temp)
						temp = sizes[i][1];
				}
				answer = posH * temp;
			}
		}
		return answer;
	}
}