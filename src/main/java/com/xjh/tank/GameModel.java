package com.xjh.tank;

import com.xjh.tank.dp.cor.BulletTankCollider;
import com.xjh.tank.dp.cor.Collider;
import com.xjh.tank.dp.cor.ColliderChain;
import com.xjh.tank.dp.cor.TankTankCollider;

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
//    //    Bullet b = new Bullet(300, 300, Dir.DOWN);
//    List<Bullet> bullets = new ArrayList();
//    //    Tank enemyTank = new Tank(200, 400, Dir.UP, this);
//    List<Tank> enemyTanks = new ArrayList<>();
//    // Explode explode = new Explode(300, 300);
//    List<Explode> explodes = new ArrayList<>();

    List<GameObject> objects = new ArrayList<>();
    //    Collider collider = new BulletTankCollider();
//    Collider collider2 = new TankTankCollider();
    ColliderChain colliderChain = new ColliderChain();

    public GameModel() {
        int initTankCount = Integer.parseInt((String) PropertyMgr.get("initTankCount"));

        for (int i = 0; i < initTankCount; i++) {
            objects.add(new Tank(200 * i, 400, Dir.UP, this, Group.BAD));
        }
    }

    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.WHITE);
//        g.drawString("子弹的数量:" + bullets.size(), 10, 60);
//        g.drawString("敌人坦克的数量:" + enemyTanks.size(), 10, 80);
//        g.drawString("爆炸的数量:" + enemyTanks.size(), 10, 100);
        g.setColor(color);

        myTank.paint(g);
//        b.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        // 碰撞检测
        for (int i = 0; i < objects.size(); i++) {
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o1 = objects.get(i);
                GameObject o2 = objects.get(j);
//                collider.collide(o1, o2);
//                collider2.collide(o1, o2);
                colliderChain.collide(o1, o2);
            }
        }

//        for (Iterator<Bullet> it = bullets.iterator(); it.hasNext(); ) {
//            final Bullet bullet = it.next();
//            if (!bullet.isLive()) {
//                it.remove();
//            }
//        }
//        if (enemyTank != null) enemyTank.paint(g);

//        for (int i = 0; i < enemyTanks.size(); i++) {
//            enemyTanks.get(i).paint(g);
//        }

        // 碰撞检测
//        for (int i = 0; i < enemyTanks.size(); i++) {
//            for (int j = 0; j < bullets.size(); j++) {
//                bullets.get(j).collideWith(enemyTanks.get(i));
//            }
//        }

//        for (int i = 0; i < explodes.size(); i++) {
//            explodes.get(i).paint(g);
//        }
//        explode.paint(g);
    }

    public void add(GameObject go) {
        this.objects.add(go);
    }

    public void remove(GameObject go) {
        this.objects.remove(go);
    }

    public Tank getMainTank() {
        return myTank;
    }
}
