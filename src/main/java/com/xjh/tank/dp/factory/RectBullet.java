package com.xjh.tank.dp.factory;

import com.xjh.tank.*;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/20 10:18 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class RectBullet extends BaseBullet {
    private static final int SPEED = 20;
    private int x, y;
    private Dir dir;
    public static final int BULLET_WIDTH = ResourceMgr.bulletD.getWidth();
    public static final int BULLET_HEIGHT = ResourceMgr.bulletD.getHeight();
    private TankFrame tf = null;
    private boolean living = true;
    private Group group = Group.BAD;
    Rectangle rectangle = new Rectangle();

    public RectBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
        this.group = group;

        this.rectangle.x = this.x;
        this.rectangle.y = this.y;
        this.rectangle.width = BULLET_WIDTH;
        this.rectangle.height = BULLET_HEIGHT;

        this.tf.bullets.add(this);
    }

    public void paint(Graphics g) {
        if (!living) {
            tf.bullets.remove(this);
        }

        // 这里颜色和移动是分开的
        final Color color = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, 20, 20);
        g.setColor(color);

        move();
    }

    private void move() {
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

        if (x < 0 || y < 0 || x > tf.GAME_WIDTH || y > tf.GAME_HEIGHT) {
            living = false;
        }

        this.rectangle.x = x;
        this.rectangle.y = y;
    }

    public void collideWith(BaseTank tank) {
        if (this.group == tank.getGroup()) return;
//        Rectangle rectangle = new Rectangle(x, y, BULLET_WIDTH, BULLET_HEIGHT);
//        Rectangle rectangle2 = new Rectangle(tank.getX(), tank.getY(), Tank.TANK_WIDTH, Tank.TANK_HEIGHT);

        if (rectangle.intersects(tank.rectangle)) {
            this.destroy();
            tank.destroy();
        }
    }

    private void destroy() {
        this.living = false;
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

}
