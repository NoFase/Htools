package SQLdialog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HolderBD {
    private final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String connectionString = "jdbc:sqlserver://10.140.27.9:3306/Tables";
    private final String login = "searcher";
    private final String password = "SoftX3000";

    public void run(){
        try {
            Class.forName(driverName);
        } catch (ClassNotFoundException e){
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return;
        }
        Connection connection = null;
        try{
            connection = DriverManager.getConnection(connectionString, login, password);
            System.out.println("Connected!");
        } catch (SQLException e){
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return;
        }
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
            return;
        }
    }
}
