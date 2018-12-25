package SQLdialog;

import java.sql.*;

public class HolderBD {
    private final String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private String connectionString = "jdbc:jtds:sqlserver://10.140.27.9:1433;databaseName=Bam";
    public static String JDBC_URL = "jdbc:sqlserver://10.140.27.9:1433;databaseName=Tables;integratedSecurity=true";

    private final String login = "searcher";
    private final String password = "SoftX3000";
    private String ipSQLServer;

    private Connection conn = null;
    private Statement stmt = null;

    public Connection getConn() {
        return conn;
    }

    public Statement getStmt() {
        return stmt;
    }

    public void run(){
//      name table with pra user - tbl_PRAUserData
        String request = "SELECT INFORMATION_SCHEMA.COLUMNS FROM tbl_PRAUserData";
//        запрос всех полей в таблице
        String request1 = "SELECT ORDINAL_POSITION,COLUMN_NAME,DATA_TYPE,CHARACTER_MAXIMUM_LENGTH,IS_NULLABLE,COLUMN_DEFAULT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'tbl_PRAUserData' ORDER BY ORDINAL_POSITION ASC";
//        String request = "SELECT * FROM sysobjects WHERE type = 'U'"; // запрос всех доступных таблиц
        /*
        information about table tbl_PRAUserData
            1		-	ID
            2		-	iModuleNum
            3		-	iUSN
            4		-	sDN
            5		-	iPRATg
            6		-	iRouteSelCode
            7		-	iUserType
            8		-	iUserStatus
            9		-	iUserExType
            10		-	iOperatorType
            11		-	iCentrexFlag
            12		-	iCallObserveFlag
            13		-	iNPFlag
            14		-	iReserved0
            15		-	iSubGroupUser
            16		-	iPriortyLevel
            17		-	iPINCtlFlag
            18		-	iReserved1
            19		-	iCmdCat
            20		-	iCentrexGrpNo
            21		-	iCxSubGrp
            22		-	sEnhancedVoiceAttribute
            23		-	sServiceCtrlFlag
            24		-	iCallSrcCode
            25		-	iPbxGrpNo
            26		-	iOperatorGrpNo
            27		-	iCodecPrefer
            28		-	sCallOutPur
            29		-	sCallInPur
            30		-	sCallOutRight
            31		-	sCallInRight
            32		-	iRecvDevType
            33		-	iCallWatchFlag
            34		-	iReserved
            35		-	iPulseType
            36		-	iChargeErr
            37		-	iChargeType
            38		-	iReserved2
            39		-	iRingMode
            40		-	iUSBFlag
            41		-	iReserved3
            42		-	iCallerAccSrc
            43		-	iCalleeAccSrc
            44		-	iPreferCugIndex
            45		-	iPreferCUGGrpNo
            46		-	iIfCUGUser
            47		-	iOutgoingAccess
            48		-	iIncomingAccess
            49		-	iPreferCUGBarring
            50		-	iReservedCUG
            51		-	sConsNewServiceRight
            52		-	sNewServiceRight
            53		-	sCallOutPassward
            54		-	sSupDontDisPwd
            55		-	iCugDataIdx
            56		-	iIsdnDataIdx
            57		-	iLimitGrpNo
            58		-	sExNewServiceRight
            59		-	iMaxAllowance
            60		-	iMaxTimeRestrict
            61		-	iRMSource
            62		-	sNPNumber
            63		-	iUCModule
            64		-	iUCUSN
            65		-	iKValue
            66		-	iSupportCoti
            67		-	iPreselectPolicyCode
            68		-	sNewServiceLocByPIN
            69		-	sNewServiceRcssByPIN
            70		-	sObservationBill
            71		-	iAllowCallInWhenPKR
            72		-	iReserved4
            73		-	iVmsidx
            74		-	iZoneIndex
            75		-	iABDCapacity
            76		-	sBarringCallInRight
            77		-	iReserved5
            78		-	iReserved6
            79		-	iStatus

         */

        try{
            DriverManager.registerDriver(new net.sourceforge.jtds.jdbc.Driver());

            conn = DriverManager.getConnection(connectionString, login, password);


            if(conn != null) {
                System.out.println("Connected!");

                DatabaseMetaData metaObj = (DatabaseMetaData) conn.getMetaData();
                System.out.println("Driver Name?= " + metaObj.getDriverName() + ", Driver Version?= " + metaObj.getDriverVersion() + ", Product Name?= " + metaObj.getDatabaseProductName() + ", Product Version?= " + metaObj.getDatabaseProductVersion());
                stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(request1);
                while (rs.next()){
                    System.out.println(rs.getString(1) + "\t\t-\t" + rs.getString(2));
                }

                rs.close();
                stmt.close();
                conn.close();
                System.out.println("\n<=====finish");
            }
        } catch (SQLException e){
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
            return;
        }
        try {
            conn.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection");
            e.printStackTrace();
            return;
        }
    }
}
