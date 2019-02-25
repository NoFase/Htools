package pfxAnalays;

import network.TCPConnection;

public class RSCConnect extends DefaultConnection {

    private Thread commandSender;
    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        checkingReceivingString(value);
        if (commandSender == null) commandSender = new CommandSenderRSC(conn);
    }
}
