package SQLdialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Holder {
    protected String conPartOne;
    protected String conPartTwo;

    private Connection connection = null;
    private Statement statement = null;

    public void connecting(String ipServer, String login, String password){
        String connectionString =  conPartOne + ipServer + conPartTwo;
        System.out.println(connectionString);
        try {
            connection = DriverManager.getConnection(connectionString, login, password);
            if (connection != null) {
                statement = connection.createStatement();
                System.out.println("Connected!");
            }
            else return;
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return;
        }
    }

    public void closeConnecting(){
        try {
            statement.close();
            connection.close();
        } catch (SQLException e){
            System.out.println("Can't close connection");
            e.printStackTrace();
            return;
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }
}
