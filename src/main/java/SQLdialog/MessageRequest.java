package SQLdialog;

import org.h2.util.ScriptReader;

public class MessageRequest {
    private String message;

    public String allPraUser(){
        return "SELECT * FROM tbl_PRAUserData";
    }
    public String listingOfAllTables(){
        return "SELECT * FROM INFORMATION_SCHEMA.TABLES";
    }

    public String creatingTblServers(){
        return "create table IF NOT EXISTS tbl_Servers (" +
                "ID   INT         NOT NULL," +
                "ABR  VARCHAR(50) NOT NULL," +
                "NAME VARCHAR(50)," +
                "IP   VARCHAR(50) NOT NULL," +
                "PRIMARY KEY ( id, abr, ip ));";
    }
    public String creatingTblLog(String abrCity){
        return "create table IF NOT EXISTS tbl_Log" + abrCity + "(" +
                "ID         LONG        NOT NULL," +
                "DATA       VARCHAR(50) NOT NULL," +
                "TIME       VARCHAR(50) NOT NULL," +
                "LOGIN      VARCHAR(50) NOT NULL," +
                "IP         VARCHAR(50) NOT NULL," +
                "COMMAND    STRING      NOT NULL," +
                "PRIMARY KEY ( id ));";
    }

    public String selectingMaxIdInTbl(String nameTbl){
        return ("SELECT MAX(ID) FROM " + nameTbl);
    }

    public String addingToTblServers(int id, String abr, String nameServer, String ip){
        return "insert into tbl_Servers (id, ABR, NAME, IP) values (" +
                id + ", '" + abr + "', '" + nameServer + "', '" + ip + "');";
    }

    public String listAllOfTbl (String nameTbl){
        return "SELECT * FROM " + nameTbl + ";";
    }

    public String deletingTbl (String nameTbl){
        return "DROP TABLE " + nameTbl;
    }
}
