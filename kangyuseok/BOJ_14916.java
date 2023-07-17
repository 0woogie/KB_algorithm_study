import java.util.Scanner;

public class BOJ_14916 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int cnt = 0;
        while(n > 1){
            if(n % 5 == 0){
                n -= 5;
            }
            else n -= 2;
            cnt++;
        }
        if(n == 1) System.out.println(-1);
        else System.out.println(cnt);
    }
}
