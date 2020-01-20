import java.util.concurrent.Semaphore;

/**
 * Участок трассы - тунель
 */
public class Tunnel extends Stage {
    Semaphore smp; //семафор для синхронизации машин на трассе

    //конструктор с парамметроим количества машин,которые могут быть одновременно в тунеле
    public Tunnel(int maxContOfCars) {
        smp = new Semaphore(maxContOfCars);
        this.length = 80;
        this.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car c) {
        try {
            try {
                System.out.println(c.getName() + " готовится к этапу(ждет): " + description);
                smp.acquire(); // могут ехать по тунелю (захватывают ресурс)
                System.out.println(c.getName() + " начал этап: " + description);
                Thread.sleep(length / c.getSpeed() * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println(c.getName() + " закончил этап: " + description);
                smp.release(); //  выехали из тунеля (освобождают ресурс)
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}