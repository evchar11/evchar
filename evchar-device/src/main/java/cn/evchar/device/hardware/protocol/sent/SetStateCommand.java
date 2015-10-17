package cn.evchar.device.hardware.protocol.sent;

import java.util.Arrays;

import cn.evchar.device.hardware.protocol.DefaultCommand;
import cn.evchar.device.hardware.protocol.types.DataType;
import cn.evchar.device.hardware.protocol.types.DeviceStateType;

public class SetStateCommand extends DefaultCommand {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(new SetStateCommand().toBytes()));
	}

	public SetStateCommand() {

	}

	public SetStateCommand(DeviceStateType state) {
		this.state = state;
	}

	private DeviceStateType state = DeviceStateType.OFF;;

	public DeviceStateType getState() {
		return state;
	}

	public void setState(DeviceStateType state) {
		this.state = state;
	}

	@Override
	public DataType getDataType() {
		return DataType.SET_STATUS;
	}

	@Override
	public int getArgsLength() {
		return 2;
	}

	@Override
	protected int[] getArgs() {
		switch (state) {
		case ENERGIZED:
			return new int[] { 0, 1 };
		default:
			return new int[] { 0, 2 };
		}
	}

}
