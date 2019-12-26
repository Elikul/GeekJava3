/**
 * 1. Создать три потока, каждый из которых выводит определенную букву (A, B и C) 5 раз (порядок – ABСABСABС). Используйте wait/notify/notifyAll.
 * 2. Создать модель MFU с возможностью печати и сканирования(копирования) (данные процессы могут происходить параллельно).
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Вывод ABC 5 раз:");
        OutputABC();
        System.out.println();
        System.out.println("MFU сканирует и печатает 50 страниц");
        MFUtask();
    }

    public static void OutputABC() {
        ABCThreads t = new  ABCThreads();
        new Thread(() -> t.printA()).start();
        new Thread(() -> t.printB()).start();
        new Thread(() -> t.printC()).start();
    }


    public static void MFUtask() {
        MFU t = new MFU();
        t.copyDocuments(50);
    }
}
