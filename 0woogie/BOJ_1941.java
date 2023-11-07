package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1941 {
	
	static char[][] board;
	static boolean[][] visited; //���ַ� ���õ� �л� ǥ��
	static Student[] students; //�л� 25�� ���� �迭
	static int answer;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		board = new char[5][5];
		visited = new boolean[5][5];
		students = new Student[25];
		int index = 0;
		for(int i=0; i<5; i++) {
			String s = sc.next();
			for(int j=0; j<5; j++) {
				students[index++] = new Student(i, j); //�� 25���� �л��� ���� ��ġ ������ ����
				board[i][j] = s.charAt(j);
			}
		}
		DFS(0, 0); //now, count
		System.out.println(answer);
	}
	
	static void DFS(int now, int count) { //now: ���� ���° �л��� ������� �ϰ� �ִ���, count: ���ְ� 7���� �Ǿ����� �Ǵ�
		if(count>7) { //���ְ� 7���� �Ѿ��ٸ�
			return;
		}
		if(count==7) { //7������ ���
			//"�ҹ��� ĥ����"�� ��Ģ�� �����ߴ��� üũ
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(visited[i][j]) {
						if(BFS(i,j))
							answer++; //"�ҹ��� ĥ����"�� �Ἲ�ϴ� ��� 
						return;
					}
				}
			}
			return;
		}
		if(now==25) { //��� �л��� �� Ȯ���� ���
			return;
		}
		Student student = students[now];
		//�ش� �л��� ���ַ� ����
		visited[student.x][student.y] = true;
		DFS(now+1, count+1);
		visited[student.x][student.y] = false;
		//�ش� �л��� ���ַ� �������� ����
		DFS(now+1, count);
	}
	
	static boolean BFS(int a, int b) {
		boolean flag = false;
		int count1 = 0; //�ο� üũ
		int count2 = 0; //�ټ��� üũ
		boolean[][] check = new boolean[5][5]; //BFS���� �̹� �湮�� ������ üũ
		Queue<int[]> queue = new LinkedList<>();
		//ť�� ������ count ó���� �湮 ó���ϱ�
		queue.add(new int[] {a, b});
		check[a][b] = true;
		count1++;
		if(board[a][b]=='S')
			count2++;
		
		while(!queue.isEmpty()) {
			int[] tmp = queue.poll();
			int x = tmp[0];
			int y = tmp[1];
			for(int i=0; i<4; i++) {
				int nx = x + dx[i];
				int ny = y + dy[i];
				if(nx<0 || nx>=5 || ny<0 || ny>=5) //���� ������ ������
					continue;
				if(visited[nx][ny] && !check[nx][ny]) { //���ַ� ���õ� �л� && ���� BFS�� �湮���� ���� �л�
					queue.add(new int[] {nx, ny});
					check[nx][ny] = true;
					count1++;
					if(board[nx][ny]=='S')
						count2++;
				}
			}
		}
		if(count1==7 && count2>=4) //7���� ���ְ� ��� �������ְ� �̴ټ��� �л��� 4�� �̻��� ���
			flag = true;
		return flag;
	}
}

class Student {
	int x;
	int y;
	
	public Student(int x, int y) {
		this.x = x;
		this.y = y;
	}
}