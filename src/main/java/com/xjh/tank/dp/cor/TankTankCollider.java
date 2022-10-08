package com.xjh.tank.dp.cor;

import com.xjh.tank.Bullet;
import com.xjh.tank.GameObject;
import com.xjh.tank.Tank;

/**
 * @Author: XJH
 * @Date: 2022/9/27 11:26 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Tank) {
            Tank tank1 = (Tank) o1;
            Tank tank2 = (Tank) o2;
            if (tank1.getRectangle().intersects(tank2.getRectangle())) {
//                tank1.stop();
//                tank2.stop();
                tank1.restoreLocation();
                tank2.restoreLocation();
            }
        }
        return true;
    }
}
