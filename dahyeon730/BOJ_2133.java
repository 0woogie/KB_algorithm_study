package algorithm.Team08;

import java.io.*;

class Main {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); //N 입력받기

        int[] DP = new int[N + 1];  // N은 1부터 시작하기 때문에 N+1의 크기를 갖는 배열 선언

        // N이 홀수일 경우, 2x1 or 1x2의 타일로 채울 수 없기 때문에 0을 출력하고 return
        if (N % 2 != 0) {
            System.out.println(0);
            return;
        }

        // 타일이 없을 경우 ???
        DP[0] = 1;

        // 3x1 타일을 채울 수 있는 경우 -> 0
        DP[1] = 0;

        // 3x2 타일을 채울 수 있는 경우 -> 3
        DP[2] = 3;

        // N은 짝수만 될 수 있기 때문에 2씩 증가
        for (int i = 4; i <= N; i += 2) {
            DP[i] = DP[i - 2] * DP[2];
            for (int j = i - 4; j >= 0; j -= 2) {
                DP[i] = DP[i] + (DP[j] * 2);
            }
        }

        // 결과값 출력
        System.out.println(DP[N]);
    }
}