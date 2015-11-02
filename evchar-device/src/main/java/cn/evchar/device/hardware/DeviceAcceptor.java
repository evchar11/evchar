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

import java.net.InetSocketAddress;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.evchar.common.ApiCode;
import cn.evchar.common.exception.EvcharException;
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
import cn.evchar.device.hardware.protocol.receive.SnStatus;
import cn.evchar.device.hardware.protocol.receive.StateStatus;
import cn.evchar.device.hardware.protocol.sent.ReadSnCommand;
import cn.evchar.device.hardware.protocol.sent.ReadStatusCommand;
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

	private Map<String, ChannelHandlerContext> deviceMap = new HashMap<>();
	private Map<ChannelHandlerContext, String> snMap = new HashMap<>();
	private Map<String, DeviceLived> liveDeviceMap = new HashMap<>();

	private DeviceAcceptor() {
	}

	public Map<String, DeviceLived> getLiveDeviceMap() {
		return liveDeviceMap;
	}

	public Map<String, ChannelHandlerContext> getDeviceMap() {
		return deviceMap;
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
											p.addLast(new ProtocolDecoder());
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

	public void add(String sn, ChannelHandlerContext ctx) {
		deviceMap.put(sn, ctx);
		logger.info("设备连接: " + sn + ctx.channel().remoteAddress());
	}

	public void remove(String sn, ChannelHandlerContext ctx) {
		deviceMap.remove(sn);
		logger.info("设备断开: " + ctx.channel().remoteAddress());
	}

	public void on(String deviceSn) {
		ChannelHandlerContext ctx = deviceMap.get(deviceSn);
		ctx.write(new SetStateCommand(DeviceStateType.POWER_ON));
		ctx.flush();
	}

	public void off(String deviceSn) {
		ChannelHandlerContext ctx = deviceMap.get(deviceSn);
		ctx.write(new SetStateCommand(DeviceStateType.POWER_OFF));
		ctx.flush();
	}

	@Override
	public void handle(BatteryStatus batteryStatus, ChannelHandlerContext ctx) {
		// refreshDeviceMap(batteryStatus.getSn(), ctx);
		logger.info("电量");
	}

	@Override
	public void handle(BootCompletedStatus bootCompletedStatus,
			ChannelHandlerContext ctx) {
		logger.info("启动完成");
	}

	@Override
	public void handle(ModelStatus modelStatus, ChannelHandlerContext ctx) {
		// refreshDeviceMap(modelStatus.getSn(), ctx);
		logger.info("型号");
	}

	@Override
	public void handle(PheriStatus pheriStatus, ChannelHandlerContext ctx) {
		// refreshDeviceMap(pheriStatus.getSn(), ctx);
		logger.info("外设");
	}

	@Override
	public void handle(PowerStatus powerStatus, ChannelHandlerContext ctx) {
		// refreshDeviceMap(powerStatus.getSn(), ctx);
		logger.info("电量");
	}

	@Override
	public void handle(ServerIpStatus serverIpStatus, ChannelHandlerContext ctx) {
		// refreshDeviceMap(serverIpStatus.getSn(), ctx);
		logger.info("服务器IP");
	}

	@Override
	public void handle(ServerPortStatus serverPortStatus,
			ChannelHandlerContext ctx) {
		// refreshDeviceMap(serverPortStatus.getSn(), ctx);
		logger.info("服务器端口");
	}

	@Override
	public void handle(StateStatus stateStatus, ChannelHandlerContext ctx) {
		refreshDeviceMap(stateStatus.getSn(), stateStatus.getState(), ctx);
		logger.info("收到设备状态:" + stateStatus.getSn() + " "
				+ stateStatus.getState());
	}

	private void refreshDeviceMap(String sn, DeviceStateType type,
			ChannelHandlerContext ctx) {
		InetSocketAddress address = (InetSocketAddress) ctx.channel()
				.remoteAddress();
		liveDeviceMap.put(sn, new DeviceLived(sn, address.getHostString(),
				address.getPort(), type));
		deviceMap.put(sn, ctx);
	}

	public DeviceLived getAliveDevice(String sn) {
		if (liveDeviceMap.containsKey(sn)) {
			return liveDeviceMap.get(sn);
		} else {
			throw new EvcharException(ApiCode.ERR_DEVICE_NOT_ONLINE, "设备当前不在线");
		}
	}

	public void onDeviceConnection(ChannelHandlerContext ctx,
			boolean isConnected) {
		if (isConnected) {
			ctx.write(new ReadSnCommand());
		} else {
			String sn = snMap.get(ctx);
			liveDeviceMap.remove(sn);
			deviceMap.remove(sn);
			logger.info("设备掉线");
		}
	}

	@Override
	public void handle(SnStatus snStatus, ChannelHandlerContext ctx) {
	}
}
