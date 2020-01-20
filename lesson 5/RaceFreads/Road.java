/**
 * Участок трассы - Дорога
 */
public class Road extends Stage {

    //конструктор
    public Road(int length) {
        this.length = length; //длина дороги
        this.description = "Дорога " + length + " метров"; //описание дороги
    }

    //метод вождения машины по дороге
    @Override
    public void go(Car c) {
        try {
            System.out.println(c.getName() + " начал этап: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}