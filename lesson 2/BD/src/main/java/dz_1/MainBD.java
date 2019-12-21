package dz_1;

/**
 * 1. Сделать методы для работы с БД (CREATE, UPDATE, DELETE, INSERT, SELECT)
 */

import java.sql.*;
public class MainBD {
    private static Connection connection;
    private static Statement stmt;


    public static void main(String[] args) {
        try {
            connect();
            Savepoint spl = connection.setSavepoint();
            try {
                CreateTableSQL(); //создаём таблицу persons
                InsertInTableSQL(); //вставляем данные в таблицу persons
                SelectAllSQL(); //выборка всех данных из таблицы persons
                UpdateTableSQL("Update persons SET Score = 15 WHERE FIO = 'Ivanov'");  //обновление таблицы persons
                SelectAllSQL(); //выборка всех данных из таблицы persons
                System.out.println("Выборка людей, у которых очков не менее 20");
                SelectSQL();
                DeleteInTableSQL("Delete FROM persons WHERE Score < 17"); //удаление кортежей из ьаблицы persons
                SelectAllSQL(); //выборка всех данных из таблицы persons
                DropTableSQL("persons"); //удаление таблицы persons
            } catch (Exception e) {
                connection.rollback(spl);
            }

            connection.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            disconnect();
        }

    }


    //метод подключения к БД
    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:students.db");
        stmt = connection.createStatement();
    }


    //метод отключения
    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод создания таблицы в БД
    public static void CreateTableSQL(){
        try {
            stmt.executeUpdate("CREATE TABLE persons (id INTEGER PRIMARY KEY AUTOINCREMENT,FIO TEXT,Score INTEGER)");
            System.out.println("Таблица persons создана!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод вставки в таблицу новых данных
    public static void InsertInTableSQL(){
        try {
            stmt.executeUpdate("INSERT INTO persons (FIO, Score) VALUES ('Ivanov', 10)");
            stmt.executeUpdate("INSERT INTO persons (FIO, Score) VALUES ('Petrov', 20)");
            stmt.executeUpdate("INSERT INTO persons (FIO, Score) VALUES ('Sidorov', 30)");
            stmt.executeUpdate("INSERT INTO persons (FIO, Score) VALUES ('Girov', 25)");
            System.out.println("Новые данные вставлены в таблицу persons!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод обновления данных в таблице
    public static void UpdateTableSQL(String SQLQuery){
        try {
            stmt.executeUpdate(SQLQuery);
            System.out.println("Данные обнавлены в таблице persons!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод выборки определённых данных из таблицы
    public static void SelectSQL(){
        try {
            ResultSet result = stmt.executeQuery("SELECT id, FIO, Score FROM persons WHERE Score >= 20");
            while (result.next()) {
                System.out.println(result.getInt(1) + " " + result.getString("FIO") + " " + result.getInt("Score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //метод удаления кортежей из таблицы
    public static void DeleteInTableSQL(String SQLQuery){
        try {
            stmt.executeUpdate(SQLQuery);
            System.out.println("Данные удалены в таблице persons!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод удаление таблицы
    public static void DropTableSQL(String tableName){
        try {
            stmt.executeUpdate("DROP TABLE " + tableName);
            System.out.println("Таблица удалена!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    //метод выборки всех кортежей из таблицы
    public static void SelectAllSQL(){
        try {
            ResultSet result = stmt.executeQuery("SELECT id, FIO, Score FROM persons");
            printAllColunms(result);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //метод вывода на консоль всех кортежей таблицы
    public static void printAllColunms(ResultSet rs) {
        try {
            if (rs.next() == false) {
                System.out.println("В выборке 0 записей");
            } else {
                do {
                    int id = rs.getInt("id");;
                    String FIO = rs.getString("FIO");
                    int Score = rs.getInt("Score");
                    System.out.println("[" + id + "|" + FIO + "|" + Score +  "]");
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
