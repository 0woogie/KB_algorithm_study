#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<int> solution(vector<int> array, vector<vector<int>> commands)
{
    vector<int> answer;
    for (int i = 0; i < commands.size(); i++)
    {
        vector<int> tmp(array.size());
        copy(array.begin(), array.end(), tmp.begin());
        sort(tmp.begin() + commands[i][0] - 1, tmp.begin() + commands[i][1]);
        answer.push_back(*(tmp.begin() + commands[i][0] + commands[i][2] - 2));
    }
    return answer;
}