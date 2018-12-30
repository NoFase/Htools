package SQLdialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Request {

    public ResultSet sendingRequest (Statement statement, String requestString) {
        try {
            return statement.executeQuery(requestString);
        } catch (SQLException e){
            System.out.println("Can't send request to BD. Incorrect URL.");
            e.printStackTrace();
            return null;
        }
    }
}
