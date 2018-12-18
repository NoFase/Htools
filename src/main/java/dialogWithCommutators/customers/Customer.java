package dialogWithCommutators.customers;

public interface Customer {
    public String getNumber();
    public String getLp() ;
    public String getTg() ;
    public String getRsc() ;
    public String getCsc() ;
    public void setStatus(Boolean status);
    public void setCategory(String category);
    public void setCallIn(Authority callIn);
    public void setCallOut(Authority callOut);
    public Boolean getStatus();
    public String getCategory();
    public Authority getCallIn();
    public Authority getCallOut();
}
