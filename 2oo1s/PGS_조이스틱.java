import java.util.*;

class Solution {

  public int solution(String name) {
    int answer = 0;

    int len = name.length();
    int move = len - 1; // 오른쪽으로 움직이는 횟수

    for (int i = 0; i < len; i++) {
      /* A와 Z 중에 더 가까운 쪽으로부터 접근
      만약에 'C'면, C->B->A 가는게 조작 횟수가 최소가 되는거고
      'V'면, V->U->T->S .... ->B->A보다는 V->W->X->Y->Z->A로 가는게 조작횟수 최소	
      */
      answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);

      // 다음으로 오는 문자 중에서 연속된 'A'가 몇개인지 확인
      int next = i + 1;
      while (next < len && name.charAt(next) == 'A') {
        next++;
      }

      /* 'JAAAAAAWAN' 이렇게 들어오면 순서대로 오른쪽으로 가면 J부터 W까지 5번 이동
      근데 순서대로 말고 역순으로, J->N->A->W로 접근하는게 덜 조작해도 된다.
      */
      move = Math.min(move, i + (len - next) + Math.min(i, len - next));
    }
    answer += move;

    return answer;
  }
}
