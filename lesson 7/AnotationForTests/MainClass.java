import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.net.URLClassLoader;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 1 Создать класс, который может выполнять «тесты».
 * В качестве тестов выступают классы с наборами методов с аннотациями @Test.
 * Для этого у него должен быть статический метод start(),
 * которому в качестве параметра передается или объект типа Class, или имя класса.
 * Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
 * Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
 * К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения.
 * Если приоритет одинаковый, то порядок не имеет значения.
 * Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
 * иначе необходимо бросить RuntimeException при запуске «тестирования».
 */
public class MainClass {
    public static void main(String[] args) throws Exception {
        start(TestTAsk.class);
        System.out.println();
    }

    public static void start(Class c) throws Exception {
        Method[] methods = c.getDeclaredMethods();
        int bsCount = 0, asCount = 0;
        List<Method> tests = new ArrayList<>();

        // проверяет на уникальность BeforeSuite и AfterSuite . добавляет тесты в arraylist
        for (Method o : methods) {
            String type = o.getDeclaredAnnotations()[0].annotationType().getSimpleName();
            if (type.equals("BeforeSuite")) {
                bsCount++;
                if (bsCount > 1) throw new RuntimeException("Вы можете использовать только одну before annotation.");
            } else if (type.equals("AfterSuite")) {
                asCount++;
                if (asCount > 1) throw new RuntimeException("Вы можете использовать только одну after annotation.");
            } else if (type.equals("Test")) {
                tests.add(o);
            }
        }

        // Сортировка ткстов по приоритетам
        tests.sort((o1, o2) -> o1.getAnnotation(Test.class).priority() - o2.getAnnotation(Test.class).priority());

        // Добавляем BeforeSuite в начало тестов и  AfterSuite в конец.
        for (Method o : methods) {
            String type = o.getDeclaredAnnotations()[0].annotationType().getSimpleName();
            if (type.equals("BeforeSuite")) {
                tests.add(0, o);
            }
            if (type.equals("AfterSuite")) {
                tests.add(o);
            }
        }

        // вывод на консоль информации о тестах
        for (Method i : tests) {
            try {
                System.out.print("(" + i.getDeclaredAnnotation(Test.class).priority() + ") ");
            } catch (NullPointerException e) {

            }
            i.invoke(c.newInstance(), null);
        }
    }


//    public static void start(String className) {
//        try {
//            Class<?> c = Class.forName(className);
//            Constructor<?> constructor = c.getConstructor(null);
//            start(c);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
