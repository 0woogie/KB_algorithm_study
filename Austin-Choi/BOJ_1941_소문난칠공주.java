package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_1941_소문난칠공주 {
	public static int[] di = {-1, 0, 1, 0};
	public static int[] dj = {0, 1, 0, -1};
	final static int MAX_PRINCESS = 7;
	public static String[][] map;
	public static boolean[][] visited;
	public static boolean[][] checkVisited;
	public static int[] sevenPrincesses;
	public static int answer = 0;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		map = new String[5][5];
		visited = new boolean[5][5];
		sevenPrincesses = new int[7];
		
		for(int i = 0; i<5; i++) {
			String[] st = br.readLine().split("");
			for(int j = 0; j<5; j++) {
				map[i][j] = st[j];
			}
		}
		
		bt(0,0,0);
		
		System.out.println(answer);
	}

	public static void bt(int index, int som, int size) {
		if(size == 7) {
			if(som>=4) {
				if(isClose())
					answer++;
				return;
			}
			return;
		}
		for(int i = index; i<25; i++) {
			//해당 학생 선택했을 때
			visited[i/5][i%5] = true;
			sevenPrincesses[size] = i;
			//조합 구하기
			if(map[i/5][i%5].equals("S"))
				bt(i + 1, som + 1, size + 1);
			else
				bt(i+1, som, size +1);
			//해당 학생 선택되지 않았을 경우 확인 위해 false
			visited[i/5][i%5] = false;
		}
	}
	//bfs
	//조건에 만족하는 7명이 모두 인접해있는지 
	//5x5칸 모두 체크해서 
	public static boolean isClose() {
		int size = 1;
		checkVisited = new boolean[5][5];
		Queue<Integer> q = new LinkedList<>();
		q.add(sevenPrincesses[0]);
		
		while(!q.isEmpty()) {
			int current = q.poll();
			checkVisited[current/5][current%5] = true;
			
			for(int dir = 0; dir <4 ; dir++) {
				int ni = (current/5) + di[dir];
				int nj = (current%5) + dj[dir];
				//범위밖임
				if(ni < 0 || nj < 0 || ni > 4 || nj > 4)
					continue;
				//이미 확인한 학생
				if(checkVisited[ni][nj])
					continue;
				//붙어있는데 공주아님
				if(!visited[ni][nj])
					continue;
				size++;
				checkVisited[ni][nj] = true;
				q.add(ni*5+nj);
			}
		}
		if(size == 7)
			return true;
		else
			return false;
	}
}
