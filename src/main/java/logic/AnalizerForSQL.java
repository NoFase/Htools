package logic;

import GUI.otherWindows.MyAlert;
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

    public AnalizerForSQL(String ipServer, boolean pra, boolean sip, boolean esl, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, boolean gw, boolean interfaces, String txtCus, String txtTg, String txtRsc, String txtGw, String txtInterface) {
        new MyAlert("такой выбор еще не запрогроммирован!");
    }

    public AnalizerForSQL(String ipServer, boolean pra, boolean prk, boolean restrict, boolean cus, boolean charg, boolean tg, boolean rsc, String txtCus, String txtTg, String txtRsc) {
        this.ipServer = ipServer;
        if (prk && restrict && cus && charg){
            requestMessage = "SELECT *" +
                    "FROM tbl_PRAUserData";
        }
        else if (prk && !restrict && !cus && !charg) {
            requestMessage = "SELECT *" +
                    "FROM tbl_PRAUserData WHERE iStatus = 0";
        }
//        requestMessage = "SELECT * FROM tbl_PRAUserData";
        connectingToSQL();
        initCustomerPra();
    }

    public AnalizerForSQL(String ipServer, boolean sip, boolean prk, boolean restrict, boolean cus, boolean charg, boolean gw, String txtCus, String txtGw) {
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

    private void connectingToSQL(){
        holder = new HolderMS2000();
        holder.connecting(ipServer, "searcher", "SoftX3000");
        rs = new Request().sendingRequest(holder.getStatement(), requestMessage);

    }
    private void initCustomerPra(){
        try {
            while (rs.next()){
                Boolean[] callIn = new ParseAuthority().transcoding(rs.getString("sCallInRight"));
                Boolean[] callOut = new ParseAuthority().transcoding(rs.getString("sCallOutRight"));
                Authority callInA = new CallIn(callIn[0], callIn[1], callIn[2], callIn[3], callIn[4]);
                Authority callOutA = new CallOUT(callOut[0], callOut[1], callOut[2], callOut[3], callOut[4]);
//                System.out.println("==========> " + rs.getString("sDn") + "\t" +
//                                rs.getString("sNPNumber")+ "\t" +
//                        rs.getString("iPRATg")+ "\t" +
//                        rs.getString("iRouteSelCode")+ "\t" +
//                        rs.getString("iCallSrcCode")+ "\t" +
//                        rs.getString("iStatus")+ "\t" +
//                        rs.getString("iCmdCat")+ "\t" +
//                        rs.getString("iChargeType"));

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
}


/*

        Holder holder = new HolderMS2000();
        holder.connecting(ipServer, "searcher", "SoftX3000");
        ResultSet rs = new Request().sendingRequest(holder.getStatement(), requestMessage);
        try {
            while (rs.next()){
                System.out.println(rs.getString("sDN") + "\t-\t" + rs.getString("iStatus")
                        + "\t-\t" + rs.getString("iCallSrcCode"));
            }
            holder.closeConnecting();
        } catch (SQLException e) {
            System.out.println("Что то пошло не так...");
            e.printStackTrace();
        }
 */