import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * 3. Написать метод, который проверяет состав массива из чисел 1 и 4.
 * Если в нем нет хоть одной четверки или единицы, то метод вернет false;
 * Написать набор тестов для этого метода (по 3-4 варианта входных данных).
 */

@RunWith(Parameterized.class)
public class checkArrayTest {
    private CheckArray41 mas;

    private int[] arr;
    private boolean check;

    @Parameterized.Parameters
    public static Collection params() {
        return Arrays.asList(
                new Object[][]{
                        {new int[]{1, 1, 1, 4}, true},
                        {new int[]{4, 4, 4, 1}, true},
                        {new int[]{4, 4, 4, 4}, false},
                        {new int[]{1, 1, 1, 1}, false},
                        {new int[]{1, 0, 1, 4}, false},
                        {new int[]{}, false},
                }
        );
    }

    public checkArrayTest(int[] arr, boolean check) {
        this.arr = arr;
        this.check = check;
    }

    @Before
    public void init() {
        mas = new CheckArray41();
    }

    @Test
    public void testCheckArrayFor1And4() {
        Assert.assertEquals(mas.checkArrayFor1And4(arr), check);
    }


    @After
    public void shutdown() throws Exception {
        mas = null;
        System.out.println("Тест выполнен!");
    }

}
