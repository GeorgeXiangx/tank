package com.xjh.tank.dp.cor;

import com.xjh.tank.GameObject;

/**
 * @Author: XJH
 * @Date: 2022/9/27 11:23 上午
 * @Email: xiangjunhong@newhope.cn
 */
public interface Collider {

    /**
     * 返回true继续,false终止
     *
     * @param o1
     * @param o2
     * @return
     */
    boolean collide(GameObject o1, GameObject o2);
}
