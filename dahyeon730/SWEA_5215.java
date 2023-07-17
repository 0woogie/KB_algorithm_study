package algorithm.Team01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
public class S_5215_햄버거다이어트 {
 
    static int N, L;
    static int maxScore=0;
    static int[][] ingredient;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        //테스트 케이스 입력받기
        int T = Integer.parseInt(br.readLine());
        
        //테스트 케이스만큼 반복
        for (int t = 1; t <= T; t++) {
        	//재료의 수와 제한 칼로리 입력받기
            st = new StringTokenizer(br.readLine(), " ");
            N = Integer.parseInt(st.nextToken());    
            L = Integer.parseInt(st.nextToken());    
 
            ingredient = new int[N][2]; //입력받을 배열
 
            //재료의 수만큼 반복 - 재료의 맛에 대한 점수와 칼로리 입력받기
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                ingredient[i][0] = Integer.parseInt(st.nextToken());
                ingredient[i][1] = Integer.parseInt(st.nextToken()); 
            }
 
            //계산할 메소드 만들기
            selectMaterial(0, 0, 0);
            
            //출력하기
            System.out.println("#" + t + " " + maxScore);
        }
    }
 
    public static void selectMaterial(int idx, int sumTaste, int sumCalorie) {
        //주어진 제한 칼로리를 초과한 경우
        if (sumCalorie > L) return;
        //주어진 제한 칼로리의 이하의 조합
        if (sumCalorie <= L) maxScore = Math.max(maxScore, sumTaste);
        //모든 조합을 확임
        if (idx == N) return;
        
        //사용할 햄버거 조합
        selectMaterial(idx + 1, sumTaste + ingredient[idx][0], sumCalorie + ingredient[idx][1]);
        //사용하지 않을 햄버거 조합
        selectMaterial(idx + 1, sumTaste, sumCalorie);
    }
 
}