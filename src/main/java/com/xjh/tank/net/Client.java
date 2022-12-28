package com.xjh.tank.net;

import com.xjh.tank.TankFrame;
import com.xjh.tank.net.msg.Msg;
import com.xjh.tank.net.msg.TankJoinMsg;
import com.xjh.tank.util.DateUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @Author: XJH
 * @Date: 2022/11/25 2:34 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Client {

    public static final Client INSTANCE = new Client();
    private Channel channel = null;

    public void connect() {
        EventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();

        try {
            ChannelFuture f = bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ClientChannelInitializer())
                    .connect("localhost", 8889);

            f.addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    if (future.isSuccess()) {
                        System.out.println("connected");
                        // 确认连接后保存channel
                        channel = future.channel();
                    } else {
                        System.out.println("not connected");
                    }
                }
            });

            f.sync();

            System.out.println(".........");

            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            group.shutdownGracefully();
        }
    }

    public void send(Msg msg) {
//        final ByteBuf byteBuf = Unpooled.copiedBuffer(msg.toBytes());
        // 这里在加入自定义的对象的Encoder和Decoder后，直接发送相应对象，不需要再发送ByteBuf
        channel.writeAndFlush(msg);
    }

    public void closeConnect() {
//        this.send("_bye_");
    }
}

class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ch.pipeline()
                .addLast(new MsgEncoder())
                .addLast(new MsgDecoder())
                .addLast(new ClientHandler());
    }
}


class ClientHandler extends SimpleChannelInboundHandler<Msg> {

    /**
     * 接收到消息处理事件
     *
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Msg msg) throws Exception {
        System.out.println(DateUtils.getDateTimeNow() + " client handler channelRead0 " + msg);
        msg.handle();
    }

    /**
     * channel激活后处理事件
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(new TankJoinMsg(TankFrame.INSTANCE.getMyTank()));
    }
}