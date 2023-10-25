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
		//��� ���̾ ����Ʈ�� ����
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
			//�� �ϸ��� ��� ���̾ �̵���Ű��
			for(int i=0; i<list.size(); i++) {
				Fireball f = list.get(i);
				f.x = Math.floorMod(f.x+dx[f.d]*f.s, N);
				f.y = Math.floorMod(f.y+dy[f.d]*f.s, N);
				list.set(i, f);
			}
			
			//����Ʈ ���Ľ�Ű�� (r, c ����)
			Collections.sort(list);
			
			List<Fireball> newList = new ArrayList<>(); //�̹� �� ��� �ϵ��� ���� ���� ���̾���� ��� �迭
			//����Ʈ ó������ ������ ���� (��� ���̾ Ȯ��)
			int index = 0;
			while(index<list.size()) {
				List<Fireball> tmpList = new ArrayList<>();
				Fireball tmp = list.get(index++);
				int x = tmp.x;
				int y = tmp.y;
				tmpList.add(tmp);
				while(index<list.size()) {
					//���� ĭ�� �ִ� ���̾ tmpList�� ����
					Fireball now = list.get(index);
					if(now.x==x && now.y==y) {
						tmpList.add(now);
						index++;
					} else {
						break;
					}
				}
				//tmpList.size() == 1�̸� �׳� newList�� ���� (�ϳ��� ĭ�� �ϳ��� ���̾�� �ִ� ���)
				if(tmpList.size()==1) {
					newList.add(tmpList.get(0));
				}
				//tmpList.size() > 1�̸� ���̾ ��ġ�� ���� �� newList�� 4���� ���̾ ���� (���� ĭ�� ���� ���̾ �ִ� ���)
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
					
					if(m==0) { //������ 0�� ���̾�� �Ҹ��
						continue;
					}
					
					if(!flag) { //������ 0, 2, 4, 6
						for(int j=0; j<8; j+=2) {
							newList.add(new Fireball(r, c, m, s, j));
						}
					} else { //������ 1, 3, 5, 7
						for(int j=1; j<8; j+=2) {
							newList.add(new Fireball(r, c, m, s, j));
						}
					}
				}
			}
			list = newList; //�̹� �� ��� �ϵ�(�̵��ϰ� �������� ������ �Ҹ��ϰ�)�� ���� ���� ���̾���� list�� �ݿ�
		}
		
		int result = 0;
		//K���� ��� �� ������ �� ���ϱ�
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