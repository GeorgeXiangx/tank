package com.xjh.tank.dp.cor;

import com.xjh.tank.Bullet;
import com.xjh.tank.GameObject;
import com.xjh.tank.Tank;
import com.xjh.tank.Wall;

/**
 * @Author: XJH
 * @Date: 2022/9/27 11:26 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Wall) {
            Bullet bullet = (Bullet) o1;
            Wall wall = (Wall) o2;
            if (bullet.getRectangle().intersects(wall.getRectangle())) {
                bullet.destroy();
                return false;
            }
        } else if (o1 instanceof Wall && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
