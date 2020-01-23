/**
 * 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
 * Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */
public class CheckArray41 {

    public boolean checkArrayFor1And4(int[] arr) {
        int count1 = 0, count4 = 0;
        for (int x : arr) {
            if (x == 1) {
                count1++;
            }
            else if (x == 4) {
                count4++;
            }
            else {
                return false;
            }
        }
        return (count1 > 0 && count4 > 0);
    }
}
