public class Test {

    public static int test(int a, int b, int toto[]) {
        int sum = 0;
        for(int i = a; i != b; i++) {
            sum += toto[i];
        }
        return sum;
    }
    public static void main(String[] args) {
        System.out.println(test(1,3, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10}));
    }
}
