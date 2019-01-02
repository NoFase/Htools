package logs;

import logic.AnalizerForSQL;

import java.sql.SQLException;

public class ShowProblemClassAndMethod {
    public ShowProblemClassAndMethod(SQLException e, Class<? extends AnalizerForSQL> aClass) {
        System.out.println("Problem in class: " + aClass.getName());
        System.out.println("Used problem method: " + e.getStackTrace()[0].getMethodName());
        e.printStackTrace();
    }
}
