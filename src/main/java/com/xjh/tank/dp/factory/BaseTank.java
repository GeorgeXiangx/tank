package com.xjh.tank.dp.factory;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import com.xjh.tank.TankFrame;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/20 10:11 上午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class BaseTank {
    public boolean isMoving = true;
    public int x = 200, y = 200;
    public TankFrame tf = null;
    public Group group = Group.BAD;
    public Dir dir = Dir.DOWN;
    public boolean living = true;
    public Rectangle rectangle = new Rectangle();

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

    public boolean isLiving() {
        return living;
    }

    public void setLiving(boolean living) {
        this.living = living;
    }

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }


    public abstract void paint(Graphics g);

    public abstract void destroy();

    public abstract void fire();
}
