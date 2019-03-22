package logic;

import dialogWithCommutators.essence.Essence;
import logic.trigers.MyTriger;
import workWithSx.ConnectToServer;

import java.util.List;

public class ManagerOfTrigers {
    private String login, password, serverIp;
    private MyTriger triger;
    private ConnectToServer server;
    private List<Essence> essences;

    public ConnectToServer getServer() {
        return server;
    }

    public ManagerOfTrigers() {
//        нужно чтобы задавались где то логин парол и адрес сервера
        server = new ConnectToServer(login, password, serverIp);
        server.run();
    }

    public void trigerStarting(MyTriger triger){
        this.triger = triger;
        server.setTriger(triger);
        if (server.isLogin()){
             triger.trigering(server);
        } else {
            System.out.println("===> ManagerOfTrigers -> trigerStarting() " +
                    new MyDate().currentDate().toString() +
                    " problem with Authorised on server!");
        }

//        ждем пока выполнится команда
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (server.isSendingMode()){
            server.isSendingMode();
        }

        essences = (triger.getEssences() == null) ? triger.getEssences() : null;

        if (triger.getEssences() != null) {
            for (Essence e : triger.getEssences()){
//                нужно здесь еще добавить path до базы данной
                e.returnStringInDB(triger.getNAMEDB());
            }
        }
    }
}
