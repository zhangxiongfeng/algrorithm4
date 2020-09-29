public class gcd {
    public static void main(String[] args) {
        int res = gcd(10, 4);
        System.out.println(res);

    }
    public static int gcd(int a , int b){
        if(b==0){
            return a;
        }
        if (a<b){
            int tmp;
            tmp = a;
            a = b;
            b = tmp;
        }
        return gcd(b, a%b);
    }
}
