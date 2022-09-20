package com.xjh.tank.dp.strategy;

import com.xjh.tank.Bullet;
import com.xjh.tank.Dir;
import com.xjh.tank.Tank;
import com.xjh.tank.TankFrame;
import com.xjh.tank.dp.factory.BaseTank;

import static com.xjh.tank.Tank.TANK_HEIGHT;
import static com.xjh.tank.Tank.TANK_WIDTH;

/**
 * @Author: XJH
 * @Date: 2022/9/16 3:31 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class FourDirFireStrategy implements FireStrategy {

    @Override
    public void fire(BaseTank tank) {
        // 计算子弹发射位置
        int bX = tank.getX() + TANK_WIDTH / 2 - Bullet.BULLET_WIDTH / 2;
        int bY = tank.getY() + TANK_HEIGHT / 2 - Bullet.BULLET_HEIGHT / 2;

        for (Dir dir : Dir.values()) {
//            new Bullet(bX, bY, dir, tank.tf, tank.group);
            TankFrame.gf.createBullet(bX, bY, dir, tank.tf, tank.group);
        }

    }
}
