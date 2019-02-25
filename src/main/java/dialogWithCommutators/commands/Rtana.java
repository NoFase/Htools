package dialogWithCommutators.commands;

public class Rtana implements Command{
    private StringBuffer command;

    public Rtana(String rsc) {
        command = new StringBuffer().append("ADD RTANA: RSC=");
        command.append(rsc);
        command.append(", TM=TMM");
    }

    @Override
    public String creatingCommand() {
        return command.toString();
    }
}
