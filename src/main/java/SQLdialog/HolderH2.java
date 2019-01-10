package SQLdialog;

import org.h2.jdbc.JdbcSQLException;

import java.sql.*;

public class HolderH2 extends Holder{

    public final String DB_Driver = "org.h2.Driver";
    public Statement statement;
    private Connection connection;


    String createTable = "create table IF NOT EXISTS tbl_Servers (" +
            "ID   INT         NOT NULL," +
            "ABR  VARCHAR(50) NOT NULL," +
            "NAME VARCHAR(50)," +
            "IP   VARCHAR(50) NOT NULL," +
            "PRIMARY KEY ( id, abr, ip ));";
    String listofAllTables = "SELECT * FROM INFORMATION_SCHEMA.TABLES";
    String req = "CREATE TABLE servers(\n" +
            "\tid BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
            "\tabrServer VARCHAR(15) NOT NULL,\n" +
            "\tnameServer VARCHAR(255) NOT NULL,\n" +
            "\tipServer  VARCHAR(15) NOT NULL,\n" +
            ");\n";


    public HolderH2() {
//        super.conPartOne = "jdbc:h2:~/src/main/resources/DB/db_httol/mydb";
//        try {
//            Class.forName(DB_Driver);
//            Connection connection = DriverManager.getConnection(conPartOne);
//            if (connection != null) {
//                System.out.println("Connected!");
//                statement = connection.createStatement();
////                statement.executeUpdate(req);
////                statement.executeQuery("SELECT * FROM servers");
//
//                statement.execute("DROP TABLE tbl_Servers");
//                statement.execute(createTable);
//                ResultSet rss = statement.executeQuery(new MessageRequest().selectingMaxIdInTbl("tbl_Servers"));
//
//                int maxId = 1;
//                while (rss.next()){
//                    maxId = rss.getInt(1);
//                    System.out.println(maxId);
//                }
//                rss.close();
//                statement.execute("insert into tbl_Servers (ID, ABR, NAME, IP) values ('" + ++maxId + "', 'CHB', 'Cheboksary', '10.140.27.9');");
////                ResultSet rs = statement.executeQuery("SELECT * FROM tbl_Servers;");
//                ResultSet rs = statement.executeQuery(new MessageRequest().listAllOfTbl("tbl_Servers"));
//                while (rs.next()){
//                    System.out.println(rs.getString(1) + "\t-\t" + rs.getString(2) + "\t-\t" + rs.getString(3)+ "\t-\t" + rs.getString(4) );
//                }
//                rs.close();
//
//
//
//
//                statement.close();
//                connection.close();
//            }
//            else System.out.println("Connection failed!");
//        } catch (JdbcSQLException e){
////            new MyAlert("Такая запись уже есть в базе данных!");
//            System.out.println("Такая запись уже есть в базе данных!");
//            e.printStackTrace();
//            return;
//        } catch (ClassNotFoundException e) {
//            System.out.println("Can't register Driver. Incorrect URL");
//            e.printStackTrace();
//        } catch (SQLException e){
//            System.out.println("Ошибка SQL !");
//            e.printStackTrace();
//        }
    }

    public ResultSet requesting(String request){
        try {
            return statement.executeQuery(request);
        } catch (SQLException e) {
            System.out.println("Can't send request: " + request + " to sql...");
            e.printStackTrace();
            return null;
        }
    }
    public void executing(String request){
        try {
            statement.execute(request);
        } catch (SQLException e) {
            System.out.println("Can't send request: " + request + " to sql...");
            e.printStackTrace();
        }
    }
    public void connecting(){
        super.conPartOne = "jdbc:h2:~/src/main/resources/DB/db_httol/mydb";
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(conPartOne);
            if (connection != null) {
                System.out.println("Connected!");
                statement = connection.createStatement();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Can't register Driver. Incorrect URL");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Ошибка SQL!");
            e.printStackTrace();
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
}
