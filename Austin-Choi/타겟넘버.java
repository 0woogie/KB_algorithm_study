class Solution {
    static int count;
    public int solution(int[] numbers, int target) {
    	count = 0;
        dfs(numbers, 0, 0, target);
        return count;
    }
    
    public static void dfs(int[] numbers, int n, int sum, int target){
        if(n == numbers.length){
        	if(sum == target) {
        		count++;
        		return;
        	}
        }
        else {
        	dfs(numbers, n+1, sum+numbers[n], target);
            dfs(numbers, n+1, sum-numbers[n], target);
        }
    }
}
