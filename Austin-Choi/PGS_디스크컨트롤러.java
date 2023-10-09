import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        //pair 로 넣고 start, jobTime
        //jobTime 기준으로 minheap 구성해서 넣고
        //총시간(jobTimeSum, 예시에서 18초짜리)은 pop된 jobTime sum해주고
        //answer = total( (jobTimeSum - 각 job의 start)들을 더한것 ) / jobs.length
        
        //반례 : (0,3) (1,9) (2,6) (80,1) 이라면 
        //80,1 이 제일 먼저 와서 망가짐 
        //따라서 우선순위큐에 2가지 비교를 하지 말고 미리 배열에서 정렬해서 큐에 넣어주기
        
        Arrays.sort(jobs, (a,b)->a[0]-b[0]);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> {
                return a[1] - b[1];
        });

        int jobTimeSum = 0;
        int total = 0;
        int cnt = 0;
        int jobIdx = 0;
       
        //현재 요청이 끝나는 시점 안에서 해결되는 요청까지만 담고 그다음에 시점 늘려서 담기
        while(cnt != jobs.length){
            while(jobIdx < jobs.length && jobs[jobIdx][0]<=jobTimeSum)
                pq.add(jobs[jobIdx++]);
                
            if(!pq.isEmpty()){
                int[] tmp2 = pq.poll();
                total += tmp2[1] + jobTimeSum - tmp2[0];
                jobTimeSum += tmp2[1];
                cnt++;
            }
            else
                //큐가 비었다면 그다음 요청 받을 시점으로 총 시간을 끌어다놓음 
                jobTimeSum = jobs[jobIdx][0];
        }
        return (int) Math.floor(total/jobs.length);
    }
}
