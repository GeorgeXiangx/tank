package com.xjh.tank.dp.factory;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import com.xjh.tank.TankFrame;

/**
 * @Author: XJH
 * @Date: 2022/9/20 10:14 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class RectFactory extends GameFactory {

    @Override
    public BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectTank(x, y, dir, tf, group);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group) {
        return new RectBullet(x, y, dir, tf, group);
    }

    @Override
    public BaseExplode createExplode(int x, int y, TankFrame tf) {
        return new RectExplode(x, y, tf);
    }
}
