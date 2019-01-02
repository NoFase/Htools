package SQLdialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Holder {
    private String DrvName;

    private Connection connection = null;
    private Statement statement = null;

    public void connecting(String ipServer, String login, String password){
        String connectionString =  "jdbc:jtds:sqlserver://" + ipServer + ":1433;databaseName=Bam";
        try {
            connection = DriverManager.getConnection(connectionString, login, password);
            if (connection != null) {
                statement = connection.createStatement();
                System.out.println("Connected!");

            }
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
