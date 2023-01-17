package com.xjh.tank.net;

/**
 * @Author: XJH
 * @Date: 2022/12/23 4:11 下午
 * @Email: xiangjunhong@newhope.cn
 */
public enum MsgType {
    TankJoin, TankStartMoving, TankStopMoving, TankDirChanged, TANK_DIE, FIRE_BULLET
}

// TCP Nagle算法