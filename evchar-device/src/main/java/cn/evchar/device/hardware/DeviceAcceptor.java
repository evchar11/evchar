package cn.evchar.device.hardware;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.device.hardware.protocol.StatusHandler;
import cn.evchar.device.hardware.protocol.netty.ProtocolDecoder;
import cn.evchar.device.hardware.protocol.netty.ProtocolEncoder;
import cn.evchar.device.hardware.protocol.netty.ServerHandler;
import cn.evchar.device.hardware.protocol.receive.BatteryStatus;
import cn.evchar.device.hardware.protocol.receive.BootCompletedStatus;
import cn.evchar.device.hardware.protocol.receive.ModelStatus;
import cn.evchar.device.hardware.protocol.receive.PheriStatus;
import cn.evchar.device.hardware.protocol.receive.PowerStatus;
import cn.evchar.device.hardware.protocol.receive.ServerIpStatus;
import cn.evchar.device.hardware.protocol.receive.ServerPortStatus;
import cn.evchar.device.hardware.protocol.receive.StateStatus;
import cn.evchar.device.hardware.protocol.sent.SetStateCommand;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public class DeviceAcceptor implements StatusHandler {

	private static final Logger logger = LoggerFactory
			.getLogger(DeviceAcceptor.class);

	private static final String SERVER_IP = "0.0.0.0";
	private static final int PORT = 55555;
	private static final int BACKLOG_SIZE = 1000;

	private static DeviceAcceptor acceptor = null;

	public static DeviceAcceptor getInstance() {
		if (acceptor == null) {
			acceptor = new DeviceAcceptor();
			acceptor.start();
			return acceptor;
		} else {
			return acceptor;
		}
	}

	private Set<ChannelHandlerContext> deviceSet = new HashSet<>();

	private DeviceAcceptor() {
	}

	public void start() {

		new Thread(new Runnable() {
			public void run() {
				EventLoopGroup bossGroup = new NioEventLoopGroup(1);
				EventLoopGroup workerGroup = new NioEventLoopGroup();
				try {
					ServerBootstrap b = new ServerBootstrap();
					b.group(bossGroup, workerGroup)
							.channel(NioServerSocketChannel.class)
							.option(ChannelOption.SO_BACKLOG, BACKLOG_SIZE)
							.handler(new LoggingHandler(LogLevel.INFO))
							.childHandler(
									new ChannelInitializer<SocketChannel>() {
										@Override
										public void initChannel(SocketChannel ch)
												throws Exception {
											ChannelPipeline p = ch.pipeline();
											p.addLast(new ProtocolEncoder());
											p.addLast(new ServerHandler(
													DeviceAcceptor.this));
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
		}).start();
	}

	public void add(ChannelHandlerContext ctx) {
		deviceSet.add(ctx);
		logger.info("设备连接: " + ctx.channel().remoteAddress());
	}

	public void remove(ChannelHandlerContext ctx) {
		deviceSet.remove(ctx);
		logger.info("设备断开: " + ctx.channel().remoteAddress());
	}

	public void on() {
		logger.info("打开设备" + deviceSet.size());
		for (ChannelHandlerContext ctx : deviceSet) {
			logger.info("设备地址" + ctx.channel().remoteAddress());
			ctx.write(new SetStateCommand(DeviceStateType.ENERGIZED));
			ctx.flush();
		}
	}

	public void off() {
		logger.info("关闭设备" + deviceSet.size());
		for (ChannelHandlerContext ctx : deviceSet) {
			logger.info("设备地址" + ctx.channel().remoteAddress());
			ctx.write(new SetStateCommand(DeviceStateType.IDLE));
			ctx.flush();
		}
	}

	@Override
	public void handle(BatteryStatus batteryStatus, ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(BootCompletedStatus bootCompletedStatus,
			ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(ModelStatus modelStatus, ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(PheriStatus pheriStatus, ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(PowerStatus powerStatus, ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(ServerIpStatus serverIpStatus, ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(ServerPortStatus serverPortStatus,
			ChannelHandlerContext ctx) {
	}

	@Override
	public void handle(StateStatus stateStatus, ChannelHandlerContext ctx) {
	}
}
