package add_dz;

/**
 * Operationable operation = (int x, int y)-> {
 *
 *     if(y==0)
 *         return 0;
 *     else
 *         return x/y;
 * };
 *
 * нужно добивать обощение чтобы можно было использовать вот так
 *
 * Operationable<Integer> operation1 = (x, y)-> x + y;
 * Operationable<String> operation2 = (x, y) -> x + y;
 */

public class Lambda2 {
    public Lambda2() {
    }

    public static void main(String[] args) {
        Operationable2<Integer> operation1 = (x, y)-> x + y;
        Operationable2<String> operation2 = (x, y) -> x + y;

        int resultInt = operation1 .calculate(10, 20);
        System.out.println("10 + 20 = " + resultInt);

        String resultStr = operation2 .calculate("Hello", "world");
        System.out.println(resultStr);
    }
}
