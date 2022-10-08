package com.xjh.tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/10/8 3:05 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Wall extends GameObject {

    int w, h;
    Rectangle rectangle;

    public Wall(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.rectangle = new Rectangle(x, y, w, h);

        GameModel.getInstance().add(this);
    }

    @Override
    public void paint(Graphics g) {
        final Color color = g.getColor();
        g.setColor(Color.GRAY);
        g.fillRect(this.x, this.y, this.w, this.h);
        g.setColor(color);
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}
