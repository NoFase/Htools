package dialogWithCommutators;



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import dialogWithCommutators.commands.LGI;
import network.*;

import static staticVariable.StaticVariables.*;


public class ConnectingWithServer implements TCPConnectionListener, Serializable {

//    public static TCPConnection connection;
    private ObjectOutputStream out;


    public ConnectingWithServer() {
        try {
            connection = new TCPConnection(this, ipServer, 6000);
            out = new ObjectOutputStream(new FileOutputStream("outObject.dat"));

        } catch (IOException e) {}
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        System.out.println("Connection ready to IP: " + ipServer);
        connection.sendString(new LGI().creatingCommand());
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
//        System.out.println(value);
        if (value == null) connection.disconnect();
        System.out.println(value);
        try {
            out.writeObject(value);
//            out.close();
//            new WriteToFile("d:/outputLog.txt").writing(value + "\n\r");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("Disconnect from ip: " + ipServer);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }
}
