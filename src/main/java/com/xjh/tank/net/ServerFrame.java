package com.xjh.tank.net;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @Author: XJH
 * @Date: 2022/12/21 4:36 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class ServerFrame extends Frame {
    public static final ServerFrame INSTANCE = new ServerFrame();

    Server server = new Server();
    TextArea taLeft = new TextArea();
    TextArea taRight = new TextArea();
    Button btnStart = new Button("start");

    public ServerFrame() {
        this.setSize(1600, 600);
        this.setLocation(300, 30);
        this.add(btnStart, BorderLayout.NORTH);
        final Panel p = new Panel(new GridLayout(1, 2));
        p.add(taLeft);
        p.add(taRight);
        this.add(p);

        taLeft.setFont(new Font("verderna", Font.PLAIN, 25));

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        ServerFrame.INSTANCE.setVisible(true);
        ServerFrame.INSTANCE.server.serverStart();
    }

    public void updateServerMsg(String string) {
        this.taLeft.setText(taLeft.getText() + string + System.getProperty("line.separator"));
    }

    public void updateClientMsg(String string) {
        this.taRight.setText(taRight.getText() + string + System.getProperty("line.separator"));
    }
}
