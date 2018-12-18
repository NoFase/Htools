package dialogWithCommutators.customers;

public class CallIn implements Authority {

    private Boolean intraOffice, local, localToll, national, international;

    public CallIn(Boolean intraOffice, Boolean local, Boolean localToll, Boolean national, Boolean international) {
        this.intraOffice = intraOffice;
        this.local = local;
        this.localToll = localToll;
        this.national = national;
        this.international = international;
    }

    @Override
    public Boolean getIntraOffice() {
        return null;
    }

    @Override
    public Boolean getLocal() {
        return null;
    }

    @Override
    public Boolean getLocalToll() {
        return null;
    }

    @Override
    public Boolean getNational() {
        return null;
    }

    @Override
    public Boolean getInternational() {
        return null;
    }
}
