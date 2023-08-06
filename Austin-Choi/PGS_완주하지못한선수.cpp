#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
using namespace std;

string solution(vector<string> participant, vector<string> completion) {
    string answer = "";
    //키 값 중복을 허용하지 않는 unordered_map 자료구조를 활용함
    //이름이 키 값으로 들어가고 value에는 존재하는 숫자가 들어감 
    //참가자에 a b c b c가 들어오면
    //자료구조에는 (a,1) (b,2) (c,2)로 저장되게 될 것임.
    unordered_map<string, int> pMap;

    //참가자 이름을 키값으로 같은 이름이 나올때마다 +1을 해줌
    for (auto name : participant) {
        ++pMap[name];
    }

    //완주한 선수 이름을 키값으로 접근해 해당 이름의 갯수를 -1을 해줌
    for (auto name : completion) {
        --pMap[name];
    }

    //만약 키로 접근했을때 value값이 0이 아니라면 완주를 하지 못하고 
    //남아있다는 뜻이므로 value값이 0 보다 클 때 
    //키값을 반환함
    for (auto item : pMap) {
        if (item.second > 0) {
            answer = item.first;
            break;
        }
    }
    return answer;
}
