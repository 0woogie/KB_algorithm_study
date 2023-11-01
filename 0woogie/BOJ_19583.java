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

        Set<String> before = new HashSet<>(); //������ȸ ������ ������ �л� ��� Set
        Set<String> after = new HashSet<>(); //������ȸ ���Ŀ� ������ �л� ��� Set
        
        String str = null;
        while((str = br.readLine()) != null) { //���� ��Դ� syntax
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
        for(String name : before) { //������ȸ ���� ������ �л��鸸 Ȯ���ϸ� ��
            if(after.contains(name)) {
                result += 1;
            }
        }
        System.out.println(result);
	}

}
