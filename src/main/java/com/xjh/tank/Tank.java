package com.xjh.tank;

import com.xjh.tank.dp.observer.TankFireEvent;
import com.xjh.tank.dp.observer.TankFireHandler;
import com.xjh.tank.dp.observer.TankFireObserver;
import com.xjh.tank.dp.strategy.DefaultFireStrategy;
import com.xjh.tank.dp.strategy.FireStrategy;

import java.awt.*;
import java.util.Arrays;
import java.util.Random;
import java.util.List;

/**
 * @Author: XJH
 * @Date: 2022/9/13 2:26 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Tank extends GameObject {

    public Dir dir = Dir.DOWN;
    private static final int SPEED = 5;
    private boolean isMoving = true;
    public static final int TANK_WIDTH = ResourceMgr.goodTankD.getWidth();
    public static final int TANK_HEIGHT = ResourceMgr.goodTankD.getHeight();
    private boolean living = true;
    public Group group = Group.BAD;
    private Random random = new Random();
    Rectangle rectangle = new Rectangle();
    FireStrategy fs;
    private int preX, preY;

    public Tank(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
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

        GameModel.getInstance().add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            GameModel.getInstance().remove(this);
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

        preX = x;
        preY = y;

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
        if (this.x > GameModel.GAME_WIDTH - Tank.TANK_WIDTH - 2) x = GameModel.GAME_WIDTH - Tank.TANK_WIDTH - 2;
        if (this.y > GameModel.GAME_HEIGHT - Tank.TANK_HEIGHT - 2) y = GameModel.GAME_HEIGHT - Tank.TANK_HEIGHT - 2;
    }

    private void randomDir() {
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void fire() {
        fs.fire(this);
    }

    public void destroy() {
        living = false;
    }

    public void stop() {
        isMoving = false;
    }

    public void restoreLocation() {
        x = preX;
        y = preY;
    }

    private List<TankFireObserver> observers = Arrays.asList(new TankFireHandler());

    public void handleFireKey() {
        final TankFireEvent tankFireEvent = new TankFireEvent(this);
        for (TankFireObserver observer : observers) {
            observer.actionOnFire(tankFireEvent);
        }
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

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    public int getPreX() {
        return preX;
    }

    public void setPreX(int preX) {
        this.preX = preX;
    }

    public int getPreY() {
        return preY;
    }

    public void setPreY(int preY) {
        this.preY = preY;
    }

    @Override
    public int getWidth() {
        return TANK_WIDTH;
    }

    @Override
    public int getHeight() {
        return TANK_HEIGHT;
    }
}
