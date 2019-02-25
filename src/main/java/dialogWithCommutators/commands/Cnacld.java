package dialogWithCommutators.commands;

public class Cnacld implements Command {

    private StringBuffer command;

    public Cnacld(String pfx) {
        command = new StringBuffer("LST CNACLD: LP=").append("0, PFX=K'");
        command.append(pfx);
        command.append(", QSTY=ANALOG;");
    }

    @Override
    public String creatingCommand() {
        return command.toString();
    }
}
