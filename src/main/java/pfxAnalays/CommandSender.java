package pfxAnalays;

import dialogWithCommutators.commands.Cnacld;
import network.TCPConnection;

import java.util.ArrayList;
import static staticVariable.StaticVariables.curPfx;

public class CommandSender extends Thread {
    private TCPConnection conn;
    private boolean isFinishPfx = false;




    public CommandSender(TCPConnection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        try {
//        ArrayList<String> pfxs = new Siren().getNumberPfx();
//        for (String pfx : pfxs) {
//        conn.sendString(new Cnacld(pfx).creatingCommand());.
//        curPfx = pfx;
            wait(1000);
//    }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String pfx = "8904262";
        curPfx = pfx;
        conn.sendString(new Cnacld(pfx).creatingCommand());

        try {
            wait(3000);
            interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
