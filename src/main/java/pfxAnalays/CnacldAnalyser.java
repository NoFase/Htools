package pfxAnalays;

import dialogWithCommutators.customers.CnacldPfx;

import static staticVariable.StaticVariables.tmpPfx;
import static staticVariable.StaticVariables.curPfx;
import static staticVariable.StaticVariables.cnacldPfxs;

public class CnacldAnalyser {
    public CnacldAnalyser(String value) {
        CnacldPfx cnacld;
        if (value.contains("Call prefix")) tmpPfx = value.split("=")[1].trim();
        if (value.contains("Route selection code")) {
            cnacld = new CnacldPfx();
            cnacld.setPfx(tmpPfx);
            cnacld.setRsc(value.split("=")[1].trim());
            cnacldPfxs.put(tmpPfx, cnacld);
            int rnd = (int)(Math.random()*100);
            System.out.println("===> CnacldAnalyser ---> method constructor --> add to cnacldPfxs with RSC " + rnd);
        }
        if (value.contains("No matching result is found")) {
            cnacld = new CnacldPfx();
            cnacld.setPfx(curPfx);
            cnacld.setRsc("absents");
            cnacldPfxs.put(curPfx, cnacld);
            int rnd = (int)(Math.random()*100);
            System.out.println("===> CnacldAnalyser ---> method constructor --> add to cnacldPfxs without RSC " + rnd);
        }
    }
}
