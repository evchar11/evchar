package cn.evchar.device.hardware;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.protocol.netty.ProtocolEncoder;
import cn.evchar.device.hardware.protocol.netty.ServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

public class DeviceAcceptor {

	private static final Logger logger = LoggerFactory
			.getLogger(DeviceAcceptor.class);

	private static final String SERVER_IP = "192.168.0.107";
	private static final int PORT = 6666;
	private static final int BACKLOG_SIZE = 1000;

	private static DeviceAcceptor acceptor = new DeviceAcceptor();

	public static DeviceAcceptor getInstance() {
		return acceptor;
	}

	private DeviceAcceptor() {
		EventLoopGroup bossGroup = new NioEventLoopGroup(1);
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
					.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, BACKLOG_SIZE)
					.handler(new LoggingHandler(LogLevel.INFO))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						public void initChannel(SocketChannel ch)
								throws Exception {
							ChannelPipeline p = ch.pipeline();
							p.addLast(new ProtocolEncoder());
							p.addLast(new ServerHandler(null));
						}
					});

			// Start the server.
			ChannelFuture f = b.bind(SERVER_IP, PORT).sync();

			// Wait until the server socket is closed.
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			logger.error(ExceptionUtils.getStackTrace(e));
		} finally {
			// Shut down all event loops to terminate all threads.
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
}
