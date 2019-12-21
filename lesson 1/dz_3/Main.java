package dz_3;

/**
 * Есть классы Fruit -> Apple, Orange (больше фруктов не надо);
 * Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
 * Для хранения фруктов внутри коробки можно использовать ArrayList;
 * Сделать метод getWeight(), который высчитывает вес коробки, зная количество фруктов и вес одного фрукта (вес яблока – 1.0f, апельсина – 1.5f. Не важно, в каких это единицах);
 * Внутри класса Коробка сделать метод compare, который позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра, true – если она равны по весу, false – в противном случае (коробки с яблоками мы можем сравнивать с коробками с апельсинами);
 * Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую (помним про сортировку фруктов: нельзя яблоки высыпать в коробку с апельсинами). Соответственно, в текущей коробке фруктов не остается, а в другую перекидываются объекты, которые были в этой коробке;
 * Не забываем про метод добавления фрукта в коробку.
 */
public class Main {
    public static void main(String[] args) {
        Box<Apple> appleBox1 = new Box<>(); //коробка с яблоками
        Box<Orange> orangeBox1 = new Box<>(); //коробка с апельсинами

        //создадим 3 яблока и 2 апельсина
        Apple ap1 = new Apple();
        Apple ap2 = new Apple();
        Apple ap3 = new Apple();
        Orange or1 = new Orange();
        Orange or2 = new Orange();

        System.out.println("Добавляем 3 яблока в коробку с яблоками");
        System.out.println("Вес одного яблока: " + ap1.getWeight());
        appleBox1.addFruit(ap1);
        appleBox1.addFruit(ap2);
        appleBox1.addFruit(ap3);

        System.out.println("Добавляем 2 апельсина в коробку с апельсинами");
        System.out.println("Вес одного апельсина: " + or1.getWeight() + "\n");
        orangeBox1.addFruit(or1);
        orangeBox1.addFruit(or2);

        System.out.println("Вес коробки с яблоками: " + appleBox1.getWeight());
        System.out.println("Вес коробки с апельсинами: " + orangeBox1.getWeight());

        System.out.println("Сравниваем вес коробки с яблоком и коробки с апельсином: " + appleBox1.compare(orangeBox1) + "\n");

        Box<Apple> appleBox2 = new Box<>(); //создаём новую коробку для яблок, чтобы переложить

        System.out.println("Вес коробок с яблоками до перекладывания: ");
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2: " + appleBox2.getWeight());
        System.out.println("\nПереложим яблоки из appleBox1 в appleBox2\n");
        appleBox1.replaceAllFruitsToOtherBox(appleBox2);
        System.out.println("Вес коробок с яблоками после перекладывания: ");
        System.out.println("Вес appleBox1: " + appleBox1.getWeight());
        System.out.println("Вес appleBox2: " + appleBox2.getWeight());

        Box<Orange> orangeBox2 = new Box<>(); //создаём новую коробку для апельсинов, чтобы переложить

        System.out.println("\nВес коробок с апельсинами до перекладывания: ");
        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());
        System.out.println("Вес orangeBox2: " + orangeBox2.getWeight());
        System.out.println("\nПереложим апельсины из orangeBox1 в orangeBox2\n");
        orangeBox1.replaceAllFruitsToOtherBox(orangeBox2);
        System.out.println("Вес коробок с апельсинами после перекладывания: ");
        System.out.println("Вес orangeBox1: " + orangeBox1.getWeight());
        System.out.println("Вес orangeBox2: " + orangeBox2.getWeight());
    }
}
