package dialogWithCommutators.customers;

public class CustomerESL extends Customer{
    private final String TYPE = "ESL";

    public CustomerESL(String number, String lp, String tg, String rsc, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        super(number, lp, tg, rsc, csc, status, category, charg, callIn, callOut);
    }

    public CustomerESL(String number, String lp, String tg, String rsc, String csc) {
        super(number, lp, tg, rsc, csc);
    }

    public CustomerESL(String number, String lp, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        super(number, lp, csc, status, category, charg, callIn, callOut);
    }

    public String getTYPE() {
        return TYPE;
    }
}
