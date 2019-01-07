package SQLdialog;

import java.sql.*;

public class HolderH2 extends Holder{

    public final String DB_Driver = "org.h2.Driver";
    public Statement statement;

    public HolderH2() {
        super.conPartOne = "jdbc:h2://d:\\JavaProjects\\Htools\\src\\main\\resources\\DB\\db_httol\\mydb.db";
        super.conPartTwo = ":1433;databaseName=Bam";
        try {
            Class.forName(DB_Driver);
            Connection connection = DriverManager.getConnection(conPartOne);
            if (connection != null) {
                System.out.println("Connected!");
                statement = connection.createStatement();
//                statement.executeUpdate(req);
//                statement.executeQuery("SELECT * FROM servers");

                ResultSet rs = statement.executeQuery("SELECT * FROM servers");
                int i=0;
                while (rs.next()){
                    System.out.println(rs.getString(1) + "\t-\t" + rs.getString(2));
                    System.out.println(i++);
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Can't register Driver. Incorrect URL");
            e.printStackTrace();
        } catch (SQLException e){
            System.out.println("Ошибка SQL !");
            e.printStackTrace();
        }


    }


    String req = "CREATE TABLE servers(\n" +
            "\tid BIGINT AUTO_INCREMENT PRIMARY KEY,\n" +
            "\tabrServer VARCHAR(15) NOT NULL,\n" +
            "\tnameServer VARCHAR(255) NOT NULL,\n" +
            "\tipServer  VARCHAR(15) NOT NULL,\n" +
            ");\n";
}
