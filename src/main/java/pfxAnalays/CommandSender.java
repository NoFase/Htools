package pfxAnalays;

import dialogWithCommutators.commands.Cnacld;
import network.TCPConnection;

import java.util.ArrayList;
import static staticVariable.StaticVariables.curPfx;

public class CommandSender extends Thread {
    private TCPConnection conn;
    private boolean finishPfx = false;
    private String curPFX;

    public String getCurPFX() {
        return curPFX;
    }

    public boolean isFinishPfx() {
        return finishPfx;
    }

    public CommandSender(TCPConnection conn) {
        this.conn = conn;
    }

    @Override
    public void run() {
        ArrayList<String> pfxs = new Siren().getNumberPfx();
        for (String pfx : pfxs) {
        conn.sendString(new Cnacld(pfx).creatingCommand());
        curPfx = pfx;
        }

//        String pfx = "8904262";
//        curPfx = pfx;
//        conn.sendString(new Cnacld(pfx).creatingCommand());

        try {
            System.out.println("===> CommandSender ---> method Run --> before sleep");
            Thread.sleep(100);
//            System.out.println("===> CommandSender ---> method Run --> after sleep");

//            interrupt();
            System.out.println("===> CommandSender ---> method Run --> interrupted");
            finishPfx = true;
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
