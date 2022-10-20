package com.xjh.tank.dp.observer;

import com.xjh.tank.Tank;

/**
 * @Author: XJH
 * @Date: 2022/10/20 3:31 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankFireEvent {

    private Tank tank;

    public TankFireEvent(Tank tank) {
        this.tank = tank;
    }

    public Tank getSource() {
        return tank;
    }
}
