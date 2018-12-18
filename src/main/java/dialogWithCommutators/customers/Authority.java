package dialogWithCommutators.customers;

public interface Authority {
    public Boolean getIntraOffice();

    public Boolean getLocal();

    public Boolean getLocalToll();

    public Boolean getNational();

    public Boolean getInternational();
}
