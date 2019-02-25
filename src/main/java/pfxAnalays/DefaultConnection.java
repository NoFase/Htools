package pfxAnalays;

import dialogWithCommutators.commands.LGI;
import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;

import static staticVariable.StaticVariables.*;

public class DefaultConnection extends Thread implements TCPConnectionListener{

    TCPConnection conn;
    public boolean isLogin = false;


    public DefaultConnection() {
            loginName = "Smednyh";
            passwordForLogin = "SoftX3000";
    }

    public void run(){
        try {
            conn = new TCPConnection(this, "10.136.33.130", 6000);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        System.out.println("===> ConnectingForTasks ---> method onConnectionReady --> Connection ready to IP: " + ipServer);
        conn.sendString(new LGI().creatingCommand());
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {

    }

    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        System.out.println("===> pfxAnalays --->NumberCrt ---> method onDisconnect --> Disconnected from IP: 10.136.33.130" );
        isLogin = false;
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }

    public void checkingReceivingString(String value){
        if (value == null) conn.disconnect();
        if (value.contains("logged in successfully")) {
            isLogin = true;
        }
            System.out.println(value);
    }
}
