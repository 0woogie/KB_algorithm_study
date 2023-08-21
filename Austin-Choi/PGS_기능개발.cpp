#include <string>
#include <vector>
#include <queue>

using namespace std;

vector<int> solution(vector<int> progresses, vector<int> speeds) {
	vector<int> answer;
	queue<int> completion_q;

	for (int i = 0; i < progresses.size(); i++) {
		int day = 0;
        //진도가 100%일때 완료되므로 
		while (progresses[i] < 100) {
			day++;
            //하루에 작업량 더해줌
			progresses[i] += speeds[i];
		}
        //각 작업 완료될때까지 필요한 날 큐에 삽입
		completion_q.push(day);
	}


	while (!completion_q.empty()) {
		int prior_completionDate = completion_q.front();
        //배포는 하루의 끝에 이루어지므로 1부터 누적시킴
		int completionDate = 1;
		completion_q.pop();

        //아직 처리할 작업이 남아 있고, 작업이 끝나기 전에 남은 일수가 큰 것에 맞춰야 하므로 1씩 증가시킴
		while ((!completion_q.empty()) && (completion_q.front() <= prior_completionDate)) {
			completion_q.pop();
			completionDate++;
		}
		answer.push_back(completionDate);
	}
	return answer;
}
