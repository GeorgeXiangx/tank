package com.xjh.tank.net;

import com.xjh.tank.net.msg.Msg;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @Author: XJH
 * @Date: 2022/12/21 4:11 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class MsgEncoder extends MessageToByteEncoder<Msg> {

    @Override
    protected void encode(ChannelHandlerContext ctx, Msg msg, ByteBuf out) throws Exception {
        // 消息体：消息类型+消息长度+消息内容
        out.writeInt(msg.getMsgType().ordinal());
        final byte[] bytes = msg.toBytes();
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
