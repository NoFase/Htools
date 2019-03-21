package dialogWithCommutators.commands;

import static staticVariable.StaticVariables.*;

    public class LGI implements Command{

    private final String LOCALWS = "10.188.4.154";


        private String login;
    private String password;

        public LGI(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public LGI() {
            login = loginName;
            password = passwordForLogin;
        }

        @Override
    public String creatingCommand() {
        if (login.isEmpty() | login.contains("none") | login == null | login == "")
            System.out.println("not initialise Name For Login");
        if (password.isEmpty() | password.contains("none") | password == null | password == "")
            System.out.println("no password");
        StringBuffer command = new StringBuffer().append("LGI:op=\"")
                .append(login).append("\", PWD =\"").append(password)
                .append("\", SER=\"").append(LOCALWS).append("---O&M System\";")
                .append("\r\n");
        return command.toString();
    }
}
