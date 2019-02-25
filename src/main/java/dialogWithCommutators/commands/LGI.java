package dialogWithCommutators.commands;


import static staticVariable.StaticVariables.*;

public class LGI implements Command{

    @Override
    public String creatingCommand() {
        if (loginName.isEmpty() | loginName.contains("none") | loginName == null | loginName == "")
            System.out.println("not initialise Name For Login");
        if (passwordForLogin.isEmpty() | passwordForLogin.contains("none") | passwordForLogin == null | passwordForLogin == "")
            System.out.println("no password");
        StringBuffer command = new StringBuffer().append("LGI:op=\"")
                .append(loginName).append("\", PWD =\"").append(passwordForLogin)
                .append("\", SER=\"").append(ipLocalWS).append("---O&M System\";")
                .append("\r\n");
        return command.toString();
    }
}
