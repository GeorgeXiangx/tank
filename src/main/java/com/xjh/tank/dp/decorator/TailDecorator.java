package com.xjh.tank.dp.decorator;

import com.xjh.tank.GameObject;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/10/9 4:09 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class TailDecorator extends GODecorator {

    public TailDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x = go.x;
        this.y = go.y;

        super.paint(g);
        final Color color = g.getColor();
        g.setColor(Color.WHITE);
        g.drawLine(x, y, x + 20, y + 20);
        g.setColor(color);
    }

    @Override
    public int getWidth() {
        return super.go.getWidth();
    }

    @Override
    public int getHeight() {
        return super.go.getHeight();
    }


}
