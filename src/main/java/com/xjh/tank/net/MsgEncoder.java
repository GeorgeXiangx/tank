package com.xjh.tank.net;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: XJH
 * @Date: 2022/12/21 4:11 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class MsgEncoder extends MessageToByteEncoder<TankJoinMsg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf out) throws Exception {
        out.writeInt(msg.x);
        out.writeInt(msg.y);
        out.writeInt(msg.getDir().ordinal());
        out.writeInt(msg.getGroup().ordinal());
        out.writeBoolean(msg.isMoving());
        out.writeLong(msg.getId().getMostSignificantBits());
        out.writeLong(msg.getId().getLeastSignificantBits());
    }
}
