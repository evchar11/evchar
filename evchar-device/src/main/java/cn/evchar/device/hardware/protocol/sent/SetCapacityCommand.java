package cn.evchar.device.hardware.protocol.sent;

import java.util.Arrays;

import cn.evchar.device.hardware.protocol.DefaultCommand;
import cn.evchar.device.hardware.protocol.types.DataType;

public class SetCapacityCommand extends DefaultCommand {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new SetCapacityCommand().toBytes()));
	}

	public SetCapacityCommand() {

	}

	public SetCapacityCommand(int capacity) {
		this.capacity = capacity;
	}

	private int capacity;

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public DataType getDataType() {
		return DataType.SET_CAPACITY;
	}

	@Override
	public int getArgsLength() {
		return 1;
	}

	@Override
	protected int[] getArgs() {
		return new int[] { capacity };
	}

}
