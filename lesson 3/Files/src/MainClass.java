/**
 * 1.  Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
 * 2. Последовательно сшить 5 файлов в один (файлы примерно 100 байт). Может пригодиться следующая конструкция:
 * ArrayList<InputStream> al = new ArrayList<>(); ... Enumeration<InputStream> e = Collections.enumeration(al);
 * 3. Написать консольное приложение, которое умеет постранично читать текстовые файлы (размером > 10 mb).
 * Вводим страницу (за страницу можно принять 1800 символов), программа выводит ее в консоль. Контролируем время выполнения:
 * программа не должна загружаться дольше 10 секунд, а чтение – занимать свыше 5 секунд.
 * 5. Прочитать содержимое файла в обратном порядке.
 */


import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;

public class MainClass {
    public static void main(String[] args) {
        readFileAsByteArray(); //Прочитать файл (около 50 байт) в байтовый массив и вывести этот массив в консоль;
        System.out.println();
        union5FilesToOne();  //Последовательно сшить 5 файлов в один
        System.out.println("Файлы сшиты!");
        reverseReadFromFile(); //Прочитать содержимое файла в обратном порядке
        AppToReadBigFile(); // консольное приложение, которое умеет постранично читать текстовые файлы
    }

    //Прочитать файл в байтовый массив и вывести этот массив в консоль;
    public static void readFileAsByteArray() {
        try (ByteArrayInputStream in = new ByteArrayInputStream(Files.readAllBytes(Paths.get("One.txt")))) {
            int b;
            while ((b = in.read()) != -1) {
                System.out.print(b + " ");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //Последовательно сшить 5 файлов в один
    public static void union5FilesToOne() {
        try (FileOutputStream unionFile = new FileOutputStream("Two.txt")) {
            ArrayList<FileInputStream> listOfFile = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                listOfFile.add(new FileInputStream("dzTwo" + i + ".txt"));
            }
            Enumeration<FileInputStream> e = Collections.enumeration(listOfFile);

            SequenceInputStream in = new SequenceInputStream(e);

            int b;
            while (true) {
                if ((b = in.read()) != -1) unionFile.write(b);
                else break;
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // консольное приложение, которое умеет постранично читать текстовые файлы
    public static void AppToReadBigFile() {
        try (RandomAccessFile raf = new RandomAccessFile("Three.txt", "r");
             BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            long fileLength = raf.length();
            long pageLength = 1800;
            long pagesCount = fileLength/pageLength;
            byte[] b = new byte[1800];
            System.out.println("Размер файла(б): " + fileLength + " | Размер страницы(б): " + pageLength + " | Количество страниц: " + pagesCount);
            while (true) {
                System.out.println("\n\nВведите страницу от 0 до " + pagesCount + ". Введите -1 чтобы выйти.");
                long page = Long.parseLong(br.readLine());
                if (page <= pagesCount && page >= 0) {
                    raf.seek(page * pageLength);
                    raf.read(b, 0, b.length);
                    for (byte obj : b)
                        System.out.print((char) obj);
                } else if (page == -1) {
                    System.out.println("\nЧтение файла окончено!");
                    System.exit(0);
                } else {
                    System.out.println("\nТакой страницы нет: " + page);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


   // Прочитать содержимое файла в обратном порядке
   public static void reverseReadFromFile() {
        try{
            File file = new File("reverse.txt");
            RandomAccessFile raf = new RandomAccessFile("reverse.txt","r");
            String line = " ";
            int x = 0;
            long fileLength = file.length();
            for (int i = 0; i <= fileLength; i++) {
                raf.seek(fileLength - i);
                x = raf.read();
                line += (char) x;
            }
            System.out.println("Чтение из файла в обратном направлении: ");
            System.out.println(line);
        }catch (Exception e) {
            e.printStackTrace();
        }

   }
}
