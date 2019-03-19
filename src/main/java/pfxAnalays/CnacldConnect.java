package pfxAnalays;

import network.TCPConnection;
import static staticVariable.StaticVariables.tmpPfx;
import static staticVariable.StaticVariables.curPfx;

public class CnacldConnect extends DefaultConnection {
    private CommandSender commandSender;
    private boolean live = true;
    Thread thread;

    {
        System.out.println("Start class CnacldConnect");
    }

    public boolean isLive() {
        return live;
    }

    private boolean isSendingCommand = false;
    @Override
    public void onReceiveString(TCPConnection tcpConnection, String value) {
//        инициализируем обект для запуска комманд LST CNACLD
        if (commandSender == null) {
            thread = new Thread(commandSender = new CommandSender(conn));
        }
        checkingReceivingString(value);
        if (isLogin && !isSendingCommand) {
//            commandSender.run();
            thread.run();
            isSendingCommand = true;
        }
        if (isLogin && isSendingCommand) {
            new CnacldAnalyser(value);
        }

        if (commandSender.isInterrupted()) {
            conn.disconnect();
            curPfx = null;
            tmpPfx = null;
            System.out.println("===> CnacldConnect ---> method onReceiveString --> check interrupt");
        }
        if (commandSender.isFinishPfx()) {
            live = false;
        }
    }
}
