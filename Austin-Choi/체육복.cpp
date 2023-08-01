#include <string>
#include <vector>
#include <algorithm>
using namespace std;

int solution(int n, vector<int> lost, vector<int> reserve) {
    int answer = 0;
    vector<int> clothPool;
    
    for (int i = 0; i < n; i++) {
        clothPool.push_back(1);
    }
    for (auto item : reserve) {
        clothPool[item - 1] += 1;
    }
    for (auto item : lost) {
        clothPool[item - 1] -= 1;
    }

    for (int idx = 0; idx < n; idx++) {
        if (clothPool[idx] == 0) {
            if (idx == 0) {
                if (clothPool[idx + 1] > 1)
                {
                    clothPool[idx] += 1;
                    clothPool[idx + 1] -= 1;
                }
            }
            else if (idx == n - 1) {
                if (clothPool[idx - 1] > 1)
                {
                    clothPool[idx] += 1;
                    clothPool[idx - 1] -= 1;
                }
            }
            else {
                if (clothPool[idx - 1] > 1 || clothPool[idx + 1] > 1)
                {
                    if (clothPool[idx + 1] > clothPool[idx - 1])
                    {
                        clothPool[idx] += 1;
                        clothPool[idx + 1] -= 1;
                    }
                    else
                    {
                        clothPool[idx] += 1;
                        clothPool[idx - 1] -= 1;
                    }

                }
            }
        }
    }

    for (int item : clothPool) {
        if (item > 0)
            answer++;
    }

    return answer;
}
