package com.xjh.tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/13 3:22 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Explode {
    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private TankFrame tf;
    int step = 0;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
//            step = 0;
            tf.explodes.remove(this);
        }
    }

}
