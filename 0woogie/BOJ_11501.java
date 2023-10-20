package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11501 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=0; t<T; t++) {
			int N = Integer.parseInt(br.readLine());
			int[] arr = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			int maxValue = 0;
			long result = 0;
			for(int i=N-1; i>=0; i--) { //�ڿ������� üũ�ϱ� (���� ��� �ֽ��� �ְ����� �� ������������ ���� �˱� ������)
				if(arr[i]<maxValue)
					result += maxValue - arr[i]; //�ְ������� ���� �ְ��� ������ ���׸�ŭ result�� �ݿ�
				else
					maxValue = arr[i]; //�ְ��� update
			}
			System.out.println(result);
		}	
	}
}
