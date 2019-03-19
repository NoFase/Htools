package logic;

import workWithSx.ConnectToServer;

public class ManagerOfTrigers {
    private String login, password, serverIp;

    public ManagerOfTrigers() {

        ConnectToServer server = new ConnectToServer(login, password, serverIp);
        server.run();
    }
}
