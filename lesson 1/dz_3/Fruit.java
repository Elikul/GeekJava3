package dz_3;

//абстрактный класс фрукт
public abstract class Fruit {
    public abstract float getWeight();
}


//класс яблоко
class Apple extends Fruit {
    private float weight = 1.0f; //вес одного яблока

    //метод получения веса яблок
    @Override
    public float getWeight() { return weight; }
}

//класс апельсин
class Orange extends Fruit {
    private float weight = 1.5f; //вес одного апельсина

    //метод получения веса апельсин
    @Override
    public float getWeight() { return weight; }
}
