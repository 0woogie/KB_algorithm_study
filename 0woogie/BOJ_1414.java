package study;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BOJ_1414 {

	static int[] parent;
	static PriorityQueue<Edge> queue; //���� ���̸� �������� �ڵ� ������ �ϱ� ���� �켱����ť ���
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		parent = new int[n+1];
		for(int i=1; i<=n; i++)
			parent[i] = i;
		queue = new PriorityQueue<>();
		int total = 0; //�� ���� ���� ����
		for(int i=0; i<n; i++) {
			String s = br.readLine();
			for(int j=0; j<n; j++) {
				char c = s.charAt(j);
				if(c=='0')
					continue;
				int k = 0;
				if(c-'a'>=0) { //a~z
					k = c-'a'+1; //1~26
				} else { //A~Z
					k = c-'A'+27; //27~52
				}
				total += k; //�� ���� ���̿� ���� ���� ���� ���ϱ�
				queue.add(new Edge(i+1,j+1,k));
			}
		}
		int edgeNum = 0; //��ǻ�� �����ϴ� ���� ����
		int sum = 0; //��ǻ�� �����ϴ� ���� ���� �� ��
		while(!queue.isEmpty()) {
			Edge now = queue.poll();
			if(find(now.a)!=find(now.b)) { //a�� b�� ���� ������� ���� ���
				union(now.a, now.b);
				edgeNum++;
				sum += now.c;
			}
			if(edgeNum==n-1) break; //��� ��ǻ�Ͱ� ����Ǹ� �ݺ��� Ż��
		}
		if(edgeNum==n-1) System.out.println(total-sum); //����� �� �ִ� ���� = ������ �ִ� ���� - �ټ��̰� ���� ����
		else System.out.println(-1); //��� ��ǻ�Ͱ� ����Ǿ� ���� ���� ��� -1 ���
	}
	
	private static void union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a>b) parent[a] = b;
		else parent[b] = a;
	}

	private static int find(int x) {
		if(parent[x]!=x)
			parent[x] = find(parent[x]);
		return parent[x];
	}
}

class Edge implements Comparable<Edge> {
	int a;
	int b;
	int c;
	Edge (int a, int b, int c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	@Override
	public int compareTo(Edge o) { //Edge Ŭ������ ��ü�� c(���� ����)�� �������� �������� ����
		return this.c - o.c;
	}
}
