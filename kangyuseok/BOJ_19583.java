import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
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

        int [][]timeSet = new int[3][2]; //0 : 총회 시작, 1 : 총회 끝 , 2 : 스트리밍 끝

        for(int i=0;i<3;i++){
            String temp = st.nextToken();
            timeSet[i][0] = Integer.parseInt(temp.substring(0, 2));
            timeSet[i][1] = Integer.parseInt(temp.substring(3, 5));
        }

        Set<String>set = new HashSet<>();
        int start = timeSet[0][0] * 60 + timeSet[0][1];
        int end = timeSet[1][0]*60 + timeSet[1][1];
        int streamingEnd = timeSet[2][0]*60 + timeSet[2][1];

        String str;
        int cnt=0;
        while((str = br.readLine()) != null) {
            st = new StringTokenizer(str);
            String chatting = st.nextToken();
            String name = st.nextToken();
            int Time = Integer.parseInt(chatting.substring(0, 2));
            int Minute = Integer.parseInt(chatting.substring(3, 5));

            int curTime = Time*60 + Minute;

            if(curTime <= start)set.add(name);
            else if(curTime >= end && curTime <= streamingEnd && set.contains(name)){
                cnt++;
                set.remove(name);
            }
        }
        System.out.println(cnt);
    }
}


