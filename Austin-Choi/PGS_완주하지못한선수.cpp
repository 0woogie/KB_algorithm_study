#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    unordered_map<string, int> pMap;

    for (auto name : participant) {
        ++pMap[name];
    }

    for (auto name : completion) {
        --pMap[name];
    }
    for (auto item : pMap) {
        if (item.second > 0) {
            answer = item.first;
            break;
        }
    }
    return answer;
} if (item.second > 0) {
            answer = item.first;
            break;
        }
    }
    return answer;
}
