package pfxAnalays;

import network.TCPConnection;

public class RSCConnect extends DefaultConnection {

    {
        System.out.println("Start class RSCConnect");
    }

    private CommandSenderRSC commandSender = null;
    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
        checkingReceivingString(value);
        if (commandSender == null) {
            System.out.println("====> RSCConnect ---> method onReceiveString ---> initialyse commandSender");
            commandSender = new CommandSenderRSC(conn);
            commandSender.run();
        }

        System.out.println(value);
    }
}
