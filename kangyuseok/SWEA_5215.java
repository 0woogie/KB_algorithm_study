import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA5215_햄버거다이어트 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //입력 받기 위한 스트림
        StringTokenizer st = new StringTokenizer(br.readLine()); //라인으로 입력 받음

        int T = Integer.parseInt(st.nextToken()); //테스트 케이스를 입력 받음


        for(int t=1;t<=T;t++){ //테스트 케이스 만큼 반복함
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); //햄버거 재료의 갯수
            int L = Integer.parseInt(st.nextToken()); //최대 칼로리

            int [] flavor = new int[N+1]; //힘버거 재료당 맛
            int [] kcal = new int[N+2]; //햄버거 재료당 칼로리

            int []dp = new int[L+1]; //칼로리에 따른 최대 맛 저장 ex) dp[1000] = 100 => 1000kcal의 최대 맛은 100이다.

            for(int i = 1;i<=N;i++){//N개의 맛과 이에 해당하는 칼로리 저장
                st = new StringTokenizer(br.readLine());
                flavor[i] = Integer.parseInt(st.nextToken()); //i번째 맛 저장
                kcal[i] = Integer.parseInt(st.nextToken());//i번째 칼로리 저
            }

            for(int i = 1;i<=N;i++){ //N개의 재료를 순회
                for(int j=L; j-kcal[i] >= 0; j--){ // 유효한 칼로리만 순회, 굳이 칼로리를 0부터 순회할 필요가 없다. 범위에 들어가는 칼로리만 순회
                    dp[j] = Math.max(dp[j], dp[j-kcal[i]]+flavor[i]); //j칼로리의 최대 맛은 이전에 j칼로리의 맛과 j칼로리에서 i칼로리를 뺀 값에서 i번째 맛을 합친 것 중에 더 큰것이다.
                }
            }

            System.out.println("#"+t +" "+ dp[L]);
        }
    }
}
