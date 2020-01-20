import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Машины для гонки
 */
public class Car implements Runnable {
    private static int CARS_COUNT; //количество машин
    private static CyclicBarrier cb; //барьер
    private static AtomicInteger winner = new AtomicInteger(0); //атомарный объект, для определения победителя гонки

    //метод установления баррьера, для того чтобы машины выехали одновременно
    static void setCb(CyclicBarrier _cb){
        Car.cb = _cb;
    }


    static {
        CARS_COUNT = 0;
    }

    private Race race;  //трасса
    private int speed; //скорость машины
    private String name; //имя участника

    //метод получения имени участника
    public String getName() {
        return name;
    }

    //метод получение скорости машины
    public int getSpeed() {
        return speed;
    }

    //конструктор
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    //метод вождения машины
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int)(Math.random() * 800));
            System.out.println(this.name + " готов");
            cb.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        //определяем победителя (кто первый закончит всю трассу, тот и выиграл)
        if (winner.incrementAndGet() == 1){
            System.out.println(this.name + " WIN");
        }
    }
}

//метод сообщения о начале гонки (т.е. когда все машины выедут одновременно)
class StartMessage implements Runnable {
    @Override
    public void run() {
        System.out.println("ВАЖНОЕ ОБЪЯВЕНИЕ >>> Гонка началась!!!");
    }
}



