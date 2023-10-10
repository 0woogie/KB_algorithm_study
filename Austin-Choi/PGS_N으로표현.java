import java.util.*;

class Solution {
    public int solution(int N, int number) {
        //dp[i] = j
        //i는 N이 사용된 횟수
        //j는 number
        int answer = 0;
        List<HashSet<Integer>> dp = new ArrayList<HashSet<Integer>>();
        
        for(int i = 1; i<=9; i++){
            //dp[x] = new HashSet<int>(); N+N-N과 N-N+N의 조합처럼 둘다 결과는 N이므로 중복 제거하는 set
            dp.add(new HashSet<Integer>());
        }
        
        for(int i = 1; i<=8; i++){    
            //N NN NNN 등으로 표현 가능한 숫자 먼저 추가
            StringBuilder sb = new StringBuilder();
            for(int j = 1; j<=i; j++)
                sb.append("1");
            dp.get(i).add(N*Integer.parseInt(sb.toString()));
            
            if(i>1)
            {
            //이때, i = 4일때 dp[i]를 구성하는 조합은
            //dp[2]랑 dp[2], dp[1]랑 dp[3], dp[3]랑 dp[1]
                for(int j = 1; j<=i; j++){
                    //left, right hand side 
                    Set<Integer> lhs = dp.get(j);
                    Set<Integer> rhs = dp.get(i-j);
                    
                    for(int lhsNum : lhs){
                        for(int rhsNum : rhs){
                            dp.get(i).add(lhsNum + rhsNum);
                            dp.get(i).add(lhsNum - rhsNum);
                            dp.get(i).add(lhsNum * rhsNum);
                            if((lhsNum & rhsNum) != 0)
                                dp.get(i).add(lhsNum / rhsNum);
                        }
                    }
                }
            }
        }
        
        Boolean flag = true;
        for(int i = 1; i<=8; i++){
            //앞에서부터 사용 횟수가 작은 순서대로 들어가있을것이므로
            //contains로 answer 반환
            if(dp.get(i).contains(number)){
                answer = i;
                flag = false;
                break;
            }
        }
            
        if(flag)
            return -1;
        return answer;
    }
}
