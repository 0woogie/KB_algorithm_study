import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

class Pos{
	int i;
	int j;
	Pos(int i, int j){
		this.i = i;
		this.j = j;
	}
	public int getI() {
		return i;
	}
	public void setI(int i) {
		this.i = i;
	}
	public int getJ() {
		return j;
	}
	public void setJ(int j) {
		this.j = j;
	}
}

//public class Main {
public class BOJ_3190 {
	static int[] di= {-1,0,1,0};
	static int[] dj= {0,1,0,-1}; //북동남서

	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
	/*
		첫째 줄에 보드의 크기 N이 주어진다. (2 ≤ N ≤ 100) 다음 줄에 사과의 개수 K가 주어진다. (0 ≤ K ≤ 100)
	
		다음 K개의 줄에는 사과의 위치가 주어지는데, 첫 번째 정수는 행, 두 번째 정수는 열 위치를 의미한다. 사과의 위치는 모두 다르며, 
		맨 위 맨 좌측 (1행 1열) 에는 사과가 없다.
	
		다음 줄에는 뱀의 방향 변환 횟수 L 이 주어진다. (1 ≤ L ≤ 100)
	
		다음 L개의 줄에는 뱀의 방향 변환 정보가 주어지는데, 정수 X와 문자 C로 이루어져 있으며. 
		게임 시작 시간으로부터 X초가 끝난 뒤에 왼쪽(C가 'L') 또는 오른쪽(C가 'D')로 90도 방향을 회전시킨다는 뜻이다. 
		
		방향 변환은 L이면 기존 dy dx더해주는 거에서 +3(270도 회전)하고 %4해주고
		D이면 dy dx더해주는 거에서 +1(90도 회전)하고 %4해줌 
		
		X는 10,000 이하의 양의 정수이며, 방향 전환 정보는 X가 증가하는 순으로 주어진다.
	*/
		//전체 지도를 의미하는 map 이차원배열
		//뱀을 의미하는 Pos(i,j) deque
		int N = Integer.parseInt(st.nextToken());
		//바운더리 벗어났을때 계산 편하게 해주려고 가로세로 한칸씩 더 넓게 세팅함
		int[][] map = new int[N+2][N+2]; 
		Deque<Pos> snake = new LinkedList<>();
		
		for(int i = 0; i<N+2; i++)
			Arrays.fill(map[i], 0); //초기화
		//0: 초기화, 1은 뱀, 2는 사과
		
		//사과 갯수 K
		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2; //사과는 2
		}
		
		//방향 변환 횟수 L
		st = new StringTokenizer(br.readLine());
		int L = Integer.parseInt(st.nextToken());
		
		//북동남서 0,1,2,3
		int direction = 1; 
		int countTime = 0;
		boolean breakFlag = false;
		
		snake.addFirst(new Pos(1,0));
		
		int a = 0;
		int prevPassedTime = 0;
		for(int z = 0; z<L; z++) {
			st = new StringTokenizer(br.readLine());
			int passedTime = Integer.parseInt(st.nextToken());
			
			//passedTime만큼 for문 돌면서 다음 위치 계산
			//passedTime에서 다음 passedTime계산할때 누적이므로 바로 앞 passedTime 빼주고 계산하기
			for(; a< passedTime-prevPassedTime; a++) {
				int nextI = snake.peekFirst().getI() + di[direction];
				int nextJ = snake.peekFirst().getJ() + dj[direction];
//				System.out.println("nextI : "+nextI +", nextJ : "+ nextJ);
//				System.out.println("countTime : "+countTime);
//				System.out.println("a : "+a);
//				System.out.println("=====================================");
				
				//if() 유효성검사 + //뱀 자기 자신 만났을때
				//가로세로 한칸씩 더 잡아서 따로 맨 처음부분만 계산해줌
				if(countTime == 0 && (nextI == 0 || nextI == N+1 || nextJ == N+1 || map[nextI][nextJ]==1)) {
					breakFlag = true;
					break;
				}

				else if(countTime != 0 && (nextI == 0 || nextI == N+1 || nextJ == 0 || nextJ == N+1 || map[nextI][nextJ]==1)) {
					breakFlag = true;
					break;
				}

				else {
					//사과가 있을때는 뱀이 사과를 먹고 길이가 1 늘어남.
					snake.addFirst(new Pos(nextI, nextJ));
					//사과가 없을때는 뱀이 이동하고 뒤가 땡겨짐 
					if(map[nextI][nextJ]!=2) {
						snake.removeLast();
					}
					//뱀이 지나갔으므로 해당 칸은 1이 됨
					map[nextI][nextJ] = 1;
					countTime++;
				}
				
				
			}
			if(breakFlag)
				break;
			
			String instructor = st.nextToken();
			//방향 바꿀때 아까 더해준 뱀 머리 다시 빼기 증가하면 안되니까 
			if(chooseDirection(direction, instructor) != direction) {
				snake.removeFirst(); //하나일때 제거하면 다음 next 계산이 안됨, flag로 처리할까
				direction = chooseDirection(direction, instructor);
				countTime++;
				a++;
			}
			prevPassedTime = passedTime;
		}
		
		System.out.println(countTime);
	}
	
	//방향 계산하는 메서드 현재 방향을 기준으로 시계방향으로 
	//설정해두엇으므로 D는 90도 꺾인거라 1을 더하고
	//L은 270도 꺾인 것이므로 3을 더함 해서 %4하면 다음 방향 나옴
	static int chooseDirection(int prevDirection, String instructor) {
		if(instructor.equals("D"))
			return (prevDirection+1)%4;
		else // L
			return (prevDirection+3)%4;
	}

}
