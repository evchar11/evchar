package cn.evchar.device.hardware.protocol.sent;

import cn.evchar.device.hardware.protocol.types.DataType;

public class ReadSnCommand extends ReadCommand
{
    @Override
    public DataType getDataType()
    {
        return DataType.READ_SN;
    }
}
