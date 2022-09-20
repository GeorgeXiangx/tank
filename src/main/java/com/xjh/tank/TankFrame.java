package com.xjh.tank;

import com.xjh.tank.dp.factory.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @Author: XJH
 * @Date: 2022/9/13 9:23 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankFrame extends Frame {
    public static final int GAME_WIDTH = 1000;
    public static final int GAME_HEIGHT = 1000;


    //    Bullet b = new Bullet(300, 300, Dir.DOWN);
//    List<Bullet> bullets = new ArrayList();
    public List<BaseBullet> bullets = new ArrayList();

    //    Tank enemyTank = new Tank(200, 400, Dir.UP, this);
    public List<BaseTank> enemyTanks = new ArrayList<>();

    // Explode explode = new Explode(300, 300);
//    public List<Explode> explodes = new ArrayList<>();
    public List<BaseExplode> explodes = new ArrayList<>();

    // 抽象工厂实现产品族的生产，实现换肤功能
    public static GameFactory gf = new DefaultFactory();

    //    Tank myTank = new Tank(200, 200, Dir.DOWN, this, Group.GOOD);
    BaseTank myTank = gf.createTank(200, 200, Dir.DOWN, this, Group.GOOD);

    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("Tank War");
        setVisible(true);

        this.addKeyListener(new MyKeyListener());

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    @Override
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

    // 双缓存解决画面闪烁问题
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        final Graphics graphics = offScreenImage.getGraphics();
        final Color color = graphics.getColor();
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        graphics.setColor(color);
        paint(graphics);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public List<BaseBullet> getBullets() {
        return bullets;
    }

    public void setBullets(List<BaseBullet> bullets) {
        this.bullets = bullets;
    }

    class MyKeyListener extends KeyAdapter {

        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        @Override
        public void keyPressed(KeyEvent e) {
            final int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
            new Thread(() -> new Audio("audio/tank_move.wav").play()).start();
        }


        @Override
        public void keyReleased(KeyEvent e) {
//            System.out.println("key released...");
            final int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                // 按键抬起时发射子弹
                case KeyEvent.VK_SPACE:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        private void setMainTankDir() {
            if (!bL && !bR && !bU && !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);

                if (bL) myTank.setDir(Dir.LEFT);
                if (bU) myTank.setDir(Dir.UP);
                if (bR) myTank.setDir(Dir.RIGHT);
                if (bD) myTank.setDir(Dir.DOWN);
            }
        }

    }

}
