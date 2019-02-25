package pfxAnalays;

import network.TCPConnection;

import java.util.ArrayList;

public class WorkerWithString {
    private TCPConnection conn;
    private String valueLine;
    private ArrayList pfx;

    public WorkerWithString(TCPConnection conn, String valueLine) {
        this.conn = conn;
        this.valueLine = valueLine;

        findingLogining();
        findingData();
    }

    private void findingLogining() {
        if (valueLine.contains("logged in successfully")) {findingData();}
    }

    private void findingData() {
        if (valueLine.contains("logged in successfully")) {
            pfx = new Siren().getNumberPfx();

        }
    }


}
