package com.xjh.tank.dp.strategy;

import com.xjh.tank.Tank;
import com.xjh.tank.dp.factory.BaseTank;

/**
 * @Author: XJH
 * @Date: 2022/9/16 3:31 下午
 * @Email: xiangjunhong@newhope.cn
 */
public interface FireStrategy {

    void fire(BaseTank tank);
}
