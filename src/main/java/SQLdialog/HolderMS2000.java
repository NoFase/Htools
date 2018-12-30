package SQLdialog;

import java.sql.DriverManager;
import java.sql.SQLException;

public class HolderMS2000 extends Holder {

    public HolderMS2000() {
        try {
            DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());
        } catch (SQLException e) {
            System.out.println("Can't register Driver. Incorrect URL");
            e.printStackTrace();
            return;
        }
    }
}
