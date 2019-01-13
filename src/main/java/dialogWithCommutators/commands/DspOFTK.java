package dialogWithCommutators.commands;

import GUI.otherWindows.MyAlert;

public class DspOFTK implements Command {
    private String numberTg;

    public void setNumberTg(String numberTg) {
        this.numberTg = numberTg;
    }

    @Override
    public String creatingCommand() {
        if (numberTg == null || numberTg.equals("")) {
            new MyAlert("Номер транка не задан");
            return null;
        } else {
        StringBuffer command = new StringBuffer().append("DSP OFTK: LT=TG, TG=").append(numberTg).append(", DT=AT;");
        return command.toString();
        }
    }
}
