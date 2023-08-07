import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2828 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int tail = 1;
        int head = m;
        st = new StringTokenizer(br.readLine());
        int j = Integer.parseInt(st.nextToken());

        int move = 0;
        for(int i=0;i<j;i++){
            st = new StringTokenizer(br.readLine());
            int apple = Integer.parseInt(st.nextToken());
            if(head < apple){
                while(head < apple){
                    head++;
                    tail++;
                    move++;
                }
            }
            else if(apple < tail){
                while(apple < tail){
                    head--;
                    tail--;
                    move++;
                }
            }
        }
        System.out.println(move);
    }
}
