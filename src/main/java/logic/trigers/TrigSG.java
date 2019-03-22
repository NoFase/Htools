package logic.trigers;

import dialogWithCommutators.commands.LstSg;
import dialogWithCommutators.essence.Essence;
import dialogWithCommutators.essence.SG;
import logic.MyDate;
import workWithSx.ConnectToServer;

import java.util.ArrayList;
import java.util.List;

public class TrigSG implements MyTriger{

    private ConnectToServer server;
    private List<Essence> essences;
    private final String NAMEDB = "sg";

    @Override
    public String getNAMEDB() {
        return NAMEDB;
    }

    @Override
    public List<Essence> getEssences() {
        return essences;
    }

    @Override
    public void trigering (ConnectToServer server) {
        this.server = server;
        server.setSendingMode(true); //?????
        sendingCommand();
        while (server.isSendingMode()){
            server.isSendingMode();
        }
    }

    private void sendingCommand(){
        server.getConnection().sendString(new LstSg().creatingCommand());
    }

    @Override
    public void filtering(String value){
        essences = new ArrayList<>();
        String[] sgElement = (value.contains("Embedded") || value.contains("Assemble")) ?
                value.split("\\s+"): null;
        if (sgElement != null) {
            String sgName = "";
            for (int i = 2; i < sgElement.length - 4; i++) {
                sgName += sgElement[i];
            }
            System.out.println("===> SgAnalyzer -> checking()" +
                    new MyDate().currentDate().toString() +
                    " id: " + Integer.parseInt(sgElement[1].trim()) +
                    " Sg Name: " + sgName +
                    " Equipment ID: " + sgElement[sgElement.length - 2].trim());

//            надо переписать чтобы добавлять сразу в бд
            essences.add(new SG(Integer.parseInt(sgElement[1].trim()), sgName, sgElement[sgElement.length - 2].trim()));
        }
    }
}
