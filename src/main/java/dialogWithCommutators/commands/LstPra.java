package dialogWithCommutators.commands;

import staticVariable.StaticVariables;

public class LstPra implements Command {

    private StringBuffer command;

    public LstPra() {
        command = new StringBuffer().append("LST PRA:;");
        StaticVariables.isCommands.put("LSTPRA", true);
    }

    public LstPra(String number, String lp){
        command = new StringBuffer().append("LST PRA: D=K'" + number + ", LP=" + lp +";");
        StaticVariables.isCommands.put("LSTPRAx", true);
    }

    @Override
    public String creatingCommand() {
//        System.out.println(command);
        return command.toString();
    }
}
