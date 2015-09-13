package cn.evchar.device.hardware.protocol.impl;

import cn.evchar.device.hardware.protocol.DefaultCommand;
import cn.evchar.device.hardware.protocol.types.DataType;

public abstract class ReadCommand extends DefaultCommand
{
    public abstract DataType getDataType();

    @Override
    public int getArgsLength()
    {
        return 0;
    }

    @Override
    protected int[] getArgs()
    {
        return BLANK_ARRAY;
    }
}
