package com.xjh.tank.dp.observer;

/**
 * @Author: XJH
 * @Date: 2022/10/20 3:33 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankFireHandler implements TankFireObserver {

    @Override
    public void actionOnFire(TankFireEvent event) {
        event.getSource().fire();
    }
}
