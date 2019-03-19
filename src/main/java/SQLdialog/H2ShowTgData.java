package SQLdialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

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

            Date date =new Date();
            rs = holder.requesting(new MessageRequest().listAllOfTbl("tblSRTTG19"));
            SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            while (rs.next()) {

                date.setTime(rs.getLong(2));
                int hours = date.getHours();
                int minuts = date.getMinutes();
//                String time = String.format("%02d:%02d:%02d", rs.getLong(2) % 3600000, rs.getLong(2) / 3600000, rs.getLong(2) / 60000);
                System.out.println("----------------------------");
                System.out.println(rs.getString(1) + " "
                        + form.format(date) + " "
                        + rs.getString(3) + " "
                        + rs.getString(4) + " "
                        + rs.getString(5) + " "
                        + rs.getString(6) + " "
                        + rs.getString(7) + " "
                        + rs.getString(8) + " ");
            }


            rs.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public String formatLongToStrTime(long time) {
        long mod = time % 3600000;
        long hours = 0;
        long mint = 0;

        if (mod != 0)
            mint = mod / 60000;
        else
            mint = 0;

        hours = time / 3600000;

        String hoursStr = String.valueOf(hours);
        String mintsStr = String.valueOf(mint);
        if (hoursStr.length() == 1)
            hoursStr = "0" + hoursStr;

        if (mintsStr.length() == 1)
            mintsStr = "0" + mintsStr;

        return hoursStr + ":" + mintsStr;
    }
}
