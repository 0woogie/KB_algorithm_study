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

        int n = Integer.parseInt(st.nextToken());
        int []arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++)arr[i]=Integer.parseInt(st.nextToken());

        Stack<Integer>stack = new Stack<>();
        StringBuilder sb = new StringBuilder();
        for(int i=n-1;i>=0;i--){
            if(stack.isEmpty()){ //처음에 스택이 비어있을 경우
                stack.push(arr[i]);
                arr[i]=-1;
            }
            else if(arr[i]<stack.peek()){ //현재 top보다 작을 경우
                int val = stack.peek();
                stack.push(arr[i]);
                arr[i] = val;
            }
            else if(arr[i] >= stack.peek()){ //현재 top보다 클 경우
                while(!stack.isEmpty() && arr[i]>= stack.peek()) stack.pop(); //자신보다 큰 수가 나올 때 까지 스택 pop
                if(stack.isEmpty()){
                    stack.push(arr[i]);
                    arr[i]=-1;
                }
                else{
                    int val = stack.peek();
                    stack.push(arr[i]);
                    arr[i] = val;
                }
            }
        }
        for(int i: arr){
            sb.append(i).append(' ');
        }
        System.out.println(sb);

    }
}

