#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> sizes)
{
    int garo = -1;
    for (int i = 0; i < sizes.size(); i++)
    {
        int tmp = max(sizes[i][1], sizes[i][0]);
        garo = max(tmp, garo);
    }
    int sero = -1;
    for (int i = 0; i < sizes.size(); i++)
    {
        int tmp = min(sizes[i][0], sizes[i][1]);
        sero = max(sero, tmp);
    }
    int answer = garo * sero;
    return answer;
}