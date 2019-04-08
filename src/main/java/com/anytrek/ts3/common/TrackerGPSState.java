package com.anytrek.ts3.common;

/**
 * GPS定位状态
 * @author John
 * date 2018 M10 8
 */
public class TrackerGPSState {
	//未定位
	public static final int NOT_POSITIONED = 0;
	
	//2D定位   （默认）
	public static final int POSITIONED_2D = 1;
	
	//3D定位
	public static final int POSITIONED_3D = 2;
}
