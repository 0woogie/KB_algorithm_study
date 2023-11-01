import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;
import java.util.Stack;
import java.util.StringTokenizer;

public class 연습용 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt()-1;
        int c = sc.nextInt()-1;
        int k = sc.nextInt();
        int map[][] = new int[3][3];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int time=0; boolean flag=true;
        while(true) {
            if(map.length >r && map[0].length > c && map[r][c]==k) {
                break;
            }
            int rnum = map.length;
            int cnum = map[0].length;
            if(rnum>=cnum) {
                ArrayList<Pair> list[] = new ArrayList[rnum];
                int colsize = 0;
                for (int i = 0; i < rnum; i++) {
                    list[i] = new ArrayList<>();
                }
                for (int i = 0; i < rnum; i++) {
                    int[] candy = new int[101];
                    for (int j = 0; j < cnum; j++) {
                        if(map[i][j]!=0)
                            candy[map[i][j]]++;
                    }
                    //열탐색이 끝나고 리스트 선언 후 넣어준다

                    int cnt=0;
                    for (int cidx = 1; cidx < candy.length; cidx++) {
                        if(candy[cidx]!=0) {
                            list[i].add(new Pair(cidx,candy[cidx]));
                            cnt++;
                            colsize = colsize < cnt ? cnt: colsize;
                        }
                    }
                    //넣고나서 우선순위 정렬
                    Collections.sort(list[i]);
                }//2차원 배열 끝
                //그 토대로 새로운 배열 생성 후 새로운 값 넣기
                map = new int[rnum][colsize*2];
                for (int i = 0; i < rnum; i++) {
                    int idx=0;
                    for (int j = 0; j < list[i].size(); j++) {
                        map[i][idx++] = list[i].get(j).num;
                        map[i][idx++] = list[i].get(j).rep;
                    }
                }//맵 갱신
            }//행이 더 많을 때 끝
            else {
                ArrayList<Pair> list[] = new ArrayList[cnum];
                int rowsize = 0;
                for (int i = 0; i < cnum; i++) {
                    list[i] = new ArrayList<>();
                }
                for (int j = 0; j < cnum; j++) {//열을 기준으로
                    int[] candy = new int[101];
                    for (int i = 0; i < rnum; i++) {//행을 탐색한다
                        if(map[i][j]!=0)
                            candy[map[i][j]]++;
                    }//행 탐색 끝
                    int cnt=0;
                    for (int cidx = 1; cidx < candy.length; cidx++) {//캔디 사이즈만큼 반복
                        if(candy[cidx]!=0) {
                            list[j].add(new Pair(cidx,candy[cidx]));
                            cnt++;
                            rowsize = rowsize < cnt ? cnt: rowsize;
                        }
                    }
                    Collections.sort(list[j]);
                }
                map = new int[rowsize*2][cnum];
                //map열을 기준으로 해서 리스트를 뽑는다
                for (int j = 0; j < cnum; j++) {
                    int idx=0;
                    //리스트에 탐색하면서 열은 고정하고, 행을 증가하면서 넣는중이다 
                    for (int i = 0; i < list[j].size(); i++) {
                        map[idx++][j] = list[j].get(i).num;
                        map[idx++][j] = list[j].get(i).rep;
                    }
                }//맵 갱신
            }
            //초 증가
            time++;
            //100초 초과시 반복끝
            if(time>100) {
                flag=false;
                break;
            }
        }//while반복문
        if(flag)
            System.out.println(time);
        else
            System.out.println(-1);
    }
    
    static class Pair implements Comparable<Pair>{
        int num;
        int rep;
        public Pair(int num, int rep) {
            this.num = num;
            this.rep = rep;
        }
        @Override
        public int compareTo(Pair o) {
            if(this.rep==o.rep) {
                return this.num - o.num;
            }
            return this.rep-o.rep;
        }
    }
}
