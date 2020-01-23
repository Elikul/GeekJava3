package chat.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.*;

public class Server {
    private Vector<ClientHandler> clients;

    //логгер
    private static final Logger logger = Logger.getLogger(chat.server.AuthService.class.getName());
    public static void log(){
        try {
            Handler handler; //слушатель для логгера
            handler = new FileHandler("chatLog.log", true); //записываем лои в файл "chatLog.log"
            handler.setLevel(Level.ALL);
            handler.setFormatter(new SimpleFormatter());
            logger.addHandler(handler);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Server() {
        log();
        clients = new Vector<>();
        ServerSocket server = null;
        Socket socket = null;
        try {
            AuthService.connect();
            server = new ServerSocket(8189);
            logger.log(Level.INFO, "Сервер запущен!"); //лог
            System.out.println("Сервер запущен. Ожидаем клиентов...");
            while (true) {
                socket = server.accept();
                logger.log(Level.INFO, "Клиент поключился!"); //лог
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Ошибка!Сервер не смог запуститься!"); //лог
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Ошибка!"); //лог
            }
            try {
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
                logger.log(Level.SEVERE, "Ошибка сервера!"); //лог
            }
            AuthService.disconnect();
        }
    }

    public void sendPersonalMsg(ClientHandler from, String nickTo, String msg) {
        log();
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nickTo)) {
                o.sendMsg("from " + from.getNick() + ": " + msg);
                from.sendMsg("to " + nickTo + ": " + msg);
                logger.log(Level.INFO, "Клиент " + from.getNick() + " отправил личное сообщение " + nickTo); //лог
                return;
            }
        }
        from.sendMsg("Клиент с ником " + nickTo + " не найден в чате");
    }

    public void broadcastMsg(ClientHandler from, String msg) {
        log();
        for (ClientHandler o : clients) {
            if (!o.checkBlackList(from.getNick())) {
                o.sendMsg(msg);
                logger.log(Level.INFO, "Клиент " + from.getNick() +  " отправил сообщение!"); //лог
            }
        }
    }

    public boolean isNickBusy(String nick) {
        log();
        for (ClientHandler o : clients) {
            if (o.getNick().equals(nick)) {
                logger.log(Level.WARNING, "Ник занят! Клиент " + nick + " не смог войти!"); //лог
                return true;
            }
        }
        return false;
    }

    public void broadcastClientsList() {
        log();
        StringBuilder sb = new StringBuilder();
        sb.append("/clientslist ");
        for (ClientHandler o : clients) {
            sb.append(o.getNick() + " ");
            logger.log(Level.INFO, "Клиент " + o.getNick() +  " вошёл в систему!"); //лог
        }
        String out = sb.toString();
        for (ClientHandler o : clients) {
            o.sendMsg(out);
            logger.log(Level.INFO, "Клиент " + o.getNick() +  " отправил сообщение!"); //лог
        }
    }

    public void subscribe(ClientHandler client) {
        clients.add(client);
        broadcastClientsList();
    }

    public void unsubscribe(ClientHandler client) {
        clients.remove(client);
        broadcastClientsList();
    }
}
