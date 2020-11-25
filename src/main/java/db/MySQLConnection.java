package db;
import entity.ToDoTaskLoaiza;

import java.sql.*;

public class MySQLConnection {

    private Connection connection;

    public MySQLConnection() {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

        } catch (ClassNotFoundException a) {
            a.printStackTrace();
        }
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://200.3.193.22:3306/P09728_1_11", "P09728_1_11", "ZCSaQGZU");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void disconnect() {
        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void createDatabase() {
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS toDoTasksLoaiza(id INT PRIMARY KEY AUTO_INCREMENT, date DATE, description varchar (200))");
            statement.execute("CREATE TABLE IF NOT EXISTS doingTasksLoaiza(id INT PRIMARY KEY AUTO_INCREMENT,  date DATE, description varchar (200))");
            statement.execute("CREATE TABLE IF NOT EXISTS doneTasksLoaiza(id INT PRIMARY KEY AUTO_INCREMENT, date DATE, description varchar (200))");
           } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }

    }

    //Ordenes
    public boolean executeSQL(String sql) {
        boolean success = false;
        try {
            connect();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            success = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            disconnect();
        }
        return success;
    }

    //Solicitudes
    public ResultSet query(String sql) {
        ResultSet output = null;
        try {
            connect();
            Statement statement = connection.createStatement();
            output = statement.executeQuery(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return output;
    }
}
