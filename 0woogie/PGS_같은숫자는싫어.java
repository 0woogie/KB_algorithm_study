package study;

import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public int[] solution(int []arr) {
        Queue<Integer> queue = new LinkedList<>();
        int x = -1; //���ڰ� ���ӵǴ��� Ȯ���ϱ� ���� ����
        for(int num : arr) {
            if(num!=x) { //������ ���ڿ� �ٸ��ٸ� (==���ӵǴ� ���� �ƴϸ�)
                x = num;
                queue.add(num);
            }
        }
        int[] answer = new int[queue.size()];
        for(int i=0; i<answer.length; i++)
            answer[i] = queue.poll(); //ť���� ������� ���� ��� �迭�� �Ѱ��ֱ�
        return answer;
    }
}