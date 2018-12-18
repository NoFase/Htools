package dialogWithCommutators.customers;

public class CallOUT implements Authority{
    private Boolean intraOffice, local, localToll, national, international;

    public Boolean getIntraOffice() {
        return intraOffice;
    }

    public Boolean getLocal() {
        return local;
    }

    public Boolean getLocalToll() {
        return localToll;
    }

    public Boolean getNational() {
        return national;
    }

    public Boolean getInternational() {
        return international;
    }

    public CallOUT(Boolean intraOffice, Boolean local, Boolean localToll, Boolean national, Boolean international) {
        this.intraOffice = intraOffice;
        this.local = local;
        this.localToll = localToll;
        this.national = national;
        this.international = international;
    }
}
