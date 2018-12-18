package dialogWithCommutators.commands;

public class LstTg implements Command {

    private StringBuffer command;

    public LstTg() {
        command = new StringBuffer().append("LST TG:;");
    }

    @Override
    public String creatingCommand() {
        return command.toString();
    }
}
