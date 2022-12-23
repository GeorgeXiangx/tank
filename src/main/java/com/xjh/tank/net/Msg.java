package com.xjh.tank.net;

/**
 * @Author: XJH
 * @Date: 2022/12/22 6:28 下午
 * @Email: xiangjunhong@newhope.cn
 */
public abstract class Msg {

    public abstract byte[] toBytes();

    public abstract void handle();
}
