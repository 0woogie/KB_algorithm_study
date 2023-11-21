import java.util.*;
//deque

class Solution {
    
    Deque<Integer> leftCol;
    Deque<Integer> rightCol;
    Deque<Deque<Integer>> mid;
    
    public void rotate(){
        //북동남서 순으로
        mid.peekFirst().addFirst(leftCol.poll());
        rightCol.addFirst(mid.peekFirst().removeLast());
        mid.peekLast().add(rightCol.removeLast());
        leftCol.addLast(mid.peekLast().poll());
    }
    public void shiftRow(){
        leftCol.addFirst(leftCol.removeLast());
        rightCol.addFirst(rightCol.removeLast());
        mid.addFirst(mid.removeLast());
    }
    public int[][] solution(int[][] rc, String[] op) {
        int[][] answer = new int[rc.length][rc[0].length];
        
        leftCol = new LinkedList<>();
        rightCol = new LinkedList<>();
        mid = new Deque<Integer>[rc[0].length-2];
        
        for(int i = 0; i<rc.length; i++) {
        	mid.add(new LinkedList<>());
        	for(int j = 1; j<rc[0].length-1; j++){
                mid.peekLast().add(rc[i][j]);
            }
        }
        
        for(int i = 0; i<rc.length; i++){
            for(int j = 0; j<rc[0].length; j++){
                if(j == 0){
                    leftCol.add(rc[i][j]);
                }
                else if(j == rc[0].length-1){
                    rightCol.add(rc[i][j]);
                }
            }
        }        
//         int rotateCount = 0;
//         int shiftCount = 0;
        
//         // 테두리만큼 rotate 수행하면 원자리
//         // 높이만큼 shift 수행하면 원자리
//         int rotateLimit = (rc[0].length + rc.length -2) * 2;
//         int shiftLimit = rc.length;
        
//         String prevOp = null;
        
//         if(op[0].equals("Rotate")){
//             rotate();
//             prevOp = "Rotate";
//         }
//         else if(op[0].equals("ShiftRow")){
//             shiftRow();
//             prevOp = "ShiftRow";
//         }

        // for(int i = 1; i<op.length; i++){
        //     if(op[i].equals("Rotate") && prevOp.equals("Rotate")){
        //         rotateCount++;
        //         if(rotateCount % rotateLimit)
        //     }
        //     else{
        //         shiftRow();
        //     }
        // }
        
        for(int i = 0; i<op.length; i++){
            if(op[i].equals("Rotate"))
                rotate();
            else
                shiftRow();
        }
        
        for(int i = 0; i<rc.length; i++){
            for(int j = 0; j<rc[0].length; j++){
                if(j == 0){
                    answer[i][j] = leftCol.poll();
                }
                else if(j == rc[0].length-1){
                    answer[i][j] = rightCol.poll();
                }
                else{
                    for(int n : mid.poll()){
                        answer[i][j] = n;
                    }
                }
            }
        }
        
        // StringBuilder sb = new StringBuilder();
        // for(int i = 0; i<answer.length; i++){
        //     for(int j : answer[i]){
        //         sb.append(j).append(" ");
        //     }
        //     sb.append("\n");
        // }
        // System.out.print(sb);
        return answer;
    }
}
