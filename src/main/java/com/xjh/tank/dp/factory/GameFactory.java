package com.xjh.tank.dp.factory;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import com.xjh.tank.TankFrame;

/**
 * @Author: XJH
 * @Date: 2022/9/20 9:48 上午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class GameFactory {

    public abstract BaseTank createTank(int x, int y, Dir dir, TankFrame tf, Group group);

    public abstract BaseBullet createBullet(int x, int y, Dir dir, TankFrame tf, Group group);

    public abstract BaseExplode createExplode(int x, int y, TankFrame tf);
}
