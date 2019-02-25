package pfxAnalays;

import network.TCPConnection;
import static staticVariable.StaticVariables.tmpPfx;
import static staticVariable.StaticVariables.curPfx;

public class CnacldConnect extends DefaultConnection {
    private Thread commandSender;

    private boolean isSendingCommand = false;
    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
//        инициализируем обект для запуска комманд LST CNACLD
        if (commandSender == null) commandSender = new CommandSender(conn);
        checkingReceivingString(value);
        if (isLogin && !isSendingCommand) {
            commandSender.run();
            isSendingCommand = true;
        }
        if (isLogin && isSendingCommand) {
            new CnacldAnalyser(value);
        }

        if (commandSender.isInterrupted()) {
            conn.disconnect();
            curPfx = null;
            tmpPfx = null;
            interrupt();
        }
    }
}
