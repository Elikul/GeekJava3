package dz_1_AND_2;


import java.util.*;

/**
 * 1. Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);
 * 2. Написать метод, который преобразует массив в ArrayList;
 * @param <T>
 */

//обобщение массива
public class array<T>{
    private T[] mas; //массив

    //конструктор
    public array(T... mas) {
        this.mas = mas;
    }

    //метод, который меняет два элемента массива местами
    public void arrElementsExchange(){
        T tmp = mas[0];
        mas[0] = mas[1];
        mas[1] = tmp;

    }


    public void setArray(T[] mas) {
        this.mas = mas;
    }

    public T[] getArray() {
        return mas;
    }

    //вывод информации об объекте в консоль
    public void info() {
        for (T obj : mas)
            System.out.println(obj.getClass().getName());
    }


    //метод, который преобразует массив в ArrayList
    public ArrayList<T> arrayToArrayList() {
        ArrayList<T> list = new ArrayList<>(Arrays.asList(mas));
        return list;
    }
}

//класс птица
class Bird{
    public void info(){
        System.out.println("Птица");
    }

}

//класс животное
class Animal{
    public void info(){
        System.out.println("Животное");
    }

}



class Main {

    public static void main(String[] args) {
        System.out.println("Новый массив:");
        array<?> m = new array<>(new Animal(),new Bird()); //создаём массив из двух объектов
        m.info();

        System.out.println("\nМеняем элементы местами:");
        m.arrElementsExchange();
        m.info();

        System.out.println("\nArrayList:");
        ArrayList<?> list = m.arrayToArrayList();
        for (Object o : list) System.out.println(o.getClass().getName());
    }
}