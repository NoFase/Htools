package SQLdialog;

import java.sql.DriverManager;
import java.sql.SQLException;

public class HolderMS2000 extends Holder {


    public HolderMS2000() {
        super.conPartOne = "jdbc:jtds:sqlserver://";
        super.conPartTwo = ":1433;databaseName=Bam";
        try {
            DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println("Can't register Driver. Incorrect URL");
            e.printStackTrace();
            return;
        }
    }
}
