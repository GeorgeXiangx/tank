package com.xjh.tank;

import com.xjh.tank.dp.factory.BaseTank;
import com.xjh.tank.dp.factory.GameFactory;
import com.xjh.tank.dp.strategy.DefaultFireStrategy;
import com.xjh.tank.dp.strategy.FireStrategy;
import com.xjh.tank.dp.strategy.FourDirFireStrategy;

import java.awt.*;
import java.util.Random;

/**
 * @Author: XJH
 * @Date: 2022/9/13 2:26 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Tank extends BaseTank {
    private static final int SPEED = 5;
    public static final int TANK_WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankD.getHeight();
    private boolean living = true;
    private Random random = new Random();
    FireStrategy fs;

    public Tank(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = TANK_WIDTH;
        this.rectangle.height = TANK_HEIGHT;

        if (group == Group.GOOD) {
            try {
                String goodTankFSName = PropertyMgr.getString("goodTankFS");
                fs = (FireStrategy) Class.forName(goodTankFSName).newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            fs = new DefaultFireStrategy();
        }
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemyTanks.remove(this);
        }

//        final Color color = g.getColor();
//        g.setColor(Color.YELLOW);
//        g.fillRect(x, y, 50, 50);
//        g.setColor(color);
        switch (dir) {
            case LEFT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(this.group == Group.GOOD ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
                break;
        }

        move();
    }

    private void move() {
        if (!isMoving) {
            return;
        }

        switch (dir) {
            case LEFT:
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

        if (this.group == Group.BAD && random.nextInt(100) > 95) fire();
        if (this.group == Group.BAD && random.nextInt(100) > 95) randomDir();

        boundsCheck();

        rectangle.x = x;
        rectangle.y = y;
    }


    private void boundsCheck() {
        if (this.x < 2) x = 2;
        if (this.y < 28) y = 28;
        if (this.x > TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 2) x = TankFrame.GAME_WIDTH - Tank.TANK_WIDTH - 2;
        if (this.y > TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 2) y = TankFrame.GAME_HEIGHT - Tank.TANK_HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void destroy() {
        living = false;

        int eX = this.x + TANK_WIDTH / 2 - Explode.EXPLODE_WIDTH / 2;
        int eY = this.y + TANK_HEIGHT / 2 - Explode.EXPLODE_HEIGHT / 2;
//        tf.explodes.add(new Explode(eX, eY, this.tf));
        tf.explodes.add(TankFrame.gf.createExplode(eX, eY, this.tf));
    }
}
