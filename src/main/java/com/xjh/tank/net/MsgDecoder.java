package com.xjh.tank.net;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;
import java.util.UUID;

/**
 * @Author: XJH
 * @Date: 2022/12/21 4:12 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class MsgDecoder extends ByteToMessageDecoder {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        // TCP拆包和粘包的问题
        if (in.readableBytes() < 8) return;

        // 4+4+4+4+1+8+8 = 33
        int x = in.readInt();
        int y = in.readInt();
        Dir dir = Dir.values()[in.readInt()];
        Group group = Group.values()[in.readInt()];
        boolean isMoving = in.readBoolean();
        UUID uuid = new UUID(in.readLong(), in.readLong());

        out.add(new TankJoinMsg(x, y, dir, group, isMoving, uuid));
    }
}
