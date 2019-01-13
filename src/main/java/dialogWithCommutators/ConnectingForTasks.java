package dialogWithCommutators;

import SQLdialog.HolderH2;
import SQLdialog.MessageRequest;
import dialogWithCommutators.commands.LGI;
import dialogWithCommutators.processor.FilterDspOFTK;
import logic.MyDate;
import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
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
                processor = false;
                sendingToDB();
            } else {
                String[] data = new FilterDspOFTK(value).getData();
                if (data[0] != null && !data[0].equals("")) dataOftk.put(data[0], data[1]);
            }
        }
    }

    private void sendingToDB() {
        holderH2 = new HolderH2();
        String request = new MessageRequest().creatingTbl("tblSRTTG19");
        holderH2.executing(request);
        System.out.println("===> ConnectingForTasks ---> method sendingToDB --> sent request to H2DB: " + request);
        request = new MessageRequest().addingToTblOFTK("tblSRTTG19", 1, new MyDate().currentDate(), "19",
                dataOftk.get("Free"), dataOftk.get("Busy"), dataOftk.get("Block"), dataOftk.get("Fault"), dataOftk.get("Installation"));
        holderH2.executing(request);
        System.out.println("===> ConnectingForTasks ---> method sendingToDB --> sent request to H2DB: " + request);
        holderH2.closeConnecting();
        dataOftk.clear();
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
