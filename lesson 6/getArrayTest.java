import org.junit.*;

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
public class getArrayTest {
    getArrayAfterLast4 mas;

    @Before
    public void init() {
        mas = new  getArrayAfterLast4();
    }


    @Test
    public void testGetPartOfArr1() {

        Assert.assertArrayEquals(new int[]{1,7},mas.getPartOfArray(new int[]{1,2,4,4,2,3,4,1,7}));
    }

    @Test
    public void testGetPartOfArr2() {

        Assert.assertArrayEquals(new int[]{5,6,2,3,5,8},mas.getPartOfArray(new int[]{2,4,5,6,2,3,5,8}));
    }

    @Test
    public void testGetPartOfArr3()
    {
        Assert.assertArrayEquals(new int[]{0,3,5,2,5},mas.getPartOfArray(new int[]{4,0,3,5,2,5}));
    }

    @Test(expected = RuntimeException.class)
    public void testGetPartOfArr4() {

        Assert.assertArrayEquals(new int[]{ },mas.getPartOfArray(new int[]{1,0,2,3,67}));
    }

    @Test
    public void testGetPartOfArr5() {

        Assert.assertArrayEquals(new int[]{ },mas.getPartOfArray(new int[]{4}));
    }

    @Test(expected = NullPointerException.class)
    public void testGetPartOfArr6() {

        Assert.assertArrayEquals(new int[]{ },mas.getPartOfArray(new int[]{ }));
    }

    @After
    public void shutdown() {
        System.out.println("Тест выполнен!");
    }
}
