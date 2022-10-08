package com.xjh.tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/13 3:22 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Explode extends GameObject{
    public static int EXPLODE_WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int EXPLODE_HEIGHT = ResourceMgr.explodes[0].getHeight();
    private GameModel gm;
    int step = 0;

    public Explode(int x, int y, GameModel gm) {
        this.x = x;
        this.y = y;
        this.gm = gm;
        new Thread(() -> new Audio("audio/explode.wav").play()).start();
    }

    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], x, y, null);
        if (step >= ResourceMgr.explodes.length) {
//            step = 0;
            gm.objects.remove(this);
        }
    }

}
