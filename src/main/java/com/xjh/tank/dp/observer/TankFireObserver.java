package com.xjh.tank.dp.observer;

/**
 * @Author: XJH
 * @Date: 2022/10/20 3:30 下午
 * @Email: xiangjunhong@newhope.cn
 */
public interface TankFireObserver {

    void actionOnFire(TankFireEvent event);
}
