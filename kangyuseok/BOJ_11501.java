import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class 연습용 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        while(t>0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int []arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for(int i=0;i<n;i++)arr[i] = Integer.parseInt(st.nextToken());

            int big=-1; //초기값
            long cost=0; //long으로 안하면 틀림 문제 조건이 n = 1백만, 주식 가격 = 1만 이므로
            for(int i=n-1;i>=0;i--){ //역방향 탐색
                if(big < arr[i]){ //역방향으로 탐색하면서 현재 최고값보다 더 큰 주식가격을 발견하면 big값 갱신
                    big = arr[i];
                }
                else if(big>= arr[i]){ //역방향으로 탐색하면서 현재 최고값보다 작거나 같으면 주식 다 팔음
                    cost += big - arr[i];
                }
            }
            System.out.println(cost);
            t--;
        }
    }
}

