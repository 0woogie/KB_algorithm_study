import java.util.*;

class Solution {
    public int solution(String name) {    
    //최소 이동 횟수 cost
    int cost = 0;

    //상하조작 미리 구해서 더해두기
    for(int i = 0; i<name.length(); i++){
        cost += Math.min(name.charAt(i)-'A', 26 - (name.charAt(i)-'A'));;
    }

    //좌우조작
    //AAAAAAA같은 A가 반복되는 곳의 끝 인덱스 구하기
    //반복A가 좌우 방향 결정에 영향을 미치기 때문
    //좌우 조작은
    //1. 오른쪽 끝으로 계속 가는 것
    //2. 오른쪽으로 갔다가 왼쪽으로 다시 가는 것
    //3. 왼쪽으로 갓다가 오른쪽으롣 다시 가는 것
    //방향 전환은 한번만 하는 것이 최소값 보장
        
    //투포인터 알고리즘 사용  
    //투포인터 
    //앞 포인터 front
    //뒤 포인터 end
    int front = 0;
    int end = front;
    while(end < name.length() && name.charAt(end) == 'A')
        end++;

    
    //ex) BJAAAAZZ
    int minVerCost = 0;
    while(end < name.length()){
        
        int backDistance = name.length() - end;
        int verCost = front + backDistance;
        
        if(front < backDistance)
            verCost += front;
        else
            verCost += backDistance;
        
        if(minVerCost == 0)
            minVerCost = verCost;
        else if(minVerCost > verCost)
            minVerCost = verCost;

        front = end;
        end++;
        while(end < name.length() && name.charAt(end) == 'A')
            end++;
    }
        if(front < minVerCost)
            minVerCost = front;

        cost += minVerCost;

        return cost;        
    }
}
