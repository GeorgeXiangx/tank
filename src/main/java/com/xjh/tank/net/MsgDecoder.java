package com.xjh.tank.net;

import com.xjh.tank.net.msg.Msg;
import com.xjh.tank.util.DateUtils;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.List;

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

        in.markReaderIndex();

        System.out.println(DateUtils.getDateTimeNow() + "-----MsgDecoder------" + ctx + " " + in.readableBytes());
        final MsgType msgType = MsgType.values()[in.readInt()];
        final int length = in.readInt();

        if (in.readableBytes() < length) {
            in.resetReaderIndex();
            return;
        }

        final byte[] bytes = new byte[length];
        in.readBytes(bytes);

        Msg msg = null;
        msg = (Msg) Class.forName("com.xjh.tank.net.msg." + msgType.toString() + "Msg").getDeclaredConstructor().newInstance();

        msg.parse(bytes);
        System.out.println(DateUtils.getDateTimeNow() + "------decode = " + msg);
        out.add(msg);
    }
}
