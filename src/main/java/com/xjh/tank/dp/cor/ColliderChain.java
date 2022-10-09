package com.xjh.tank.dp.cor;

import com.xjh.tank.GameObject;
import com.xjh.tank.PropertyMgr;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: XJH
 * @Date: 2022/9/27 3:37 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class ColliderChain implements Collider {

    List<Collider> colliders = new LinkedList<>();

    public ColliderChain() {
//        add(new TankTankCollider());
//        add(new BulletTankCollider());
        String colliders = PropertyMgr.getString("colliders");
        Arrays.stream(colliders.split(",")).forEach(collider -> {
            try {
                add((Collider) Class.forName(collider).newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void add(Collider collider) {
        colliders.add(collider);
    }

    public void remove(Collider collider) {
        colliders.remove(collider);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i < colliders.size(); i++) {
            if (!colliders.get(i).collide(o1, o2)) return false;
        }
        return true;
    }
}
