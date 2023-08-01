#include <string>
#include <vector>

using namespace std;
int cnt = 0;
void sol(vector<int> v, int idx, int sum, int t);
int solution(vector<int> numbers, int target)
{
    sol(numbers, 0, 0, target);

    int answer = cnt;
    return answer;
}
void sol(vector<int> v, int idx, int sum, int t)
{
    if (idx == v.size())
    {
        if (sum == t)
        {
            cnt++;
            return;
        }
        else
            return;
    }
    sol(v, idx + 1, sum + v[idx], t);
    sol(v, idx + 1, sum - v[idx], t);
}