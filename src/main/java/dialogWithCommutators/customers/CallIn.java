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
        return intraOffice;
    }

    @Override
    public Boolean getLocal() {
        return local;
    }

    @Override
    public Boolean getLocalToll() {
        return localToll;
    }

    @Override
    public Boolean getNational() {
        return national;
    }

    @Override
    public Boolean getInternational() {
        return international;
    }
}
