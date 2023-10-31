package com.algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583_싸이버개강총회 {
	public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        //입력받기
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        //set 만들고, 뒤에서 비교
        Set<String> before = new HashSet<>();
        Set<String> after = new HashSet<>();
        Set<String> nameSet = new HashSet<>();
        String str = null;
        
        //채팅 기록을 읽어오면서 출석 여부 확인
        while((str = br.readLine()) != null) {
            String[] arr = str.split(" ");
            String time = arr[0];
            String name = arr[1];

            nameSet.add(name);
            if(S.compareTo(time) >= 0) {
                before.add(name);
            }else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) {
                after.add(name);
            }
        }

        //이름이 before와 after에 동시에 속하는 경우(출석 인정)
        int ans = 0;
        for(String name : nameSet) {
            if(before.contains(name) && after.contains(name)) {
                ans += 1;
            }
        }
        //출석이 확인된 학회원의 인원 수 출력
        System.out.println(ans);
    }
}
