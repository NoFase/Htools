package dialogWithCommutators.customers;

public class CustomerSip extends Customer {
    private final String TYPE = "SIP";


    public CustomerSip(String number, String lp, String tg, String rsc, String csc, String status, String category, String charg, Authority callIn, Authority callOut) {
        super(number, lp, tg, rsc, csc, status, category, charg, callIn, callOut);
    }

    public CustomerSip(String number, String lp, String tg, String rsc, String csc) {
        super(number, lp, tg, rsc, csc);
    }

    public CustomerSip(String sDn, String sNPNumber, String iCallSrcCode, String iStatus, String iCmdCat, String iChargeType, Authority callInA, Authority callOutA) {
        super(sDn, sNPNumber, iCallSrcCode, iStatus, iCmdCat, iChargeType, callInA, callOutA);
    }

    public String getTYPE() {
        return TYPE;
    }
}
