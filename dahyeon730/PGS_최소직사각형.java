package algorithm.Team03;

public class PGS_최소직사각형 {

	public int solution(int[][] sizes) {
        int answer = 0;
        int max_width = 0; //최대 가로값
        int max_height = 0; //최대 세로값
        
        for(int i=0; i<sizes.length; i++) { //명함 개수만큼 반복
        	if(sizes[i][0] < sizes[i][1]) { //세로가 더 크면 가로랑 바꿈 (두 변 중 긴 부분을 가로, 짧은 부분을 세로)
        		int temp = sizes[i][0];
        		sizes[i][0] = sizes[i][1];
        		sizes[i][1] = temp;
        	}
        	
        	if(max_width < sizes[i][0]) //가로 중 최대값
        		max_width = sizes[i][0];
        	
        	if(max_height < sizes[i][1]) //세로 중 최대값
        		max_height = sizes[i][1];
        }
        answer = max_width*max_height; //지갑 크기
        return answer;
    }
}