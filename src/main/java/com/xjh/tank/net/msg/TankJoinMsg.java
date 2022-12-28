package com.xjh.tank.net.msg;

import com.xjh.tank.Dir;
import com.xjh.tank.Group;
import com.xjh.tank.Tank;
import com.xjh.tank.TankFrame;
import com.xjh.tank.net.Client;
import com.xjh.tank.net.MsgType;
import com.xjh.tank.util.DateUtils;

import java.io.*;
import java.util.Objects;
import java.util.UUID;

/**
 * @Author: XJH
 * @Date: 2022/12/21 4:10 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class TankJoinMsg extends Msg {

    public int x, y;
    private Dir dir;
    private Group group;
    private boolean moving;
    private UUID id;

    public TankJoinMsg(int x, int y, Dir dir, Group group, boolean moving, UUID id) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.moving = moving;
        this.id = id;
    }

    public TankJoinMsg(Tank tank) {
        this.x = tank.getX();
        this.y = tank.getY();
        this.dir = tank.getDir();
        this.group = tank.getGroup();
        this.moving = tank.isMoving();
        this.id = tank.getId();
    }

    public TankJoinMsg() {

    }

    @Override
    public String toString() {
        return "TankJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", dir=" + dir +
                ", group=" + group +
                ", moving=" + moving +
                ", id=" + id +
                '}';
    }

    @Override
    public void parse(byte[] bytes) {
        final DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.dir = Dir.values()[dis.readInt()];
            this.group = Group.values()[dis.readInt()];
            this.moving = dis.readBoolean();
            this.id = new UUID(dis.readLong(), dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public byte[] toBytes() {
        ByteArrayOutputStream baos = null;
        DataOutputStream dos = null;
        byte[] bytes = null;
        try {
            baos = new ByteArrayOutputStream();
            dos = new DataOutputStream(baos);

            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(dir.ordinal());
            dos.writeInt(group.ordinal());
            dos.writeBoolean(moving);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();

            bytes = baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (baos != null) {
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (dos != null) {
                    dos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    /**
     * 消息接收后的处理流程
     */
    @Override
    public void handle() {
        // 判断此消息是不是由自己发出，是则略过，否则需要将新加入的坦克绘制到Frame上，并且要报告给server上其他的client自己的tank的位置
        if (this.id.equals(TankFrame.INSTANCE.getMyTank().getId())
                || Objects.nonNull(TankFrame.INSTANCE.getTankByUUID(this.id))) {
            return;
        }
        // 加入tank map
        final Tank tank = new Tank(this);
        TankFrame.INSTANCE.addTank(tank);
        System.out.println(DateUtils.getDateTimeNow() + " 发送自己的tank : " + TankFrame.INSTANCE.getMyTank() + " 给其他client");
        // 将自己发送给其他client
        final TankJoinMsg tankJoinMsg = new TankJoinMsg(TankFrame.INSTANCE.getMyTank());
        Client.INSTANCE.send(tankJoinMsg);
        System.out.println(DateUtils.getDateTimeNow() + " TankJoinMsg : " + tankJoinMsg);
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.TankJoin;
    }

    public Dir getDir() {
        return dir;
    }

    public Group getGroup() {
        return group;
    }

    public boolean isMoving() {
        return moving;
    }

    public UUID getId() {
        return id;
    }
}
