package logic.trigers;

import dialogWithCommutators.essence.Essence;
import workWithSx.ConnectToServer;

import java.util.List;

public interface MyTriger {

    void trigering (ConnectToServer connection);
    void filtering (String value);
    List<Essence> getEssences();
    String getNAMEDB();
}
