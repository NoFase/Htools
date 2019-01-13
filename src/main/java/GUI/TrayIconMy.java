package GUI;


import logic.MyTiming;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.*;

public class TrayIconMy {
    public  final String APPLICATION_NAME = "Mushroom";
    public  final String ICON_STR = "/images/image_mushroom32x32.png";

    public TrayIconMy() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
//                try {
//                    new CollectorLogs();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                setTrayIcon();
            }
        });
    }


    private  void setTrayIcon() {
        if(! SystemTray.isSupported() ) {
            return;
        }

        PopupMenu trayMenu = new PopupMenu();
        MenuItem item = new MenuItem("Exit");
        item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        MenuItem timing = new MenuItem("Timing");
        timing.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MyTiming timer = new MyTiming();
                timer.run();

            }
        });
        trayMenu.add(item);
        trayMenu.add(timing);

        URL imageURL = TrayIconMy.class.getResource(ICON_STR);

        Image icon = Toolkit.getDefaultToolkit().getImage(imageURL);
        TrayIcon trayIcon = new TrayIcon(icon, APPLICATION_NAME, trayMenu);
        trayIcon.setImageAutoSize(true);

        SystemTray tray = SystemTray.getSystemTray();
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        trayIcon.displayMessage(APPLICATION_NAME, "Application started!",
                TrayIcon.MessageType.INFO);
    }
}
