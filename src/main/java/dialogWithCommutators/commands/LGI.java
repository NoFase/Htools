package dialogWithCommutators.commands;


import static staticVariable.StaticVariables.*;

public class LGI implements Command{

    @Override
    public String creatingCommand() {
        StringBuffer command = new StringBuffer().append("LGI:op=\"")
                .append(loginName).append("\", PWD =\"").append(passwordForLogin)
                .append("\", SER=\"").append(ipLocalWS).append("---O&M System\";")
                .append("\r\n");
        return command.toString();
    }
}
