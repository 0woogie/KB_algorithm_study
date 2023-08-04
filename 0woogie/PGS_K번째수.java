package study;

import java.util.Arrays;

class Solution {
    public int[] solution(int[] array, int[][] commands) {
        int[] result = new int[commands.length];
        
        for(int i=0; i<commands.length; i++) {
        	int a = commands[i][0];
        	int b = commands[i][1];
        	int k = commands[i][2];
        	int[] tmp = new int[b-a+1];
        	for(int j=a-1; j<b; j++)
        		tmp[j-a+1] = array[j]; //array�� a��° ���ں��� b��° ���ڱ��� ��������
        	Arrays.sort(tmp); //����
        	result[i] = tmp[k-1]; //k��° ���� ���ϱ�
        }
        
        return result;
    }
}