package cn.evchar.device.hardware.protocol.sent;

import cn.evchar.device.hardware.protocol.types.DataType;

public class ReadStatusCommand  extends ReadCommand
{
    @Override
    public DataType getDataType()
    {
        return DataType.READ_STATUS;
    }
}