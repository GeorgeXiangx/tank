package com.xjh.tank.dp.strategy;

import com.xjh.tank.Tank;

import java.io.Serializable;

/**
 * @Author: XJH
 * @Date: 2022/9/16 3:31 下午
 * @Email: xiangjunhong@newhope.cn
 */
public interface FireStrategy extends Serializable {

    void fire(Tank tank);
}
