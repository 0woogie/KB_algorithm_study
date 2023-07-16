package study;
import java.util.Scanner;

public class S5215 {

	static int result;
	static int N, M;
	static int L;
	static int[][] arr;
	static int[][] output;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++) {
			N = sc.nextInt(); //����� ��
			L = sc.nextInt(); //���� Į�θ�
			
			arr = new int[N][2];
			for(int i=0; i<N; i++) {
				arr[i][0] = sc.nextInt(); //��� i�� ����
				arr[i][1] = sc.nextInt(); //��� i�� Į�θ�
			}
			result = 0;
			for(int i=1; i<=N; i++) { //��� N�� �߿��� 1�� �̴� ��� ~ N�� �̴� ������
				M = i;
				output = new int[M][2]; //���� ��� M���� ������ Į�θ� ������ ���� �迭 output
				dfs(0, 0); //depth, start
			}
			System.out.println("#"+test_case+" " + result);
		}
	}

	static void dfs(int depth, int start) {
		if(depth==M) { //���� ����(M���� �̾��� ��)
			//����� ���� �հ� Į�θ� �� ���
			int score = 0;
			int cal = 0;
			for(int i=0; i<M; i++) {
				score += output[i][0];
				cal += output[i][1];
			}
			//���� Į�θ� ���ϸ鼭 ���� ����� �ְ� �������� ������ ����
			if(cal<=L && score>result) result = score;
			return;
		}
		for(int i=start; i<N; i++) {
			output[depth][0] = arr[i][0];
			output[depth][1] = arr[i][1];
			dfs(depth+1, i+1);
		}
	}

}
