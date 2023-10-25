package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_20056 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
		int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
		
		List<Fireball> list = new ArrayList<>();
		//모든 파이어볼 리스트에 저장
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			list.add(new Fireball(r, c, m, s, d));
		}
		
		for(int k=1; k<=K; k++) {
			//한 턴마다 모든 파이어볼 이동시키기
			for(int i=0; i<list.size(); i++) {
				Fireball f = list.get(i);
				f.x = Math.floorMod(f.x+dx[f.d]*f.s, N);
				f.y = Math.floorMod(f.y+dy[f.d]*f.s, N);
				list.set(i, f);
			}
			
			//리스트 정렬시키기 (r, c 기준)
			Collections.sort(list);
			
			List<Fireball> newList = new ArrayList<>(); //이번 턴 모든 일들이 끝난 뒤의 파이어볼들을 담는 배열
			//리스트 처음부터 끝까지 돌기 (모든 파이어볼 확인)
			int index = 0;
			while(index<list.size()) {
				List<Fireball> tmpList = new ArrayList<>();
				Fireball tmp = list.get(index++);
				int x = tmp.x;
				int y = tmp.y;
				tmpList.add(tmp);
				while(index<list.size()) {
					//같은 칸에 있는 파이어볼 tmpList에 저장
					Fireball now = list.get(index);
					if(now.x==x && now.y==y) {
						tmpList.add(now);
						index++;
					} else {
						break;
					}
				}
				//tmpList.size() == 1이면 그냥 newList에 저장 (하나의 칸에 하나의 파이어볼만 있는 경우)
				if(tmpList.size()==1) {
					newList.add(tmpList.get(0));
				}
				//tmpList.size() > 1이면 파이어볼 합치고 나눈 뒤 newList에 4개의 파이어볼 저장 (같은 칸에 여러 파이어볼 있는 경우)
				else {
					Fireball first = tmpList.get(0);
					int r = first.x;
					int c = first.y;
					int m = first.m;
					int s = first.s;
					int d = first.d%2;
					boolean flag = false;
					
					for(int i=1; i<tmpList.size(); i++) {
						Fireball fb = tmpList.get(i);
						m += fb.m;
						s += fb.s;
						if(fb.d%2 != d)
							flag = true;
					}
					
					m /= 5;
					s /= tmpList.size();
					
					if(m==0) { //질량이 0인 파이어볼은 소멸됨
						continue;
					}
					
					if(!flag) { //방향은 0, 2, 4, 6
						for(int j=0; j<8; j+=2) {
							newList.add(new Fireball(r, c, m, s, j));
						}
					} else { //방향은 1, 3, 5, 7
						for(int j=1; j<8; j+=2) {
							newList.add(new Fireball(r, c, m, s, j));
						}
					}
				}
			}
			list = newList; //이번 턴 모든 일들(이동하고 합쳐지고 나뉘고 소멸하고)이 끝난 뒤의 파이어볼들을 list에 반영
		}
		
		int result = 0;
		//K번의 명령 후 질량의 합 구하기
		for(Fireball fb : list) {
			result += fb.m;
		}
		System.out.println(result);
	}

}

class Fireball implements Comparable<Fireball> {
	int x;
	int y;
	int m;
	int s;
	int d;
	
	public Fireball(int x, int y, int m, int s, int d) {
		this.x = x;
		this.y = y;
		this.m = m;
		this.s = s;
		this.d = d;
	}
	
	@Override
	public int compareTo(Fireball o) {
		if(this.x == o.x) {
			return this.y - o.y;
		}
		return this.x - o.x;
	}
}