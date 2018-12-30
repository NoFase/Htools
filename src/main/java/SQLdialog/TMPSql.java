package SQLdialog;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TMPSql {

    public TMPSql() {
        //        new HolderBD().run();

        Holder holder = new HolderMS2000();
        holder.connecting("jdbc:jtds:sqlserver://10.140.27.9:1433;databaseName=Bam", "searcher", "SoftX3000");
        String req = "SELECT ORDINAL_POSITION,COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_PRAUserData' ORDER BY ORDINAL_POSITION ASC";

        req = new MessageRequest().allPraUser();
        ResultSet rs = new Request().sendingRequest(holder.getStatement(), req);
        try {
            while (rs.next()){
//                for (int i = 1; i < 80; i++) {
//                    System.out.print(rs.getString(i) + "\t");
//                }
//                System.out.println("");
                System.out.println(rs.getString("sDN") + "\t-\t" + rs.getString(2));
            }
            holder.closeConnecting();
        } catch (SQLException e) {
            System.out.println("Что то пошло не так...");
            e.printStackTrace();
        }

    }
}
