package algorithm.Team03;

import java.util.Arrays;

public class PGS_체육복 {

	public int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;
        int count = 0; //빌린 학생 수
        
        //테스트케이스의 배열이 순서대로 안 되어 있을 수 있기 때문에 정렬을 해줘야 함!!!!!
        Arrays.sort(reserve);
        Arrays.sort(lost);
        
        //[1] 여벌 옷을 가지고 있는 학생이 체육복을 도난 당하면 빌려줄 수 없는 경우
        for(int i=0; i<lost.length; i++){
            for(int j=0; j<reserve.length; j++){
                if(lost[i]==reserve[j]){ //도난 당한 학생 == 여벌옷 가져온 학생
                    lost[i] = reserve[j] = -1; //-1로 초기화해서 다음 조건문 검사에 포함되지 않도록 만듦
                    count++; //자신의 여벌 체육복을 사용하기 때문에 수업을 들을 수 있음(count:+1)
                    break;
                }
            }
        }
        
        //[2] 도난당한 학생에서 체육복을 빌려주는 경우 
        for(int i=0; i<lost.length; i++) {
            for(int j=0; j<reserve.length; j++){
                if(i == reserve[j]-1 || i == reserve[j]+1){ //여벌 옷을 가지고 있는 학생의 앞의 학생이 도난당했거나 뒤의 학생이 도난당했으면
                    reserve[j] = -1;  //옷을 빌려주고 -1로 만들어 더 이상 빌려줄 수 없음
                    count++; //1명의 학생이 수업을 들을 수 있음(count:+1)
                    break;
                }
            }
        }
     
        answer = n - lost.length + count;  //전체 학생수 - 잃어버린 학생 수 + 자신의 여분을 사용하거나 빌린 학생 수
        return answer;
    }
}
