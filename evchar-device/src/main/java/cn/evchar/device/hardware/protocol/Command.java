package cn.evchar.device.hardware.protocol;


public interface Command extends Protocol
{
    public byte[] toBytes();
}
