import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_12100_2048Easy {
	//북동남서
	//dir 0,1,2,3
	public static final int UP = 0;
	public static final int RIGHT = 1;
	public static final int DOWN = 2;
	public static final int LEFT = 3;
	public static int answer;
	public static int[][] map;
	//지도 크기
	public static int N;
	
	public static void main(String[] args) throws IOException {
		// 움직이는 방향 180도 방향에서부터 시작해서 스택에 넣기
		// 2개씩 pop해서 더해서 출력용 리스트 만들기
		// 출력하는 map 싹 비우고 움직이는 방향 좌상단부터 차례대로 넣어주기
		
		// 작동하는 2048 만들고 bfs로 각 방향 움직이는거 하나씩 해보면서 최대값 갱신하고 출력
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		answer = -1;
		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for(int i = 0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		dfs(0);
		
		//answer = getMax(map);
		System.out.println(answer);
	}
	public static void dfs(int depth) {
		if(depth == 5) {
			findMax();
			return;
		}
		int[][] temp = new int[N][N];
		for(int i = 0; i<N; i++) {
			//temp[i] = map[i]하면 ref가 연결되어버림
			//값만 필요하니까 clone 씀
			temp[i] = map[i].clone();
		}
		
		for(int i = 0; i<4; i++) {
			move(i);
			dfs(depth+1);
			for(int j = 0; j<N; j++)
				map[j] = temp[j].clone();
		}
	}
	public static void move(int dir) {
		List<Integer>[] updateData = new List[N];
		for(int i = 0; i<N; i++) {
			int[] inputArr = makeInputArr(i, dir);
			Stack<Integer> s = makeStack(inputArr, dir);
			updateData[i] = makePrinterList(s);
		}
		if(dir == UP) {
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					map[j][i] = updateData[i].get(j);
				}
			}
		}
		else if(dir == DOWN) {
			for(int i = 0; i<N; i++) {
				for(int j = N-1; j>0; j--) {
					map[j][i] = updateData[i].get(N-j-1);
				}
			}
		}
		else if(dir == LEFT) {
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					map[i][j] = updateData[i].get(j);
				}
			}
		}
		else {
			for(int i = 0; i<N; i++) {
				for(int j = N-1; j>0; j--) {
					map[i][j] = updateData[i].get(N-j-1);
				}
			}
		}
	}
	// stack에 넣을 방향에 따라 입력 배열 만들어주기
	// idx 현재 iterate하는 순번, dir 방향
	public static int[] makeInputArr(int idx, int dir) {
		int[] result = new int[N];
		if(dir % 2 == 0) {
			for(int i = 0; i<N; i++) {
				result[i] = map[i][idx]; 
			}
		}
		else {
			result = map[idx].clone();
		}
		return result;
	}
	// 연산용 스택 만들기 
	// arr 입력 배열, dir 방향
	public static Stack<Integer> makeStack(int[] arr, int dir){
		Stack<Integer> s = new Stack<Integer>();
		
		//up, left -> 0, 3
		//위쪽으로 이동하려면 아래쪽것부터 스택에 넣고 pop해서 계산하는 방식으로 리스트에 저장함
		//왼쪽으로 이동하려면 오른쪽것부터 스택에 넣고 ...
		if(dir % 3 == 0) {
			for(int i = N-1; i>0; i--) {
				s.add(arr[i]);
			}
		}
		//right, down -> 1, 2
		//오른쪽으로 이동하려면 왼쪽것부터 스택에 넣고 pop해서 계산하는 방식으로 리스트에 저장함
		//아래쪽으로 이동하려면 위쪽것부터 스택에 넣고 ...
		else {
			for(int i = 0; i<N; i++) {
				s.add(arr[i]);
			}
		}
		
		return s;
	}
	//갱신용 리스트 만들기
	// s 입력 스택
	public static List<Integer> makePrinterList(Stack<Integer> s){
		List<Integer> l = new ArrayList<Integer>();
		while(!s.isEmpty()) {
			int first = -1;
			int second = -3;
			
			//두개의 요소를 각자 0이 아닐때 pop해서 저장
			//0이면 pop해서 버림
			if(s.peek() != 0)
				first = s.pop();
			else
				s.pop();
			if(!s.isEmpty()) {
				if(s.peek() != 0)
					second = s.pop();
				else
					s.pop();
			}
			
			if(first != -1) {
				//first와 second가 성공적으로 pop된 경우
				if(second != -3) {
					if(first == second)
						l.add(first+second);
					else {
						l.add(first);
						l.add(second);
					}
				}
				//first만 pop된 경우
				else 
					l.add(first);
			}
		}
		return l;
	}
	public static void findMax() {
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++)
                answer = Math.max(answer, map[i][j]);
    }
}
