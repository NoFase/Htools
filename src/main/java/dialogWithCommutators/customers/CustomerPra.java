package dialogWithCommutators.customers;

public class CustomerPra implements Customer{
    private String number, lp, tg, rsc, csc, status, category, charg;;
    public final String TYPE = "PRA";
    private Authority callIn, callOut;

    public CustomerPra(String number, String lp, String tg, String rsc, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        this.number = number;
        this.lp = lp;
        this.tg = tg;
        this.rsc = rsc;
        this.csc = csc;
        this.status = status;
        this.category = category;
        this.charg = charg;
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
}
