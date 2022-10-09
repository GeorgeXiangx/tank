package com.xjh.tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/21 6:25 下午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class GameObject {

    public int x, y;

    public abstract void paint(Graphics g);

    public abstract int getWidth();

    public abstract int getHeight();
}
