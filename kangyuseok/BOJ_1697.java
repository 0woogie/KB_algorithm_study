#include<iostream>
#include<algorithm>
#include<cstring>
#include<vector>
#include<string>
#include<stack>
#include<queue>
#include<deque>
#include<cmath>
#include<map>
#include<set>
#include<tuple>
using namespace std;
using ll = long long;
using ull = unsigned long long;
int n, k;
bool visit[100001];
int main() {
	ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
	cin >> n >> k;
	queue<pair<int,int>>q;
	q.push({ k, 0 });
	int cnt;
	while (!q.empty()) {
		int x = q.front().first;
		int time = q.front().second;
		visit[x] = true;
		if (x == n) {
			cnt = time;
			break;
		}
		q.pop();
		if(!visit[x-1])
			q.push({ x - 1, time + 1 });
		if (x % 2 == 0 && !visit[x / 2])
			q.push({ x / 2, time + 1 });
		if(!visit[x+1])
			q.push({ x +1, time + 1 });
	}
	cout << cnt;

	return 0;
}
