package dialogWithCommutators.commands;

public class Rtana implements Command{
    private StringBuffer command;

    public Rtana(String rsc) {
        command = new StringBuffer().append("LST RTANA: RSC=");
        command.append(rsc);
        command.append(";");
        System.out.println("===> Rtana ---> method constructor --> create command:" + command);
    }

    @Override
    public String creatingCommand() {
        return command.toString();
    }
}
