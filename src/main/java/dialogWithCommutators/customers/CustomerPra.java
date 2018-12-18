package dialogWithCommutators.customers;

public class CustomerPra implements Customer{
    private String number, lp, tg, rsc, csc;
    private Boolean status;
    private String category;
    private Authority callIn, callOut;

    public CustomerPra(String number, String lp, String tg, String rsc, String csc) {
        this.number = number;
        this.lp = lp;
        this.tg = tg;
        this.rsc = rsc;
        this.csc = csc;
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

    public void setStatus(Boolean status) {
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

    public Boolean getStatus() {
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
