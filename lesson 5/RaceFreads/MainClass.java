import java.util.concurrent.CyclicBarrier;

/**
 * Организуем гонки:
 * Все участники должны стартовать одновременно, несмотря на то, что на подготовку у каждого их них уходит разное время.
 * В тоннель не может заехать одновременно больше половины участников (условность).
 * Попробуйте все это синхронизировать.
 * Только после того, как все завершат гонку, нужно выдать объявление об окончании.
 * Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты классов из пакета util.concurrent.
 */

public class MainClass {
    public static final int CARS_COUNT = 4; //максимальное количество машин,которе может участвовать в гонке
    public static Thread[] threads = new Thread[CARS_COUNT]; //потоки = количество машин (один поток-одна машина)


    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT/2), new Road(40)); //трасса - дорога 60м, тунель 80м, дорога 40м
        Car[] cars = new Car[CARS_COUNT]; //массив машин

        Car.setCb(new CyclicBarrier(CARS_COUNT,new StartMessage())); // устанавливаем барьер для того, чтобы все машины начали ехать одновременно


        //едут дистанцию с разным времнем
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        //создаём и запускаем потоки(машины)
        for (int i = 0; i < CARS_COUNT; i++) {
            threads[i] = new Thread(cars[i]);
            threads[i].start();
        }

        for (int i = 0; i < cars.length; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}



