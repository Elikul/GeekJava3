package ServerClient;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerMain {
    public static void main(String[] args) {

        ServerSocket server = null; //сервер
        Socket socket = null;  //сокет

        try {
            server = new ServerSocket(8189);

            socket = server.accept(); //подключения клиента к серверу

            Scanner in = new Scanner(socket.getInputStream()); //для ввода сообщений
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true); //для вывода сообщений
            Scanner console = new Scanner(System.in);  //для вывода сообщений на консоль

            //чтение сообщений от клиента
            Thread t1 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        String str = in.nextLine();
                        if(str.equals("/end")) {
                            out.println("/end");
                            break;
                        }
                        System.out.println("Client: " + str);
                    }
                }
            });
            t1.start();

            //отправка сообщений клиенту
            Thread t2 = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try{
                            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("stur.ser"));
                            Student s1 = (Student) ois.readObject();
                            ois.close();
                            s1.info();
                        }catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                }
            });
            t2.setDaemon(true);
            t2.start();

            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

