package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_19583 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        String S = st.nextToken();
        String E = st.nextToken();
        String Q = st.nextToken();

        Set<String> before = new HashSet<>(); //개강총회 이전에 입장한 학생 담는 Set
        Set<String> after = new HashSet<>(); //개강총회 이후에 퇴장한 학생 담는 Set
        
        String str = null;
        while((str = br.readLine()) != null) { //종종 써먹는 syntax
            String[] s = str.split(" ");
            String time = s[0];
            String name = s[1];

            if(S.compareTo(time) >= 0) {
                before.add(name);
            }else if(E.compareTo(time) <= 0 && Q.compareTo(time) >= 0) {
                after.add(name);
            }
        }

        int result = 0;
        for(String name : before) { //개강총회 전에 입장한 학생들만 확인하면 됨
            if(after.contains(name)) {
                result += 1;
            }
        }
        System.out.println(result);
	}

}
