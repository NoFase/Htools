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

    private final String PATTERNAUTH = "((01111)|(10111)|(11011)|(11101)|(11110))([0-1]{27})";
    private String requestMessage;
    private Holder holder;
    private ResultSet rs;
    private String ipServer;
    private StringBuilder stringBuilderReq = new StringBuilder("SELECT * FROM ");
    private String tblPra = "tbl_PRAUserData";
    private String tblSip = "tbl_SIPUserData";
    private Pattern pattern;


    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean esl, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, boolean gw, boolean interfaces, String txtCus, String txtTg, String txtRsc, String txtGw, String txtInterface) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        stringBuilderReq.append(tblPra);
//        ============>
//        System.out.println(stringBuilderReq.toString());
        if (prk && restrict && cus && charg){
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            if (txtCus.equals("") || txtCus == null) requestMessage = stringBuilderReq.toString();
//            нужна проверка txtCus на то что удовлетворяет требованиям категории
            else {
                constructingString("iCmdCat", txtCus);
            }
            if (tg && !txtTg.equals("")){
                constructingString("iPRATg", txtTg);
            }
            else if (rsc && !txtRsc.equals("")){
                constructingString("iRouteSelCode", txtRsc);
            }

        }
        else if (prk && !restrict && !cus && !charg) {
            stringBuilderReq.append(" WHERE (iStatus = 0)");
            requestMessage = stringBuilderReq.toString();
            if (tg && !txtTg.equals("")){
                constructingString("iPRATg", txtTg);
            }
            else if (rsc && !txtRsc.equals("")){
                constructingString("iRouteSelCode", txtRsc);
            }
        }
        else if (!prk && restrict && !cus && !charg){
//            pattern = Pattern.compile(PATTERNAUTH);
//            stringBuilderReq.append(" WHERE (sCallOutRight = dbo.regexp_match(");
//            stringBuilderReq.append(PATTERNAUTH);
//            stringBuilderReq.append("))");
//            requestMessage = stringBuilderReq.toString();
        }
//               ==========>
        System.out.println(requestMessage);
        connectingToSQL();
        initCustomerPra();
    }

    public AnalizerForSQL(String ipServer, boolean sip, boolean prk, boolean restrict, boolean cus, boolean charg, boolean gw, String txtCus, String txtGw) {
        this.ipServer = ipServer;
        stringBuilderReq.append(tblSip);

        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean esl, boolean prk, boolean restrict, boolean cus, boolean charg, boolean gw, String txtCus, String txtInterface, int i) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, boolean gw, String txtCus, String txtTg, String txtRsc, String txtGw) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean esl, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, boolean interfaces, String txtCus, String txtTg, String txtRsc, String txtInterface, int i) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean sip, boolean esl, boolean prk, boolean restrict, boolean cus, boolean charg,  boolean gw, boolean interfaces,String txtCus, String txtGw, String txtInterface) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }
//[1]{5}[0-1]{27}
    private void connectingToSQL(){
        holder = new HolderMS2000();
        holder.connecting(ipServer, "searcher", "SoftX3000");
        rs = new Request().sendingRequest(holder.getStatement(), requestMessage);

    }
    private void initCustomerPra(){
        try {
            while (rs.next()){
                System.out.println(rs.getString("sCallInRight"));
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
        stringBuilderReq.delete(stringBuilderReq.length()-1 , stringBuilderReq.length());
        stringBuilderReq.append(" AND ");
        stringBuilderReq.append(parameter);
        stringBuilderReq.append(" = ");
        stringBuilderReq.append(txt);
        stringBuilderReq.append(")");
        requestMessage = stringBuilderReq.toString();
    }

}
