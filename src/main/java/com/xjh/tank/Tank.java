package com.xjh.tank;

import com.xjh.tank.net.TankJoinMsg;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @Author: XJH
 * @Date: 2022/9/13 2:26 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Tank {

    private int x = 200, y = 200;
    private Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean isMoving = false;
    private TankFrame tf = null;
    public static final int TANK_WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankD.getHeight();
    private boolean living = true;
    private Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rectangle = new Rectangle();
    private UUID id = UUID.randomUUID();

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
    }

    public Tank(TankJoinMsg tankJoinMsg) {
        this.x = tankJoinMsg.x;
        this.y = tankJoinMsg.y;
        this.dir = tankJoinMsg.getDir();
        this.isMoving = tankJoinMsg.isMoving();
        this.group = tankJoinMsg.getGroup();
        this.id = tankJoinMsg.getId();

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = TANK_WIDTH;
        this.rectangle.height = TANK_HEIGHT;
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.enemyTanks.remove(this);
        }

        // uuid on head
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString(id.toString(), this.x, this.y - 10);
        g.setColor(color);

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
        // 计算子弹发射位置
        int bX = this.x + TANK_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int bY = this.y + TANK_HEIGHT / 2 - Bullet.BULLET_HEIGHT / 2;
        this.tf.bullets.add(new Bullet(bX, bY, this.dir, this.tf, this.group));
    }

    public void destroy() {
        living = false;

        int eX = this.x + TANK_WIDTH / 2 - Explode.EXPLODE_WIDTH / 2;
        int eY = this.y + TANK_HEIGHT / 2 - Explode.EXPLODE_HEIGHT / 2;
        tf.explodes.add(new Explode(eX, eY, this.tf));
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public boolean isMoving() {
        return isMoving;
    }

    public UUID getId() {
        return id;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }
}
