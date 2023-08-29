#include <iostream>
#include <string>
#include <cstring>
#include <sstream>
#include <algorithm>
#include <vector>
#include <queue>
#include <map>
#include <math.h>
#define INF 2000000000
using namespace std;
using ll = long long;
int x[4] = {1, 0, -1, 0};
int y[4] = {0, 1, 0, -1};
int arr[101][101];
int main()
{
    ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
    int n;
    cin >> n;

    int k;
    cin >> k;
    for (int i = 0; i < k; i++)
    {
        int a, b;
        cin >> a >> b;
        arr[a][b] = 1; // 사과
    }
    deque<pair<int, int>> d;
    map<int, char> m;
    int l;
    cin >> l;
    for (int i = 0; i < l; i++)
    {
        int a;
        cin >> a;
        char b;
        cin >> b;
        m.insert({a, b}); // 방향
    }
    d.push_back({1, 1});
    arr[1][1] = 2; // 자기 몸
    int idx = 0;
    int time = 0;
    while (1)
    {
        pair<int, int> head = d.front();
        pair<int, int> tail = d.back();

        // 게임이 멈추는 조건
        if (head.first + y[idx] <= 0 || head.first + y[idx] > n || head.second + x[idx] <= 0 || head.second + x[idx] > n)
        {
            time++;
            break;
        }
        if (arr[head.first + y[idx]][head.second + x[idx]] == 2)
        {
            time++;
            break;
        }
        if (arr[head.first + y[idx]][head.second + x[idx]] == 0)
        {
            d.pop_back();
            d.push_front({head.first + y[idx], head.second + x[idx]});
            arr[tail.first][tail.second] = 0;                   // 꼬리 자르기
            arr[head.first + y[idx]][head.second + x[idx]] = 2; // 머리 늘리기
        }
        else if (arr[head.first + y[idx]][head.second + x[idx]] == 1)
        {
            d.push_front({head.first + y[idx], head.second + x[idx]});
            arr[head.first + y[idx]][head.second + x[idx]] = 2; // 머리 늘리기
        }
        time++;
        if (m.find(time) != m.end())
        {
            if (m[time] == 'L')
            {
                if (idx - 1 < 0)
                    idx = 3;
                else
                    idx--;
            }
            else
            {
                if (idx + 1 > 3)
                    idx = 0;
                else
                    idx++;
            }
        }
    }
    cout << time;

    return 0;
}
