package com.xjh.tank.net;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

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
        buf.writeBytes(tankJoinMsg.toBytes());

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
}
