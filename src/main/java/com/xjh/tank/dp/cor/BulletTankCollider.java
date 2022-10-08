package com.xjh.tank.dp.cor;

import com.xjh.tank.*;

/**
 * @Author: XJH
 * @Date: 2022/9/27 11:26 上午
 * @Email: xiangjunhong@newhope.cn
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1 instanceof Bullet && o2 instanceof Tank) {
            Bullet bullet = (Bullet) o1;
            Tank tank = (Tank) o2;
//            bullet.collideWith(tank);
            if (bullet.getGroup() == tank.getGroup()) return true;
            if (bullet.getRectangle().intersects(tank.getRectangle())) {
                bullet.destroy();
                tank.destroy();

                int eX = tank.getX() + Tank.TANK_WIDTH / 2 - Explode.EXPLODE_WIDTH / 2;
                int eY = tank.getY() + Tank.TANK_HEIGHT / 2 - Explode.EXPLODE_HEIGHT / 2;
                new Explode(eX, eY);

                return false;
            }
        } else if (o1 instanceof Tank && o2 instanceof Bullet) {
            return collide(o2, o1);
        }
        return true;
    }
}
