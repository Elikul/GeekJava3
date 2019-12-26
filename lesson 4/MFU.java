/**
 * 2. Создать модель MFU с возможностью печати и сканирования(копирования) (данные процессы могут происходить параллельно).
 */


public class MFU {
    private final Object mfu = new Object(); //Объект синхронизации
    private volatile char action = 's';  //действие 's' - сканировать ,'p' - печатать
    private int printed = 0, scanned = 0; //количество напечатаных и отсканированных документов

    //метод распечатывания документов
    public void printDocuments(int count) {
        synchronized (mfu) {
            try {
                for (int i = 0; i < count; i++) {
                    while (action != 'p') {
                        mfu.wait();
                    }
                    setPrinted(getPrinted() + 1);
                    System.out.println("Распечатано " + getPrinted() + " страниц");
                    action = 's';
                    Thread.sleep(50);
                    mfu.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    //метод сканирования документов
    public void scanDocuments(int count) {
        synchronized (mfu) {
            try {
                for (int i = 0; i < count; i++) {
                    while (action != 's') {
                        mfu.wait();
                    }
                    setScanned(getScanned() + 1);
                    System.out.println("Отсканировано " + getScanned() + " страниц");
                    action = 'p';
                    Thread.sleep(50);
                    mfu.notify();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //методд копирования документов
    public void copyDocuments(int count) {
        new Thread(() -> this.scanDocuments(count)).start();
        new Thread(() -> this.printDocuments(count)).start();
    }


    //метод получения количества распечатанных страниц документа
    public int getPrinted() {
        return printed;
    }

    //метод установки количества  страниц документа, которые нкжно распечатать
    public void setPrinted(int printed) {
        this.printed = printed;
    }

    //метод получения количества отсканиованных страниц документа
    public int getScanned() {
        return scanned;
    }

    //метод установки количества страниц документа, которые нужно отсканировать
    public void setScanned(int scanned) {
        this.scanned = scanned;
    }
}
