package logic;

import dialogWithCommutators.ConnectingForTasks;
import dialogWithCommutators.ConnectingWithServer;
import dialogWithCommutators.commands.Command;
import dialogWithCommutators.commands.DspOFTK;
import network.TCPConnection;

import java.io.IOException;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import static staticVariable.StaticVariables.*;

public class MyTiming implements Runnable {
    private final long LONGPERIOD = 300000;
    public MyTiming() {
        System.out.println("===> MyTiming ---> method CONSTRUCTOR --> Class initialised");
    }

    @Override
    public void run() {
        MyDate time = new MyDate();
        Date startTime = time.setStartTimeForOFTK();
        System.out.println("===> MyTiming ---> method run --> set startTime: " + startTime.toString());

        ipServer = "10.140.27.8";
        loginName = "Smednyh";
        passwordForLogin = "SoftX3000";


//        ConnectingForTasks connectingForTasks = new ConnectingForTasks();

//        while (!connectingForTasks.getConnected()){
//            System.out.println("===> MyTiming ---> method run --> connected:" + connectingForTasks.getConnected());
//        }

//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("===> MyTiming ---> method run --> sending command DSP OFTK");
//        Command dspOftk = new DspOFTK();
//        String command = null;
//        while (command == null){
//            ((DspOFTK) dspOftk).setNumberTg("19");
//            command = dspOftk.creatingCommand();
//        }
//        connection.sendString(command);


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                ipServer = "10.140.27.8";
                ConnectingForTasks connectingForTasks = new ConnectingForTasks();
//                while (!connectingForTasks.getConnected());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Command dspOftk = new DspOFTK();
                String command = null;
                while (command == null){
                ((DspOFTK) dspOftk).setNumberTg("110");
                command = dspOftk.creatingCommand();
                }
                connection.sendString(command);
                try {
                    Thread.sleep(1000);
                    connection.disconnect();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, startTime, LONGPERIOD);
    }
}
