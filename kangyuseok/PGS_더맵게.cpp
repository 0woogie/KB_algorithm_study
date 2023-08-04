#include <string>
#include <vector>
#include <queue>
using namespace std;

int solution(vector<int> scoville, int K)
{
    priority_queue<int, vector<int>, greater<int>> pq;
    for (int i = 0; i < scoville.size(); i++)
    {
        pq.push(scoville[i]);
    }
    int answer = 0;

    while (!pq.empty())
    {
        int a = pq.top();
        if (a >= K)
            break;
        pq.pop();
        if (pq.empty())
        {
            answer = -1;
            break;
        }
        int b = pq.top();
        pq.pop();
        long long n = a + (b * 2);
        pq.push(n);
        if (n == a || n == b)
        {
            answer = -1;
            break;
        }
        answer++;
    }

    return answer;
}