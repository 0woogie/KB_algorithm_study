#include <string>
#include <vector>
#include <algorithm>
using namespace std;

vector<int> solution(vector<int> answers) {
    //cpp 벡터는 int형 배열인데 함수 쓸수 있는 배열
    //일종의 자바 arraylist같은 느낌
    vector<int> answer;
    vector<int> supoA;
    vector<int> supoB;
    vector<int> supoC;

    
    // 각 수포자마다 특정 자릿수마다 숫자 패턴이 반복됨
    // 계속될 인덱스가 (인덱스%패턴 자릿수)이기 때문에 그것으로 조건문 처리해서 패턴 만듬
    // supoA
    //12345
    for (int idx = 0; idx < answers.size(); idx++) {
        supoA.push_back((idx % 5) + 1);
    }
    
    // supoB
    //0123 4567
    //2123 2425
    for (int idx = 0; idx < answers.size(); idx++) {
        //2의 배수 인덱스는 항상 2
        if (idx % 2 == 0)
            supoB.push_back(2);
        else {
            if (idx % 8 > 4) {
                //인덱스 5는 4
                if (idx % 8 == 5)
                    supoB.push_back(4);
                //인덱스 7은 5
                else
                    supoB.push_back(5);
            }
            // 인덱스 1은 1
            // 인덱스 3은 3
            else
                supoB.push_back(idx % 8);
        }
    }
    // supoC
    //0 1 2 3 4   5 6 7 8 9
    //3 3 1 1 2   2 4 4 5 5
    for (int idx = 0; idx < answers.size(); idx++) {
        //2의 배수 인덱스마다 앞의 숫자와 같으므로
        if (idx % 2 == 1)
            supoC.push_back(supoC[idx-1]);
        else
        {
            //10자리마다 반복됨 그리고 첫 자리는 3
            if (idx % 10 == 0)
                supoC.push_back(3);
            else {
                // 인덱스 2와 4의 값은
                // 1 과 2므로 인덱스 값을 나눠준 것
                if (idx % 10 < 5)
                    supoC.push_back((idx % 10 / 2));
                else
                    supoC.push_back((idx % 10 / 2) + 1);
            }
        }
    }
    //맞힌갯수 0 1 2에 저장하고 0=a, 1=b, 2=c
    int aCount = 0;
    int bCount = 0;
    int cCount = 0;
    for (int idx = 0; idx < answers.size(); idx++) {
        if (answers[idx] == supoA[idx])
            aCount += 1;
        if (answers[idx] == supoB[idx])
            bCount += 1;
        if (answers[idx] == supoC[idx])
            cCount += 1;
    }
    //pair라는 형이 다른 두 변수를 하나로 묶는 자료형을 선언하여
    //그것을 pair vector로 선언해 수포자 종류, 맞힌 갯수 저장
    vector<pair<int, int>> countList;
    countList.push_back(make_pair(1, aCount));
    countList.push_back(make_pair(2, bCount));
    countList.push_back(make_pair(3, cCount));

    //가장 높은 점수 받은 사람 골라내는 것
    int maxElem = countList[0].second;
    for (int i = 1; i < countList.size(); i++) {
        if (countList[i].second > maxElem)
            maxElem = countList[i].second;
    }
    
    //가장 높은 점수 받은 사람이 여러명일때 처리하는 것
    for (int i = 0; i < countList.size(); i++) {
        if (countList[i].second == maxElem)
            answer.push_back(countList[i].first);
    }
    
    //높은 점수 받은 사람이 여러명일때 return하는 값을 오름차순으로 정렬하라니까 정렬함
    sort(answer.begin(), answer.end());
    
    return answer;
}
