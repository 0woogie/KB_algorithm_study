package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_21610_마법사상어와비바라기 {
	//8방향 표현
	static int[] di = { 0,-1,-1,-1, 0, 1, 1, 1};  
	static int[] dj = {-1,-1, 0, 1, 1, 1, 0,-1};
	
	public static void main(String[] args) throws IOException {
		//input initialize
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		//N이 50, M이 100이므로
		//3중반복문 써도 시간복잡도에 어긋나지 않음
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		//하늘
		int[][] sky = new int[N][N];
		
		//보드 초기화
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				sky[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//구름 이동 명령들 : 방향 [0], 거리 [1]
		int[][] cloudCmds = new int[M][2];
		for(int i = 0; i< M; i++) {
			st = new StringTokenizer(br.readLine());
			//방향표현이 1,2,3,4,5,6,7,8이므로 idx에 맞추려고 1 뺌
			cloudCmds[i][0] = Integer.parseInt(st.nextToken())-1;
			cloudCmds[i][1] = Integer.parseInt(st.nextToken());
		}
		
		
		//구름 위치 저장용 cloudPosCol, cloudPosRow
		ArrayList<Integer> cloudPosCol = new ArrayList<Integer>();
		ArrayList<Integer> cloudPosRow = new ArrayList<Integer>();
		
		//비바라기를 시전하면 (N-1, 0), (N-1, 1), (N-2, 0), (N-2, 1)에 비구름이 생김
		cloudPosCol.add(N-1); cloudPosRow.add(0);
		cloudPosCol.add(N-1); cloudPosRow.add(1);
		cloudPosCol.add(N-2); cloudPosRow.add(0);
		cloudPosCol.add(N-2); cloudPosRow.add(1);
		
		ArrayList<Integer> oldCloudPosCol = new ArrayList<Integer>();
		ArrayList<Integer> oldCloudPosRow = new ArrayList<Integer>();
		
		// 동서남북은 인덱스가 %2 == 0, 대각선은 %2 == 1
		for(int z = 0; z < M; z++) {
			//1) 모든 구름이 [0] 방향으로 [1]칸 이동한다.
			for(int i = 0; i < cloudPosCol.size(); i++) {
				int newPosCol = cloudPosCol.indexOf(i)+(di[cloudCmds[z][0]]*(cloudCmds[z][1]%N));
				int newPosRow = cloudPosRow.indexOf(i)+(dj[cloudCmds[z][0]]*(cloudCmds[z][1]%N));
				
				//N보다 큰 건 %연산으로 해결했으니 0보다 작을때만 예외처리해줌
				if(newPosCol < 0)
					newPosCol = N + newPosCol;
				if(newPosRow < 0)
					newPosRow = N + newPosRow;
				
				cloudPosCol.set(i, newPosCol);
				cloudPosRow.set(i, newPosRow);
			}
		
			//2) 각 구름에서 비가 내려 구름이 있는 칸의 바구니에 저장된 물의 양이 1 증가한다.
			for(int i = 0; i < cloudPosCol.size(); i++) {
				sky[cloudPosCol.get(i)][cloudPosRow.get(i)] += 1;
			}
		
			//3) 구름이 모두 사라진다.
			oldCloudPosCol = cloudPosCol;
			oldCloudPosRow = cloudPosRow;
		
			//4) 2에서 물이 증가한 칸 (r, c)에 대각선 방향으로 [1,3,5,7]
			//거리가 1인 칸에 물이 있는 바구니의 수만큼 
			//(r, c)에 있는 바구니의 물이 양이 증가한다.
			//이때는 경계를 넘어가는 칸은 대각선 방향으로 거리가 1인 칸이 아니다.
			int waterCount = 0;
			for(int i = 0; i< cloudPosCol.size(); i++) {
				int pc = cloudPosCol.get(i);
				int pr = cloudPosRow.get(i);

				for(int w = 0; w< di.length; w++) {
					if(w%2==0)
						continue;
					else
					{
						if(pc+di[w] > -1 && pc+di[w] < N
							&& pr+dj[w] > -1 && pr+dj[w] < N
						){
							if(sky[pc+di[w]][pr+dj[w]]>0)
								waterCount += 1;
						}
					}
				}
				sky[pc][pr] += waterCount;
				waterCount = 0;
			}
		
			//새 구름을 생성해야하니까 비워준다.
			cloudPosCol.clear();
			cloudPosRow.clear();
			
			//5) 바구니에 저장된 물의 양이 2 이상인 모든 칸에 구름이 생기고, 물의 양이 2 줄어든다. 
			//이때 구름이 생기는 칸은 3에서 구름이 사라진 칸이 아니어야 한다.
			for(int y = 0; y<N; y++) {
				for(int x = 0; x<N; x++) {
					//contains같이 iterate가 일어나는 조건문을 아래에 두고 
					//간단한것부터 처리해서 효율 올리기
					if(sky[y][x] < 2 )
						continue;
					else if(oldCloudPosCol.contains(y) && oldCloudPosRow.contains(x))
						continue;
					else {
						sky[y][x] -= 2;
						cloudPosCol.add(y);
						cloudPosRow.add(x);
					}
				}
			}//inner-for
		
		}//for
		
		//합산
		int total = 0;
		for(int i = 0; i<N; i++) {
			for(int j = 0; j<N; j++) {
				total += sky[i][j];
			}
		}
		
		//출력
		System.out.println(total);
		
	}//main

}
