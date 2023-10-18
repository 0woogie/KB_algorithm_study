#include <iostream>
#include <algorithm>
#include <climits>
#include <string>
#include <vector>
#include <queue>
#include <stack>
#include <deque>
#include <cmath>
#include <time.h>
#include <cstring>
#include <cmath>
#include <cstring>
using namespace std;
using ll = long long;
using ull = unsigned long long;
int price[1001][4];
int dp[1001][1001];
int main()
{
    ios_base::sync_with_stdio(false), cin.tie(NULL), cout.tie(NULL);
    int n;
    cin >> n;

    for (int i = 1; i <= n; i++)
    {
        for (int j = 1; j <= 3; j++)
        {
            int color;
            cin >> color;
            price[i][j] = color;
        }
    }
    dp[1][1] = price[1][1];
    dp[1][2] = price[1][2];
    dp[1][3] = price[1][3];
    for (int i = 2; i <= n; i++)
    {
        dp[i][1] += price[i][1] + min(dp[i - 1][2], dp[i - 1][3]);
        dp[i][2] += price[i][2] + min(dp[i - 1][1], dp[i - 1][3]);
        dp[i][3] += price[i][3] + min(dp[i - 1][1], dp[i - 1][2]);
    }
    cout << min(min(dp[n][1], dp[n][2]), dp[n][3]);

    return 0;
}
