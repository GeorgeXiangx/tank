package com.xjh.tank.dp.observer;

import java.io.Serializable;

/**
 * @Author: XJH
 * @Date: 2022/10/20 3:30 下午
 * @Email: xiangjunhong@newhope.cn
 */
public interface TankFireObserver extends Serializable {

    void actionOnFire(TankFireEvent event);
}
