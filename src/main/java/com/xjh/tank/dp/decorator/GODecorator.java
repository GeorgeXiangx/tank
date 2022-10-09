package com.xjh.tank.dp.decorator;

import com.xjh.tank.GameObject;

import java.awt.*;

/**
 * @Author: XJH
 * @Date: 2022/10/8 5:47 下午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class GODecorator extends GameObject {

    GameObject go;

    public GODecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        go.paint(g);
    }
}
