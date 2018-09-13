package happynumber;

import java.util.HashSet;

/**
 * Created by Administrator on 2018/8/29.
 */
public class HappyNumber {
    public static boolean isHappy(int n) {
        HashSet<Integer> set = new HashSet<>();
        while( n != 1 ){
            if( set.contains(n) ){
                return false;
            }else{
                set.add(n);

                int sum = 0;
                while( n != 0 ){
                    int sth = n % 10;
                    sum += Math.pow(sth, 2);
                    n /= 10;
                }
                n = sum;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isHappy(200));
    }
}
