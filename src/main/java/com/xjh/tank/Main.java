package com.xjh.tank;

import com.xjh.tank.net.Client;

/**
 * @Author: XJH
 * @Date: 2022/9/13 9:11 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        final Frame frame = new Frame();
        final TankFrame tankFrame = TankFrame.INSTANCE;
        tankFrame.setVisible(true);

//        // 初始化敌方坦克
//        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));
//        for (int i = 0; i < initTankCount; i++) {
//            tankFrame.enemyTanks.add(new Tank(200 * i, 400, Dir.UP, tankFrame, Group.BAD));
//        }

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(25);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                tankFrame.repaint();
            }
        }).start();

        Client.INSTANCE.connect();
    }
}
