package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

class FireBall{
	private int posI;
	private int posJ;
	private int m; //질량
	private int d; //방향
	private int s; //속력
	
	public FireBall(int m, int d, int s) {
		this.m = m;
		this.d = d;
		this.s = s;
	}

	public FireBall(int posI, int posJ, int m, int d, int s) {
		this.posI = posI;
		this.posJ = posJ;
		this.m = m;
		this.d = d;
		this.s = s;
	}
	
	public int getPosI() {
		return posI;
	}

	public void setPosI(int posI) {
		this.posI = posI;
	}

	public int getPosJ() {
		return posJ;
	}

	public void setPosJ(int posJ) {
		this.posJ = posJ;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getD() {
		return d;
	}

	public void setD(int d) {
		this.d = d;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}
}
/* 12:48
 * ArrayList<FireBall>[][] map 3파라미터
 * ArrayList<FireBall> ballList 5파라미터
 * 1. 모든 파이어볼이 자신의 방향 di로 속력 si칸 만큼 이동한다.
 *   이동하는 중에는 같은 칸에 여러 개의 파이어볼이 있을 수도 있다.
 * 2. 이동이 모두 끝난 뒤, 2개 이상의 파이어볼이 있는 칸에서는 다음과 같은 일이 일어난다.
 *   같은 칸에 있는 파이어볼은 모두 하나로 합쳐진다.
 *   파이어볼은 4개의 파이어볼로 나누어진다.
 *   나누어진 파이어볼의 질량, 속력, 방향은 다음과 같다.
 *    - 질량은 ⌊(합쳐진 파이어볼 질량의 합)/5⌋이다.
 *    - 속력은 ⌊(합쳐진 파이어볼 속력의 합)/(합쳐진 파이어볼의 개수)⌋이다.
 *    - 합쳐지는 파이어볼의 방향이 모두 홀수이거나 모두 짝수이면, 
 *      방향은 0, 2, 4, 6이 되고, 그렇지 않으면 1, 3, 5, 7이 된다.
 *    - 질량이 0인 파이어볼은 소멸되어 없어진다.
 */
public class BOJ_20056_마법사상어와파이어볼 {
	//방향                            0  1  2  3  4  5  6  7
	public final static int[] di = {-1,-1, 0, 1, 1, 1, 0,-1};
	public final static int[] dj = { 0, 1, 1, 1, 0,-1,-1,-1};
	public static Stack<FireBall>[][] map;
	public static ArrayList<FireBall> ballList;
	
	public static void main(String[] args) throws IOException {
		//1번 행은 N번과 연결되어 있고, 1번 열은 N번 열과 연결되어 있다.
		//map 정사각형 가로=세로= N
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		map = new Stack[N][N];
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				map[i][j] = new Stack<FireBall>();
			}
		}
		ballList = new ArrayList<FireBall>();
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int posI = Integer.parseInt(st.nextToken())-1;
			int posJ = Integer.parseInt(st.nextToken())-1;
			int m = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			
			//전체 파이어볼 정보 기록하는 볼리스트에 일단 저장
			FireBall fb = new FireBall(posI, posJ, m, d, s);
			ballList.add(fb);
		}
		
		for(int k = 0; k<K; k++) {
			//이동시켜서 map에 이번에는 위치정보까지 포함해서 저장
			for(FireBall b : ballList) {
				//저번주 스터디에서 배운 Math.floorMod 활용
				int ni = Math.floorMod(b.getPosI() + (b.getS()*di[b.getD()]), N);
				int nj = Math.floorMod(b.getPosJ() + (b.getS()*dj[b.getD()]), N);
				map[ni][nj].add(new FireBall(b.getM(), b.getD(), b.getS()));
			}
			
			for(int i = 0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					//한 곳에 2개 이상의 파이어볼 있을 때
					if(map[i][j].size()>1) {
						int totalm = 0;
						int totals = 0;
						//모두 홀수인지 짝수인지 구별하기 위해 홀수 짝수 세서 저장
						int totalOdd = 0;
						int totalEven = 0;
						int size = map[i][j].size();
						//홀짝 구분 끝나고 새 방향 저장할 배열
						int[] nd = new int[4];
						while(!map[i][j].isEmpty()) {
							FireBall temp = map[i][j].pop();
							totalm += temp.getM();
							totals += temp.getS();
							if(temp.getD() % 2 == 1)
								totalOdd += 1;
							else
								totalEven += 1;
						}
						//전부 홀이거나 짝일때
						if(totalOdd == size || totalEven == size)
						{
							nd[0]=0; nd[1]=2; nd[2]=4; nd[3]=6;
						}
						else {
							nd[0]=1; nd[1]=3; nd[2]=5; nd[3]=7;
						}
						//질량 0인건 없어지니까 계산안함
						for(int n : nd) {
							if(totalm/5 != 0)
								ballList.add(new FireBall(i, j, totalm/5, totalOdd/size, n));
						}	
					}
					
					//한 곳에 파이어볼 하나 있을때 쪼개지지도 않으므로 그냥 저장
					if(map[i][j].size()==1) {
						FireBall bb = map[i][j].pop();
						ballList.add(new FireBall(i, j, bb.getM(), bb.getD(), bb.getS()));
					}	
				}
			}
		}
		int answer = 0;
		for(FireBall b : ballList) {
			answer += b.getM();
		}
		System.out.println(answer);
	}

}
