/**
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 */


public class ABCThreads {
    private final Object obj = new Object(); //объект синхронизации
    private volatile char literal = 'A'; //литера

    //метод вывода буквы A на консоль
    public void printA() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (literal != 'A') {
                        obj.wait();
                    }
                    System.out.print("A");
                    literal = 'B';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //метод вывода буквы B на консоль
    public void printB() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (literal != 'B') {
                        obj.wait();
                    }
                    System.out.print("B");
                    literal = 'C';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //метод вывода буквы C на консоль
    public void printC() {
        synchronized (obj) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (literal != 'C') {
                        obj.wait();
                    }
                    System.out.print("C");
                    literal = 'A';
                    obj.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
