package com.xjh.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: XJH
 * @Date: 2022/9/21 11:24 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class GameModel {

    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 1000;

    Tank myTank = new Tank(200, 200, Dir.DOWN, this, Group.GOOD);
    //    Bullet b = new Bullet(300, 300, Dir.DOWN);
    List<Bullet> bullets = new ArrayList();
    //    Tank enemyTank = new Tank(200, 400, Dir.UP, this);
    List<Tank> enemyTanks = new ArrayList<>();
    // Explode explode = new Explode(300, 300);
    List<Explode> explodes = new ArrayList<>();


    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            enemyTanks.add(new Tank(200 * i, 400, Dir.UP, this, Group.BAD));
        }
    }

    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
        g.drawString("敌人坦克的数量:" + enemyTanks.size(), 10, 80);
        g.drawString("爆炸的数量:" + enemyTanks.size(), 10, 100);
        g.setColor(color);

        myTank.paint(g);
//        b.paint(g);

        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
//            final Bullet bullet = it.next();
//            if (!bullet.isLive()) {
//                it.remove();
//            }
//        }
//        if (enemyTank != null) enemyTank.paint(g);

        for (int i = 0; i < enemyTanks.size(); i++) {
            enemyTanks.get(i).paint(g);
        }

        // 碰撞检测
        for (int i = 0; i < enemyTanks.size(); i++) {
            for (int j = 0; j < bullets.size(); j++) {
                bullets.get(j).collideWith(enemyTanks.get(i));
            }
        }

        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);
        }
//        explode.paint(g);
    }

    public Tank getMainTank() {
        return myTank;
    }
}
