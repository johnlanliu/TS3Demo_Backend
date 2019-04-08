package com.anytrek.ts3.common;

/**
 * 设备上报指令枚举类  （HTTP版）
 * @author kkzhu
 */
public enum TrackerCommandType {
	uRpLoc, gDecode, gCellAddr, report, uRpMulti, uPulse, u, w, uAdminCollectData, uHelp, agps, uUpdate, uObdRp;
	
	public static TrackerCommandType getType(String cmd){
		return valueOf(cmd);
	} 
}
