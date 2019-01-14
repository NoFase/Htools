package SQLdialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class H2ShowTgData {
    public H2ShowTgData() {
        HolderH2 holder = new HolderH2();
        holder.connecting();
        Statement statement;
        ResultSet rs;
        try {
//
//            rs = holder.requesting(new MessageRequest().listingOfAllTables());
//            while (rs.next()) System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
//            rs.close();

            rs = holder.requesting(new MessageRequest().listAllOfTbl("tblSRTTG19"));
            while (rs.next()) System.out.println(rs.getString(1) + " "
                    + rs.getString(2) + " "
                    + rs.getString(3) + " "
                    + rs.getString(4) + " "
                    + rs.getString(5) + " "
                    + rs.getString(6) + " "
                    + rs.getString(7) + " "
                    + rs.getString(8) + " ");
            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
