import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        char []ch = st.nextToken().toCharArray();
        Queue<Character>q = new LinkedList<>();

        char[]a = {'A', 'A', 'A', 'A'};
        char[]b = {'B', 'B'};

        int cnt=0;
        int size = 0;
        int idx = 0;
        for(int i=0;i<ch.length;i++){
            if(ch[i] == '.' || i == ch.length-1){
                if(i == ch.length-1 && ch[i] == '.')size = i - idx;
                else if(i == ch.length-1)size = i -idx +1;
                else size= i - idx;

                idx = i+1;

                if(size % 2 == 0){
                    while(size != 0){
                        if(size >= 4){
                            size -= 4;
                            for(int j=0;j<4;j++)q.add('A');
                        }
                        else {
                            size-=2;
                            for(int j = 0;j<2;j++)q.add('B');
                        }
                    }
                    if(ch[i] == '.')q.add('.');
                }
                else {
                    System.out.println(-1);
                    q.clear();
                    break;
                }

            }
        }
        int aa = q.size();
        for(int i=0;i<aa;i++){
            System.out.print(q.peek());
            q.remove();
        }
    }
}
