import java.util.*;
class Solution
{
public
    int solution(int n, int[] lost, int[] reserve)
    {
        int answer = n;
        int[] arr = new int[n + 1];
        Arrays.fill(arr, 1);
        for (int i : lost)
            arr[i]--;
        for (int i : reserve)
            arr[i]++;
        for (int i = 1; i <= n; i++)
        {
            if (arr[i] == 0)
            {
                if (arr[i - 1] == 2)
                    arr[i - 1]--;
                else if (i + 1 <= n && arr[i + 1] == 2)
                    arr[i + 1]--;
                else
                    answer--;
            }
        }
        return answer;
    }
}