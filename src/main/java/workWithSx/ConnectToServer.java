package workWithSx;

import dialogWithCommutators.commands.LGI;
import logic.MyDate;
import logic.trigers.AnalyzerString;
import network.TCPConnection;
import network.TCPConnectionListener;

import java.io.IOException;
import java.util.function.BinaryOperator;


public class ConnectToServer implements TCPConnectionListener, Runnable {

    private String login, password, serverIp;
    private final int PORT = 6000;
    private TCPConnection connection;
    private boolean loginStatus;

    private boolean sendingMode = false;
    private BinaryOperator<String> operator = (s1, s2) -> new LGI(s1, s2).creatingCommand();
    private AnalyzerString analyzerType;

    public boolean isLogin() {
        return loginStatus;
    }

    public void setAnalyzerType(AnalyzerString analyzerType) {
        this.analyzerType = analyzerType;
    }

    public boolean isSendingMode() {
        return sendingMode;
    }

    public void setSendingMode(boolean sendingMode) {
        this.sendingMode = sendingMode;
    }

    public ConnectToServer(String login, String password, String serverIp) {
        this.login = login;
        this.password = password;
        this.serverIp = serverIp;
    }

    @Override
    public void run() {
        try {
            connection = new TCPConnection(this, serverIp, PORT);
        } catch (IOException e) {
            System.out.println("======> ConnectToServer -> run() \n Any problem with connection to IP: "
                    + serverIp
                    + "\n"
                    + e.getMessage());
        }
    }

    @Override
    public void onConnectionReady(TCPConnection tcpConnection) {
        System.out.println("===> ConnectToServer -> onConnectionReady() -> " +
                new MyDate().currentDate().toString() +
                " Connect ready to Ip: " + serverIp);
        connection.sendString(operator.apply(login, password));
    }

    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        if (value == null) tcpConnection.disconnect();
        else analysis(value);
    }



    @Override
    public void onDisconnect(TCPConnection tcpConnection) {
        loginStatus = false;
        System.out.println("===> ConnectToServer -> onDisconnect() -> " +
                new MyDate().currentDate().toString() +
                " Disconnected from IP: " + serverIp);
    }

    @Override
    public void onException(TCPConnection tcpConnection, Exception e) {

    }

    private void analysis(String value) {
        sendingMode = (value.contains("Number of results =")) ? false : true;
        if (!value.contains("--------------------") ||
            !value.contains("END")&&
            analyzerType != null){
            analyzerType.checking(value);
        } else if (value.contains("logged in successfully")) {
            loginStatus = true;
            System.out.println("===> ConnectToServer -> analysis() -> login" +
                    new MyDate().currentDate().toString());
        }
    }
}
