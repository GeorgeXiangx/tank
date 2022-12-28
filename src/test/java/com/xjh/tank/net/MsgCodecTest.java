package com.xjh.tank.net;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import com.xjh.tank.net.msg.TankJoinMsg;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.junit.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;

/**
 * @Author: XJH
 * @Date: 2022/12/23 1:55 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class MsgCodecTest {


    @Test
    public void testEncoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        final UUID uuid = UUID.randomUUID();
        final TankJoinMsg tankJoinMsg = new TankJoinMsg(5, 10, Dir.DOWN, Group.GOOD, false, uuid);

        ch.pipeline().addLast(new MsgEncoder());
        ch.writeOutbound(tankJoinMsg);

        // 出站,entity -> bytes    encode序列化
        final ByteBuf buf = (ByteBuf) ch.readOutbound();

        // 类型
        MsgType msgType = MsgType.values()[buf.readInt()];
        assertEquals(msgType, MsgType.TankJoin);

        // 长度
        final int length = buf.readInt();
        assertEquals(length, 33);

        // 消息体
        final int x = buf.readInt();
        final int y = buf.readInt();
        Dir dir = Dir.values()[buf.readInt()];
        Group group = Group.values()[buf.readInt()];
        boolean isMoving = buf.readBoolean();
        UUID id = new UUID(buf.readLong(), buf.readLong());

        assertEquals(5, x);
        assertEquals(10, y);
        assertEquals(Dir.DOWN, dir);
        assertEquals(Group.GOOD, group);
        assertEquals(false, isMoving);
        assertEquals(uuid, id);
    }

    @Test
    public void testDecoder() {
        EmbeddedChannel ch = new EmbeddedChannel();

        final UUID uuid = UUID.randomUUID();
        final TankJoinMsg tankJoinMsg = new TankJoinMsg(5, 10, Dir.DOWN, Group.GOOD, false, uuid);

        ch.pipeline().addLast(new MsgDecoder());

        final ByteBuf buf = Unpooled.buffer();
        buf.writeInt(MsgType.TankJoin.ordinal());
        final byte[] bytes = tankJoinMsg.toBytes();
        buf.writeInt(bytes.length);
        buf.writeBytes(bytes);

        // 进站,bytes -> entity     decode反序列化
        ch.writeInbound(buf.duplicate());
        final TankJoinMsg msg = (TankJoinMsg) ch.readInbound();

        assertEquals(5, msg.x);
        assertEquals(10, msg.y);
        assertEquals(Dir.DOWN, msg.getDir());
        assertEquals(Group.GOOD, msg.getGroup());
        assertEquals(false, msg.isMoving());
        assertEquals(uuid, msg.getId());
    }

    @Test
    public void testDateTimeStr() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss.SSS"));
    }
}
