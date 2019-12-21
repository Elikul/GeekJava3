package add_dz;

/**
 * Сделать методы для калькулятора (сложение двух чисел, деление и умножение) через лямбду
 */
public class Lambda {

    public Lambda() {
    }

    public static void main(String[] args) {
        Operationable add = (x, y) -> {
            return x + y;
        };
        Operationable sub = (x, y) -> {
            return x - y;
        };
        Operationable multip = (x, y) -> {
            return x * y;
        };
        Operationable div = (x, y) -> {
          try {
              return x/y;
          }catch (ArithmeticException err){
              System.out.println("Не можем делить на 0");
          }
          return -1;
        };
        double result = add .calculate(10, 20);
        System.out.println("10 + 20 = " + result);
        result = sub .calculate(10, 20);
        System.out.println("10 - 20 = " + result);
        result = multip .calculate(10, 20);
        System.out.println("10 * 20 = " + result);
        result = div .calculate(10, 20);
        System.out.println("10 / 20 = " + result);
    }
}
