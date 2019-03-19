package logic.trigers;

import dialogWithCommutators.essence.SG;
import logic.MyDate;

public class SgAnalyzer implements AnalyzerString {

    @Override
    public AnalyzerString checking(String value) {
        String[] sgElement = (value.contains("Embedded") || value.contains("Assemble")) ?
                value.split("\\s+"): null;
        if (sgElement != null){
            String sgName = "";
            for (int i = 2; i < sgElement.length - 4; i++) {
                sgName += sgElement[i];
            }
            System.out.println("===> SgAnalyzer -> checking()" +
                    new MyDate().currentDate().toString() +
                    " id: " + Integer.parseInt(sgElement[1].trim()) +
                    " Sg Name: " + sgName +
                    " Equipment ID: " + sgElement[sgElement.length-2].trim());

            return  (AnalyzerString) new SG(Integer.parseInt(sgElement[1].trim()), sgName, sgElement[sgElement.length-2].trim());
        }
        return null;
    }
}
