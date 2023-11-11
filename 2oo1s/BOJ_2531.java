import java.io.*;
import java.util.*;

// 109분 ... HashSet에 꽂히는 바람에
public class BOJ_2531 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());   // 접시의 수
        int d = Integer.parseInt(st.nextToken());   // 초밥 가짓수
        int k = Integer.parseInt(st.nextToken());   // 연속해서 먹는 접시의 수
        int c = Integer.parseInt(st.nextToken());   // 쿠폰 번호

        int[] dish = new int[N + k - 1];
        for (int i = 0; i < N; i++)
            dish[i] = Integer.parseInt(br.readLine());

        for (int i = N; i < N + k - 1; i++)
            dish[i] = dish[i - N];  // 싸이클로 초밥이 이뤄져있어서 슬라이드 옮길 때 편하려고 배열 뒤에 연장

        int[] eat = new int[d + 1];
        int cnt = 1; // 쿠폰에 해당하는 접시는 먹었다고 가정
        eat[c]++;

        int s = 0;
        int e = k;  // 윈도우 초기 설정
        for (int i = s; i < k; i++) {
            if (eat[dish[i]] == 0)
                cnt++;
            eat[dish[i]]++;
        }

        int temp = cnt; // 현재 dish[0] ~ dish[k-1]까지의 초밥 종류 임시로 저장
        for (int i = e; i < dish.length; i++) {
            eat[dish[s]]--;
            /*
            슬라이드 첫번째 초밥을 없앴는데 해당 종료의 초밥이 0개가 되었다면,
            슬라이드를 오른쪽으로 한칸 옮겼을 때 옮긴 슬라이드에는 해당 종료의 초밥이 없다는 뜻
            그래서, temp(=초밥의 종류)를 감소
             */
            if (eat[dish[s]] == 0)
                temp--;
             /*
             슬라이드를 오른쪽으로 한 칸 옮겼을 때, 맨 오른쪽에 새로 추가되는 초밥 처리
             슬라이드를 옮겼는데 새로 추가된 초밥의 종류를 현재까지 0개 먹었다면,
             현재 temp에 해당 종류의 초밥은 카운트가 안된 것이므로 증가
             */
            if (eat[dish[i]] == 0)
                temp++;
            eat[dish[i]]++;

            cnt = Math.max(cnt, temp);
            s++;
        }
        System.out.println(cnt);
    }
}