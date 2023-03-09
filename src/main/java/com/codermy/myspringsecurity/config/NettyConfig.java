package com.codermy.myspringsecurity.config;


import com.codermy.myspringsecurity.netty.NettyServer;
import com.codermy.myspringsecurity.netty.NettyServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author huangyongqi
 * @date 2023/3/9
 */
@Configuration
public class NettyConfig {

    @Value("${netty.server.port}")
    private int port;

    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Bean
    public EventLoopGroup bossGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public EventLoopGroup workerGroup() {
        return new NioEventLoopGroup();
    }

    @Bean
    public ServerBootstrap serverBootstrap() {
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup(), workerGroup())
                .channel(NioServerSocketChannel.class)
                .childHandler(nettyServerHandler);
        return b;
    }

    @Bean(initMethod = "start", destroyMethod = "shutdownGracefully")
    public NettyServer nettyServer() {
        return new NettyServer(port, serverBootstrap());
    }

}

