#include <vector>
#include <map>
using namespace std;

int solution(vector<int> nums)
{
    map<int, int> m;
    for (int i = 0; i < nums.size(); i++)
    {
        m[nums[i]]++;
    }
    int answer = 0;

    int s = nums.size() / 2;
    if (s < m.size())
        answer = s;
    else
        answer = m.size();
    return answer;
}