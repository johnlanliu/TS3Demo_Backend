package com.anytrek.ts3.common;

/**
 * 车辆行驶状态
 * @author John
 * date 2018 M10 8
 */
public class TrackerEvent {
	//静止并且点火
	public static final int IDLE = 0;
	//行驶
	public static final int DRIVING = 1;
	//熄火
	public static final int ENGINE_OFF = 2;
}
