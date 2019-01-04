package logic;

import GUI.otherWindows.MyAlert;
import SQLdialog.Holder;
import SQLdialog.HolderMS2000;
import SQLdialog.Request;
import dialogWithCommutators.customers.*;
import logs.ShowProblemClassAndMethod;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Pattern;

import static staticVariable.StaticVariables.customers;

public class AnalizerForSQL {

    private String requestMessage;
    private Holder holder;
    private ResultSet rs;
    private String ipServer;
    private StringBuilder stringBuilderReq = new StringBuilder("SELECT * FROM ");
    private String tblPra = "tbl_PRAUserData";
    private String tblSip = "tbl_SIPUserData";
    private Pattern pattern;


    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, boolean gw, boolean interfaces, String txtCSC, String txtCus, String txtTg, String txtRsc, String txtGw, String txtInterface) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCSC, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        stringBuilderReq.append(tblPra);
//        ============>
//        System.out.println(stringBuilderReq.toString());
//        1     v   v   v   v
        if (prk && CSC && cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
//            нужна проверка txtCus на то что удовлетворяет требованиям категории
            stringBuilderReq.delete(stringBuilderReq.length()-1 , stringBuilderReq.length());
            stringBuilderReq.append(" AND ");
            constructingString("iChargeType");
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        2     v   v   v   o
        else if (prk && CSC && cus && !charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        3     v   v   o   v
        else if (prk && CSC && !cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            constructingString("iChargeType");
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        4     v   o   v   v
        else if (prk && !CSC && cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            constructingString("iChargeType");
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        5     o   v   v   v
        else if (!prk && CSC && cus && charg){
            stringBuilderReq.append("WHERE (iChargeType = 0)");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        6     o   o   v   v
        else if (!prk && !CSC && cus && charg){
            stringBuilderReq.append("WHERE (iChargeType = 0)");
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        7     o   v   v   o
        else if (!prk && CSC && cus && !charg){
            if (!txtCSC.equals("") || txtCSC != null) {
                stringBuilderReq.append("WHERE iCallSrcCode = ");
                stringBuilderReq.append(txtCSC);
                stringBuilderReq.append(")");
            }
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        8     v   v   o   o
        else if (prk && CSC && !cus && !charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        9     v   o   o   v
        else if (prk && !CSC && !cus && charg) {
            stringBuilderReq.append(" WHERE (iStatus = 0 AND ");
            constructingString("iChargeType");
            if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        10     v   o   o   o
        else if (prk && !CSC && !cus && !charg) {
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (tg && !txtTg.equals(""))constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        11     o   v   o   o
        else if (!prk && CSC && !cus && !charg) {
            stringBuilderReq.append(" Where (");
            if (!txtCSC.equals("") || txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (tg && !txtTg.equals(""))constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        12    o   o   v   o
        else if (!prk && !CSC && cus && !charg) {
            stringBuilderReq.append(" Where (");

            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (tg && !txtTg.equals(""))constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
        }
//        13    o   o   o   v
        else if (!prk && !CSC && !cus && charg) {
            stringBuilderReq.append(" Where (");
            constructingString("iChargeType");
            if (tg && !txtTg.equals(""))constructingString("iPRATg", txtTg);
            else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
//            else requestMessage = stringBuilderReq.toString();
        }
        requestMessage = stringBuilderReq.toString();
//               ==========>
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerPra();

//        for (Map.Entry<String, Customer> xxx: customers.entrySet()) {
//            System.out.println(xxx.getValue().getCallIn().getInternational().toString() + "\t" +
//                    xxx.getValue().getCallIn().getIntraOffice().toString() + "\t" +
//                    xxx.getValue().getCallIn().getLocal().toString() + "\t" +
//                    xxx.getValue().getCallIn().getLocalToll().toString() + "\t" +
//                    xxx.getValue().getCallIn().getNational().toString());
//        }
    }

    public AnalizerForSQL(String ipServer, boolean sip, boolean prk, boolean CSC, boolean cus, boolean charg, boolean gw, String txtCSC, String txtCus, String txtGw) {
        this.ipServer = ipServer;
        stringBuilderReq.append(tblSip);

        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg, boolean gw, String txtCSC, String txtCus, String txtInterface, int i) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, boolean gw, String txtCSC, String txtCus, String txtTg, String txtRsc, String txtGw) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, boolean interfaces, String txtCSC, String txtCus, String txtTg, String txtRsc, String txtInterface, int i) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean sip, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg,  boolean gw, boolean interfaces, String txtCSC, String txtCus, String txtGw, String txtInterface) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }
    private void connectingToSQL(){
        holder = new HolderMS2000();
        holder.connecting(ipServer, "searcher", "SoftX3000");
        rs = new Request().sendingRequest(holder.getStatement(), requestMessage);
    }
    private void initCustomerPra(){
        try {
            while (rs.next()){
//                System.out.println(rs.getString("sCallInRight"));
                Boolean[] callIn = new ParseAuthority().transcoding(rs.getString("sCallInRight"));
                Boolean[] callOut = new ParseAuthority().transcoding(rs.getString("sCallOutRight"));
                Authority callInA = new CallIn(callIn[0], callIn[1], callIn[2], callIn[3], callIn[4]);
                Authority callOutA = new CallOUT(callOut[0], callOut[1], callOut[2], callOut[3], callOut[4]);

                customers.put(rs.getString("sDn"),
                        new CustomerPra(rs.getString("sDn"),
                                rs.getString("sNPNumber"),
                                rs.getString("iPRATg"),
                                rs.getString("iRouteSelCode"),
                                rs.getString("iCallSrcCode"),
                                rs.getString("iStatus"),
                                rs.getString("iCmdCat"),
                                rs.getString("iChargeType"),
                                callInA, callOutA));
            }
        } catch (SQLException e) {
            new ShowProblemClassAndMethod(e, this.getClass());
        }
    }

    private void constructingString (String parameter, String txt) {
        if (stringBuilderReq.substring(stringBuilderReq.length()-1).equals(")")) {
            stringBuilderReq.delete(stringBuilderReq.length()-1 , stringBuilderReq.length());
            stringBuilderReq.append(" AND ");
        }
        else if (stringBuilderReq.toString().contains("(") && !stringBuilderReq.toString().contains("AND") && stringBuilderReq.toString().contains("=")) stringBuilderReq.append(" AND ");
        stringBuilderReq.append(" ");
        stringBuilderReq.append(parameter);
        stringBuilderReq.append(" = ");
        stringBuilderReq.append(txt);
        if (stringBuilderReq.toString().contains("AND")) stringBuilderReq.append(")");
        requestMessage = stringBuilderReq.toString();
    }

    private void constructingString (String parameter){
        if (stringBuilderReq.substring(stringBuilderReq.length()-1).equals(")")) {
            stringBuilderReq.delete(stringBuilderReq.length()-1 , stringBuilderReq.length());
        }
        if (stringBuilderReq.toString().contains("(") && !stringBuilderReq.toString().contains("AND") && stringBuilderReq.toString().contains("=")) stringBuilderReq.append(" AND ");
        stringBuilderReq.append(parameter);
        stringBuilderReq.append(" = 0)");
//        if (stringBuilderReq.toString().contains("AND")) stringBuilderReq.append(")");
        requestMessage = stringBuilderReq.toString();
    }
}

//        1     v   v   v   v
//        2     v   v   v   o
//        3     v   v   o   v
//        4     v   o   v   v
//        5     o   v   v   v
//        6     o   o   v   v
//        7     o   v   v   o
//        8     v   v   o   o
//        9     v   o   o   v
//        10    v   o   o   o
//        11    o   v   o   o
//        12    o   o   v   o
//        13    o   o   o   v
