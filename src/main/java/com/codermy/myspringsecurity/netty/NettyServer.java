package com.codermy.myspringsecurity.netty;

import io.netty.bootstrap.ServerBootstrap;

/**
 * @author huangyongqi
 * @date 2023/3/9
 */
public class NettyServer {

    private int port;
    private ServerBootstrap serverBootstrap;

    public NettyServer(int port, ServerBootstrap serverBootstrap) {
        this.port = port;
        this.serverBootstrap = serverBootstrap;
    }

    public void start() throws InterruptedException {
        serverBootstrap.bind(port).sync().channel().closeFuture().sync();
    }

    public void shutdownGracefully() {
        serverBootstrap.config().group().shutdownGracefully();
        serverBootstrap.config().childGroup().shutdownGracefully();
    }

}

