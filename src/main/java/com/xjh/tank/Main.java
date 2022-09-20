package com.xjh.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

/**
 * @Author: XJH
 * @Date: 2022/9/13 9:11 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
//        final Frame frame = new Frame();
        final TankFrame tankFrame = new TankFrame();
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
//            tankFrame.enemyTanks.add(new Tank(200 * i, 400, Dir.UP, tankFrame, Group.BAD));
            tankFrame.enemyTanks.add(TankFrame.gf.createTank(200 * i, 400, Dir.UP, tankFrame, Group.BAD));
        }

        new Thread(() -> new Audio("audio/war1.wav").loop()).start();

        while (true) {
            Thread.sleep(25);
            tankFrame.repaint();
        }
    }
}
