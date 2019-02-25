package pfxAnalays;

import dialogWithCommutators.commands.Rtana;
import dialogWithCommutators.customers.CnacldPfx;
import network.TCPConnection;

import java.util.Map;

import static staticVariable.StaticVariables.cnacldPfxs;

public class CommandSenderRSC extends Thread {
    private TCPConnection conn;

    public CommandSenderRSC(TCPConnection conn) {
        this.conn = conn;
    }

    public void run(){
        for (Map.Entry<String, CnacldPfx> entry: cnacldPfxs.entrySet()){
            String rsc = entry.getValue().getRsc();
            if (!rsc.equals("absents") || !rsc.equals("NULL")) {
                conn.sendString(new Rtana(rsc).creatingCommand());
            }
        }
    }
}
