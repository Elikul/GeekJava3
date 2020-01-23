import java.util.Arrays;

/**
 * 2. Написать метод, которому в качестве аргумента передается
 * не пустой одномерный целочисленный массив.
 * Метод должен вернуть новый массив, который получен путем вытаскивания
 * из исходного массива элементов, идущих после последней четверки.
 * Входной массив должен содержать хотя бы одну четверку,
 * иначе в методе необходимо выбросить RuntimeException.
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 * Вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ].
 */
public class getArrayAfterLast4 {
    public int[] getPartOfArray(int[] arr) throws RuntimeException {
        if (arr.length == 0) {
            throw new NullPointerException();
        }

        int lastIndexOf4 = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 4) {
                lastIndexOf4 = i + 1;
            }
        }

        if (lastIndexOf4 == -1){
            throw new RuntimeException();
        }
        else {
            return Arrays.copyOfRange(arr, lastIndexOf4, arr.length);
        }
    }
}