package study;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_1941 {
	
	static char[][] board;
	static boolean[][] visited; //공주로 선택된 학생 표시
	static Student[] students; //학생 25명에 대한 배열
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
				students[index++] = new Student(i, j); //총 25명의 학생에 대한 위치 정보를 저장
				board[i][j] = s.charAt(j);
			}
		}
		DFS(0, 0); //now, count
		System.out.println(answer);
	}
	
	static void DFS(int now, int count) { //now: 지금 몇번째 학생을 대상으로 하고 있는지, count: 공주가 7명이 되었는지 판단
		if(count>7) { //공주가 7명이 넘었다면
			return;
		}
		if(count==7) { //7공주인 경우
			//"소문난 칠공주"의 규칙을 만족했는지 체크
			for(int i=0; i<5; i++) {
				for(int j=0; j<5; j++) {
					if(visited[i][j]) {
						if(BFS(i,j))
							answer++; //"소문난 칠공주"를 결성하는 경우 
						return;
					}
				}
			}
			return;
		}
		if(now==25) { //모든 학생을 다 확인한 경우
			return;
		}
		Student student = students[now];
		//해당 학생을 공주로 선택
		visited[student.x][student.y] = true;
		DFS(now+1, count+1);
		visited[student.x][student.y] = false;
		//해당 학생을 공주로 선택하지 않음
		DFS(now+1, count);
	}
	
	static boolean BFS(int a, int b) {
		boolean flag = false;
		int count1 = 0; //인원 체크
		int count2 = 0; //다솜파 체크
		boolean[][] check = new boolean[5][5]; //BFS에서 이미 방문한 곳인지 체크
		Queue<int[]> queue = new LinkedList<>();
		//큐에 넣을때 count 처리와 방문 처리하기
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
				if(nx<0 || nx>=5 || ny<0 || ny>=5) //영역 밖으로 나가면
					continue;
				if(visited[nx][ny] && !check[nx][ny]) { //공주로 선택된 학생 && 아직 BFS로 방문하지 않은 학생
					queue.add(new int[] {nx, ny});
					check[nx][ny] = true;
					count1++;
					if(board[nx][ny]=='S')
						count2++;
				}
			}
		}
		if(count1==7 && count2>=4) //7명의 공주가 모두 인접해있고 이다솜파 학생이 4명 이상인 경우
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