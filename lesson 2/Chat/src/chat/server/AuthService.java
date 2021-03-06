package chat.server;

import java.sql.*;
import java.util.Date;

public class AuthService {
    private static Connection connection;
    private static Statement stmt;

    public static void connect() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            stmt = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addUser(String login, String pass, String nick) {
        try {
            String query = "INSERT INTO main (login, password, nickname) VALUES (?, ?, ?);";
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, login);
            ps.setInt(2, pass.hashCode());
            ps.setString(3, nick);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getNickByLoginAndPass(String login, String pass) {
        try {
            ResultSet rs = stmt.executeQuery("SELECT nickname, password FROM main WHERE login = '" + login + "'");
            int myHash = pass.hashCode();
            if (rs.next()) {
                String nick = rs.getString(1);
                int dbHash = rs.getInt(2);
                if (myHash == dbHash) {
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //логирование входа
    public static void logs_in(String nick){
        Date date = new Date();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id, nickname FROM main WHERE nickname = " + nick);
            stmt.executeUpdate("INSERT INTO logs_in (id_person,Date) VALUES ( " + rs + " , " + date.getTime() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //логирование выхода
    public static void logs_out(String nick){
        Date date = new Date();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id, nickname FROM main WHERE nickname = " + nick);
            stmt.executeUpdate("INSERT INTO logs_out (id_person,Date) VALUES ( " + rs + " , " + date.getTime() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //логирование неудачного входа
    public static void logs_unsuccesful_in(String nick){
        Date date = new Date();
        try {
            ResultSet rs = stmt.executeQuery("SELECT id, nickname FROM main WHERE nickname = " + nick);
            stmt.executeUpdate("INSERT INTO logs_unsuccesful_in (id_person,Date) VALUES ( " + rs + " , " + date.getTime() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
