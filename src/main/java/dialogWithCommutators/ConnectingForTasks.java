package dialogWithCommutators;

import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import dialogWithCommutators.commands.LGI;
import dialogWithCommutators.processor.FilterDspOFTK;
import logic.MyDate;
import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import static staticVariable.StaticVariables.connection;
import static staticVariable.StaticVariables.ipServer;

public class ConnectingForTasks implements TCPConnectionListener{

    private Boolean connected = false;
    private Boolean processor = false;
    private HolderH2 holderH2;
    private HashMap<String, String> dataOftk = new HashMap<>();


    public ConnectingForTasks() {
        try {
            connection = new TCPConnection(this, ipServer, 6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        connected = true;
        System.out.println("===> ConnectingForTasks ---> method onConnectionReady --> Connection ready to IP: " + ipServer);
        connection.sendString(new LGI().creatingCommand());
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        if (value.contains("logged in successfully")) {
            connected = true;
            System.out.println("===> ConnectingForTasks ---> method onReceiveString --> connected: " + connected);
        }
        System.out.println(value);
        if (value == null) connection.disconnect();
        else if (value.contains("Sum of trunk")) processor = true;
        if (processor) {
            if (value.contains("END")) {
                System.out.println("===> ConnectingForTasks ---> method onReceiveString --> value contained END");
                processor = false;
                sendingToDB();
            } else {
                String[] data = new FilterDspOFTK(value).getData();
                if (data[0] != null && !data[0].equals("")) dataOftk.put(data[0], data[1]);
            }
        }
    }

    private void sendingToDB() {
        SimpleDateFormat form = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        holderH2 = new HolderH2();
        String request = new MessageRequest().creatingTbl("tblSRTTG19");
        holderH2.connecting();
//        holderH2.executing(new MessageRequest().deletingTbl("tblSRTTG19"));
//        holderH2.executing("select DATEDIFF('second',timestamp '1970-01-01 00:00:00' ,  CURRENT_TIMESTAMP())");

//        holderH2.executing(new MessageRequest().deletingTbl("tblSRTTG19"));
        holderH2.executing(request);
        System.out.println("===> ConnectingForTasks ---> method sendingToDB --> sent request to H2DB: " + request);
        int maxId = 1;
        try{
            ResultSet rs =  holderH2.requesting(new MessageRequest().selectingMaxIdInTbl("tblSRTTG19"));
            while (rs.next()) {
                maxId = rs.getInt(1);
            }
            rs.close();
            System.out.println("===> ConnectingForTasks ---> method sendingToDB --> request max Id in tblSRTTG19, maxId = " + maxId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request = new MessageRequest().addingToTblOFTK("tblSRTTG19", ++maxId, new Date(new MyDate().currentDate().getTime()), "19",
                dataOftk.get("Free"), dataOftk.get("Busy"), dataOftk.get("Block"), dataOftk.get("Fault"), dataOftk.get("Installation"));
        holderH2.executing(request);
        System.out.println("===> ConnectingForTasks ---> method sendingToDB --> sent request to H2DB: " + request);
        holderH2.closeConnecting();
        dataOftk.clear();
        holderH2.closeConnecting();
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        connected = false;
        System.out.println("===> ConnectingForTasks ---> method onDisconnect --> Disconnected from IP: " + ipServer);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }

    public Boolean getConnected() {
        return connected;
    }
}
