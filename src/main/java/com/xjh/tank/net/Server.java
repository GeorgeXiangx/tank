package com.xjh.tank.net;

import com.xjh.tank.util.DateUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @Author: XJH
 * @Date: 2022/12/8 3:37 下午
 * @Email: xiangjunhong@newhope.cn
 */
public class Server {
    public static ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    public void serverStart() {
        final NioEventLoopGroup bossGroup = new NioEventLoopGroup(1);
        final NioEventLoopGroup workerGroup = new NioEventLoopGroup(2);

        final ServerBootstrap b = new ServerBootstrap();
        try {
            final ChannelFuture future = b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    // 禁用Nagle算法，保证消息的实时性
                    .option(ChannelOption.TCP_NODELAY, Boolean.TRUE)
                    .childHandler(new ChannelInitializer<SocketChannel>() {

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline()
                                    .addLast(new MsgEncoder())
                                    .addLast(new MsgDecoder())
                                    .addLast(new ServerChildHandler());
                        }
                    })
                    .bind(8889)
                    .sync();

            ServerFrame.INSTANCE.updateServerMsg("server started....");
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }
}

class ServerChildHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Server.clients.add(ctx.channel());
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 更新ServerFrame界面文字消息
        ServerFrame.INSTANCE.updateClientMsg(msg.toString());
        System.out.println(DateUtils.getDateTimeNow() + " Server开始转发消息 ： " + msg);
        // Server收到消息后直接转发给所有client
        Server.clients.writeAndFlush(msg);

//        ByteBuf buf = null;
//        try {
//            buf = (ByteBuf) msg;
////            System.out.println(buf);
//            byte[] bytes = new byte[buf.readableBytes()];
//            buf.getBytes(buf.readerIndex(), bytes);
//            final String s = new String(bytes);
//
//            if (s.equals("_bye_")) {
//                System.out.println("有客户端要退出");
//                Server.clients.remove(ctx.channel());
//                ctx.close();
//            }
//
//            System.out.println(s);
//            ServerFrame.INSTANCE.updateClientMsg(s);
//            Server.clients.writeAndFlush(msg);
//            ServerFrame.INSTANCE.updateServerMsg(s);
//        } finally {
////            if (buf != null) {
////                ReferenceCountUtil.release(buf);
////            }
//        }

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        // 删除出现异常的客户端channel，并关闭连接
        Server.clients.remove(ctx.channel());
        ctx.close();
    }
}
