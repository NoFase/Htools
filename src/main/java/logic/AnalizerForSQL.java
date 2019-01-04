package logic;

import SQLdialog.Holder;
import SQLdialog.HolderMS2000;
import SQLdialog.Request;
import dialogWithCommutators.customers.*;
import logs.ShowProblemClassAndMethod;

import java.sql.ResultSet;
import java.sql.SQLException;

import static staticVariable.StaticVariables.customers;

public class AnalizerForSQL {

    private String requestMessage;
    private Holder holder;
    private ResultSet rs;
    private String ipServer;
    private StringBuilder stringBuilderReq = new StringBuilder("SELECT * FROM ");
    private String tblPra = "tbl_PRAUserData";
    private String tblSip = "tbl_SIPUserData";
    private String tblESL = "tbl_ESLUserData";
    private boolean prk, CSC, cus, charg, tg, rsc;
    private String txtCSC, txtCus, txtTg, txtRsc;

    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCSC, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.tg = tg;
        this.rsc = rsc;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;
        this.txtTg = txtTg;
        this.txtRsc = txtRsc;

        stringBuilderReq.append(tblPra);
        analasingCheckers(pra);
        requestMessage = stringBuilderReq.toString();
//               ==========>
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerPra();

        nulling();

        stringBuilderReq.append(tblSip);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerSip();

        nulling();

        stringBuilderReq.append(tblESL);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerESL();
    }
//    for pra
    public AnalizerForSQL(String ipServer, boolean pra, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCSC, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.tg = tg;
        this.rsc = rsc;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;
        this.txtTg = txtTg;
        this.txtRsc = txtRsc;

        stringBuilderReq.append(tblPra);
        analasingCheckers(pra);
        requestMessage = stringBuilderReq.toString();
//               ==========>
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerPra();
    }
//    for sip or esl
    public AnalizerForSQL(String ipServer, boolean prk, boolean CSC, boolean cus, boolean charg, String txtCSC, String txtCus, int i) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;

        if (i==1) stringBuilderReq.append(tblSip);
        else stringBuilderReq.append(tblESL);

        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();

        System.out.println("======> " + requestMessage);
        connectingToSQL();
        if (i == 1) initCustomerSip();
        else initCustomerESL();
    }
//    for pra and sip
    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCSC, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.tg = tg;
        this.rsc = rsc;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;
        this.txtTg = txtTg;
        this.txtRsc = txtRsc;

        stringBuilderReq.append(tblPra);
        analasingCheckers(pra);
        requestMessage = stringBuilderReq.toString();
//               ==========>
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerPra();

        nulling();

        stringBuilderReq.append(tblSip);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerSip();
    }
//    for pra and esl
    public AnalizerForSQL(String ipServer, boolean pra, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCSC, String txtCus, String txtTg, String txtRsc, int i) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.tg = tg;
        this.rsc = rsc;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;
        this.txtTg = txtTg;
        this.txtRsc = txtRsc;

        stringBuilderReq.append(tblPra);
        analasingCheckers(pra);
        requestMessage = stringBuilderReq.toString();
//               ==========>
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerPra();

        nulling();

        stringBuilderReq.append(tblESL);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerESL();
    }
//    for sip and esl
    public AnalizerForSQL(String ipServer, boolean sip, boolean esl, boolean prk, boolean CSC, boolean cus, boolean charg,  String txtCSC, String txtCus) {
        this.ipServer = ipServer;
        this.prk = prk;
        this.CSC = CSC;
        this.cus = cus;
        this.charg = charg;
        this.txtCSC = txtCSC;
        this.txtCus = txtCus;

        stringBuilderReq.append(tblSip);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerSip();

        nulling();

        stringBuilderReq.append(tblESL);
        analasingCheckers(false);
        requestMessage = stringBuilderReq.toString();
        System.out.println("======> " + requestMessage);
        connectingToSQL();
        initCustomerESL();
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

    private void initCustomerSip(){
        try{
            while (rs.next()){
                Boolean[] callIn = new ParseAuthority().transcoding(rs.getString("sCallInRight"));
                Boolean[] callOut = new ParseAuthority().transcoding(rs.getString("sCallOutRight"));
                Authority callInA = new CallIn(callIn[0], callIn[1], callIn[2], callIn[3], callIn[4]);
                Authority callOutA = new CallOUT(callOut[0], callOut[1], callOut[2], callOut[3], callOut[4]);

                customers.put(rs.getString("sSDn"),
                        new CustomerSip(rs.getString("sSDn"),
                                rs.getString("sNPNumber"),
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

    private void initCustomerESL(){
        try{
            while (rs.next()){
                Boolean[] callIn = new ParseAuthority().transcoding(rs.getString("sCallInRight"));
                Boolean[] callOut = new ParseAuthority().transcoding(rs.getString("sCallOutRight"));
                Authority callInA = new CallIn(callIn[0], callIn[1], callIn[2], callIn[3], callIn[4]);
                Authority callOutA = new CallOUT(callOut[0], callOut[1], callOut[2], callOut[3], callOut[4]);

                customers.put(rs.getString("sDn"),
                        new CustomerESL(rs.getString("sDn"),
                                rs.getString("sNPNumber"),
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
        if (stringBuilderReq.toString().contains("=") && !stringBuilderReq.toString().contains(")")) stringBuilderReq.append(")");
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

    private void analasingCheckers(Boolean pra) {
//        1     v   v   v   v
        if (prk && CSC && cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
//            нужна проверка txtCus на то что удовлетворяет требованиям категории
            stringBuilderReq.delete(stringBuilderReq.length()-1 , stringBuilderReq.length());
            stringBuilderReq.append(" AND ");
            constructingString("iChargeType");
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        2     v   v   v   o
        else if (prk && CSC && cus && !charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        3     v   v   o   v
        else if (prk && CSC && !cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            constructingString("iChargeType");
            if(pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        4     v   o   v   v
        else if (prk && !CSC && cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            constructingString("iChargeType");
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        5     o   v   v   v
        else if (!prk && CSC && cus && charg){
            stringBuilderReq.append("WHERE (iChargeType = 0)");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        6     o   o   v   v
        else if (!prk && !CSC && cus && charg){
            stringBuilderReq.append("WHERE (iChargeType = 0)");
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        7     o   v   v   o
        else if (!prk && CSC && cus && !charg){
            if (!txtCSC.equals("") && txtCSC != null) {
                stringBuilderReq.append("WHERE iCallSrcCode = ");
                stringBuilderReq.append(txtCSC);
                stringBuilderReq.append(")");
            }
            if (!txtCus.equals("") || txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        8     v   v   o   o
        else if (prk && CSC && !cus && !charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        9     v   o   o   v
        else if (prk && !CSC && !cus && charg) {
            stringBuilderReq.append(" WHERE (iStatus = 0 AND ");
            constructingString("iChargeType");
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        10     v   o   o   o
        else if (prk && !CSC && !cus && !charg) {
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        11     o   v   o   o
        else if (!prk && CSC && !cus && !charg) {
            stringBuilderReq.append(" Where (");
            if (!txtCSC.equals("") && txtCSC != null) constructingString("iCallSrcCode", txtCSC);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        12    o   o   v   o
        else if (!prk && !CSC && cus && !charg) {
            stringBuilderReq.append(" Where (");
            if (!txtCus.equals("") && txtCus != null) constructingString("iCmdCat", txtCus);
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        13    o   o   o   v
        else if (!prk && !CSC && !cus && charg) {
            stringBuilderReq.append(" Where (");
            constructingString("iChargeType");
            if (pra) {
                if (tg && !txtTg.equals("")) constructingString("iPRATg", txtTg);
                else if (rsc && !txtRsc.equals("")) constructingString("iRouteSelCode", txtRsc);
            }
        }
//        14    o   o   o   o
        else if (!prk && !CSC && !cus && !charg){
            if (pra) {

                if (tg && !txtTg.equals("")) {
                    stringBuilderReq.append(" Where (");
                    constructingString("iPRATg", txtTg);
                }
                else if (rsc && !txtRsc.equals("")) {
                    stringBuilderReq.append(" Where (");
                    constructingString("iRouteSelCode", txtRsc);
                }
            }
        }
    }

    private void nulling(){
        requestMessage = null;
        stringBuilderReq = null;
        stringBuilderReq = new StringBuilder("SELECT * FROM ");
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
//        14    o   o   o   o
