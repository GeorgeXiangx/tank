package com.xjh.tank.dp.factory;

import com.xjh.tank.Tank;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/9/20 10:13 上午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank tank);
}
