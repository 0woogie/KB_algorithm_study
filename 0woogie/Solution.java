package study;

import java.util.HashSet;

public class Solution {
    public int solution(int[] nums) {
        int n = nums.length; //������ ���ϸ� ��
        HashSet<Integer> s = new HashSet<>();
        for(int num : nums) s.add(num); //���տ� �ֱ� -> ���� ���� ���ϸ� �ߺ� ����
        if(n/2<s.size()) //���ϸ� ������ ���� �� �ִ� �������� ������
            return n/2; //���� �� �ִ� ������ŭ �������� �ϳ��� ��������
        else //������ �� ������
            return s.size(); //�ƽ����� �������� �����
    }
}
