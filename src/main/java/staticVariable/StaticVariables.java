package staticVariable;


import javafx.scene.control.TextArea;
import dialogWithCommutators.customers.Customer;
import network.TCPConnection;

import java.util.HashMap;

public class StaticVariables {
    public static HashMap<String, String[]> listOfServers = new HashMap<String, String[]>();
    public static String loginName;
    public static String ipServer;
    public static String ipLocalWS = "10.188.4.154";
    public static String passwordForLogin;
    public static TCPConnection connection;
    public static HashMap<String, Boolean> isCommands = new HashMap<>();
    public static HashMap<String, Customer> customers = new HashMap<>();
    public static TextArea fldPlace = new TextArea();
    public static String actualNumber = null;
    public static int increment = 0;

    {
        isCommands.put("LSTPRA", false);
        isCommands.put("LSTPRAx", false);
        isCommands.put("LSTMSBR", false);
        isCommands.put("LSTVSBR", false);
    }

}
