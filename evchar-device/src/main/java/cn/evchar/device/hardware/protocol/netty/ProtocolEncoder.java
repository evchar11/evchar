package cn.evchar.device.hardware.protocol.netty;

import cn.evchar.device.hardware.protocol.Command;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;


public class ProtocolEncoder extends MessageToByteEncoder<Command>
{
    @Override  
    protected void encode(ChannelHandlerContext ctx, Command cmd, ByteBuf out) throws Exception {  
        byte[] datas = cmd.toBytes(); 
        out.writeBytes(datas);  
        ctx.flush();  
    }  
}
