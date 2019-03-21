package logic;

import logic.trigers.MyTriger;
import logic.trigers.TrigSG;
import workWithSx.ConnectToServer;

public class ManagerOfTrigers {
    private String login, password, serverIp;
    private MyTriger triger;
    private ConnectToServer server;

    public ManagerOfTrigers() {
        server = new ConnectToServer(login, password, serverIp);
        server.run();
    }

    public void trigerStarting(MyTriger triger){
        this.triger = triger;
        if (server.isLogin()){
             triger.run();
        } else {
            System.out.println("===> ManagerOfTrigers -> trigerStarting() " +
                    new MyDate().currentDate().toString() +
                    " problem with Authorised on server!");
        }
    }
}
