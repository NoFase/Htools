package dialogWithCommutators.customers;

public class CustomerPra implements Customer{
    private String number, lp, tg, rsc, csc, status, category, charg;;
    public final String TYPE = "PRA";
    private Authority callIn, callOut;
    private Boolean intraOfficeI, localI, localTollI, nationalI, internationalI;
    private Boolean intraOfficeO, localO, localTollO, nationalO, internationalO;

    public CustomerPra(String number, String lp, String tg, String rsc, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        this.number = number;
        this.lp = lp;
        this.tg = tg;
        this.rsc = rsc;
        this.csc = csc;
        this.status = status;
        this.category = category;
        this.charg = charg;
        intraOfficeI = callIn.getIntraOffice();
        internationalI = callIn.getInternational();
        localI = callIn.getLocal();
        localTollI = callIn.getLocalToll();
        nationalI = callIn.getNational();
        intraOfficeO = callOut.getIntraOffice();
        localO = callOut.getLocal();
        localTollO = callOut.getLocalToll();
        nationalO = callOut.getNational();
        internationalO = callOut.getInternational();
    }

    public CustomerPra(String number, String lp, String tg, String rsc, String csc) {
        this.number = number;
        this.lp = lp;
        this.tg = tg;
        this.rsc = rsc;
        this.csc = csc;
    }

    public String getTYPE() {
        return TYPE;
    }

    public String getNumber() {
        return number;
    }

    public String getLp() {
        return lp;
    }

    public String getTg() {
        return tg;
    }

    public String getRsc() {
        return rsc;
    }

    public String getCsc() {
        return csc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setCallIn(Authority callIn) {
        this.callIn = callIn;
    }

    public void setCallOut(Authority callOut) {
        this.callOut = callOut;
    }

    public String getStatus() {
        return status;
    }

    public String getCategory() {
        return category;
    }

    public Authority getCallIn() {
        return callIn;
    }

    public Authority getCallOut() {
        return callOut;
    }

    public Boolean getIntraOfficeI() {
        return intraOfficeI;
    }

    public Boolean getLocalI() {
        return localI;
    }

    public Boolean getLocalTollI() {
        return localTollI;
    }

    public Boolean getNationalI() {
        return nationalI;
    }

    public Boolean getInternationalI() {
        return internationalI;
    }

    public Boolean getIntraOfficeO() {
        return intraOfficeO;
    }

    public Boolean getLocalO() {
        return localO;
    }

    public Boolean getLocalTollO() {
        return localTollO;
    }

    public Boolean getNationalO() {
        return nationalO;
    }

    public Boolean getInternationalO() {
        return internationalO;
    }
}
