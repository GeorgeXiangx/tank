package com.xjh.tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/13 3:22 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Explode extends GameObject {
    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    int step = 0;

    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
//            step = 0;
            GameModel.getInstance().remove(this);
        }
    }

    @Override
    public int getWidth() {
        return EXPLODE_WIDTH;
    }

    @Override
    public int getHeight() {
        return EXPLODE_HEIGHT;
    }
}
