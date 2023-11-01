import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_10942_팰린드롬?다른풀이 {
	public static int[] A;
	public static char[] cArr;
	//O(n)
	//원래 A[i] 선형탐색을 하며 각 요소를 중심으로 갖는 가장 긴 팰린드롬의 반지름이지만
	//패딩 때문에 A[i]는 길이가 됨.
	public static void palindrome(String s) {
		//r은 i-1 단계 까지의 모든 팰린드롬의 끝나는 인덱스 중 가장 큰 값
		int r = 0;
		//c는 i-1 단계 까지의 r의 값이 최대가 되게 하는 중심 인덱스를 저장함.
		int c = 0;
		for(int i = 0; i<s.length(); i++) {
			//i번째 문자가 i미만의 문자를 중심으로 하는 팰린드롬이 아님(자식 팰린드롬이 아니라는 것)
			if(r<i)
				A[i] = 0;
			//i번째 문자가 i미만의 문자를 중심으로 하는 팰린드롬의 요소이다.
			//ex. banana에서 i=1일때 a는 anana의 요소이다.
			else
//현재 팰린드롬 안의 팰린드롬 길이를 초기값으로 취해서 불필요한 계산을 줄임
// r-i는 현재 회문 범위 안에서 가질수 있는 길이의 최댓값
// A[2*c-i]는 c가 중심으로 있는 팰린드롬의 길이(항상 내부에서 최대값으로 계산됨)
//min 연산 이유는 A[2*c-i]가 r-i보다 긴 경우가 있음
				A[i] = Math.min(A[2*c-i], r-i);

//초기화된 A[i]값에서 1씩 팰린드롬의 길이를 늘려가며 
//양 끝단의 값이 같은지(팰린드롬인지)확인해서 
//팰린드롬이면 길이를 1씩 늘림
			while((i-A[i]-1>=0) 
					&& (i+A[i]+1<s.length()) 
					&& (cArr[i-A[i]-1]==cArr[i+A[i]+1]))
				A[i]++;
			
//i에서 A[i]+i 이상으로 팰린드롬 확장이 불가능한 경우
//현재 길이 최대값으로 지정되어있는 길이 r이 while문의 조건탈출 후 현재 탐색중인 팰린드롬 i보다 짧으니 갱신해주고
//현재 팰린드롬의 중심 i가 새로운 가장 긴 팰린드롬의 중심이 됨
			if(r<i+A[i]) {
				r = i + A[i];
				c = i;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		//짝수 길이의 팰린드롬을 위해 # 패딩을 넣어줘야 한다.
		sb.append("#");
		for(int i = 0; i<N; i++) {
			sb.append(st.nextToken()).append("#");
		}
	
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		cArr = sb.toString().toCharArray();
		A = new int[cArr.length];
		palindrome(sb.toString());
		
		sb = new StringBuilder();
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			//패딩 고려해서 바꿈
			int start = ((Integer.parseInt(st.nextToken())-1)*2)+1;
			int end = ((Integer.parseInt(st.nextToken())-1)*2)+1;
			int mid = (start+end) /2;
			//eeee
			//0  3
			//#e#e#e#e#
			// 1234567 
			
			if(A[mid]!=0)
				sb.append("1\n");
			else
				sb.append("0\n");
		}
		System.out.println(sb);

	}

}
