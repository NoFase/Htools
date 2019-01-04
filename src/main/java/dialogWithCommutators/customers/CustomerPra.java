package dialogWithCommutators.customers;

public class CustomerPra extends Customer{
    private final String TYPE = "PRA";

    public CustomerPra(String number, String lp, String tg, String rsc, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        super(number, lp, tg, rsc, csc, status, category, charg, callIn, callOut);
    }

    public CustomerPra(String number, String lp, String tg, String rsc, String csc) {
        super (number, lp, tg, rsc, csc);
    }

    public String getTYPE() {
        return TYPE;
    }
}
