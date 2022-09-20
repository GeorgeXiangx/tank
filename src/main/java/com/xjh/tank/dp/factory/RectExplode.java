package com.xjh.tank.dp.factory;

import com.xjh.tank.Audio;
import com.xjh.tank.ResourceMgr;
import com.xjh.tank.TankFrame;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/20 10:18 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class RectExplode extends BaseExplode {

    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private int x, y;
    private TankFrame tf;
    int step = 0;

    public RectExplode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.PINK);
        g.fillRect(x, y, step * 10, step * 10);
        step++;

        if (step >= ResourceMgr.explodes.length) {
            tf.explodes.remove(this);
        }

        g.setColor(color);
    }
}
