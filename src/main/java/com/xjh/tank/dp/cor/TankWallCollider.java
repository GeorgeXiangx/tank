package com.xjh.tank.dp.cor;

import com.xjh.tank.GameObject;
import com.xjh.tank.Tank;
import com.xjh.tank.Wall;

/**
 * @Author: XJH
 * @Date: 2022/9/27 11:26 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Tank && o2 instanceof Wall) {
            Tank tank = (Tank) o1;
            Wall wall = (Wall) o2;
            if (tank.getRectangle().intersects(wall.getRectangle())) {
                tank.restoreLocation();
            }
        } else if (o1 instanceof Wall && o2 instanceof Tank) {
            collide(o2, o1);
        }
        return true;
    }
}
