package dz_3;

import java.util.ArrayList;

/**
 * Класс коробка для фруктов
 */
public class Box<T extends Fruit> {

    private ArrayList<T> fruits; //список фруктов

    //конструктор, создание списка фруктов
    public Box(){
        fruits = new ArrayList<>();
    }

    //конструктор
    public Box(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    //конструктор, добавление в список фруктов
    public Box(T fruit) {
        fruits = new ArrayList<>();
        fruits.add(fruit);
    }


    public ArrayList<T> getFruits() {
        return fruits;
    }

    public void setFruits(ArrayList<T> fruits) {
        this.fruits = fruits;
    }

    //метод полученя веса коробки
    public float getWeight() {
        return fruits.size() == 0 ? 0 : fruits.size() * fruits.get(0).getWeight();
    }


    //позволяет сравнить текущую коробку с той, которую подадут в compare в качестве параметра
    //true – если она равны по весу, false – в противном случае
    public boolean compare(Box<?> otherBox) {
        return this.getWeight() == otherBox.getWeight();
    }

    //метод, который позволяет пересыпать фрукты из текущей коробки в другую
    public void replaceAllFruitsToOtherBox(Box<T> otherBox) {
        otherBox.fruits.addAll(fruits);
        fruits.clear();
    }

    //метод добавления фрукта в коробку
    public void addFruit(T fruitToAdd) { fruits.add(fruitToAdd); }
}
