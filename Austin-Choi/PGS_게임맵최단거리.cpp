#include <vector>
#include <queue>
using namespace std;
//상하좌우
int dy[4] = {-1,1,0,0};
int dx[4] = {0,0,-1,1};
vector<vector<bool> > visit;
//n과 m은 각각 1이상 100이하의 자연수라 했으므로
//최대치로 설정
//c++은 자바와 다르게 선언시 변수로 사이즈 지정 안됨
//그래서 사이즈 지정되면 최대크기로 잡아놓고 함
int dist[100][100] = {0, };
int answer = 0;

//최단거리 탐색이므로 queue를 이용한 bfs 활용
int bfs(int y, int x, vector<vector<int> > maps){
     //행몇개 m
    int row_size = maps.size();
    //열몇개 n
    int col_size = maps[0].size();
    
    //거리 계산할 dist 주어진 map크기만큼 초기화해주기
    for(int j = 0; j<row_size; j++){
        for(int i = 0; i<col_size; i++){
            dist[j][i]=0;
        }
    }
    //0,0 자리에서 시작하므로 일단 1 넣어줌
    dist[0][0]=1;
    
    queue<pair<int,int>> q;
    q.push(make_pair(y,x));
    
    while(!q.empty()){
        int cur_y = q.front().first;
        int cur_x = q.front().second;
        q.pop();
        
        //상하좌우 선택을 위해 위에 선언해둔 dx dy 사용 
        for(int i = 0; i<4; i++){
            // 다음 좌표 잡기
           int ny = cur_y + dy[i];
           int nx = cur_x + dx[i];
            
            if(ny>=0 && nx>=0 && ny < row_size && nx < col_size){
                //map에서 1은 길이고 dist가 0이라는 것은 해당 좌표를 아직 방문하지 않았다는 뜻이므로
                //거기로 간다
                if(maps[ny][nx]==1 && dist[ny][nx] == 0)
                {
                    //1씩 전의 dist값에 누적시켜서 
                    //해당 좌표까지의 온 거리를 계산.
                    dist[ny][nx] = dist[cur_y][cur_x] + 1;
                    q.push(make_pair(ny,nx));
                }
            }
        }
    }
    //도착점의 dist 값이 0이라는 것은 결국 길을 찾지 못한 것이므로 -1반환
    if(dist[row_size-1][col_size-1]==0)
        return -1;
    //도착했으면 누적된 dist 값을 리턴해줌
    else
         return dist[row_size-1][col_size-1];
}
int solution(vector<vector<int> > maps)
{
    return bfs(0,0, maps);
}
