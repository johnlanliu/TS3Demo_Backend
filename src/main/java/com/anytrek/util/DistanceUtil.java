package com.anytrek.util;

import java.util.Arrays;

/**
 * @author z_hongc
 * @version 创建时间：2010-11-1 上午10:06:55 计算两个经纬度之间的距离
 */
public class DistanceUtil {
	private static final double EARTH_RADIUS = 6378.137;

	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	/**
	 * 返回两点间的距离，单位米
	 * @param lat1
	 * @param lon1
	 * @param lat2
	 * @param lon2
	 * @return
	 */
	public static double getDistance(double lat1, double lon1, double lat2, double lon2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lon1) - rad(lon2);
		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_RADIUS * 1000.0;
		return s;
	}

	/**
	 * 计算距离经纬度一定距离内最大，最小点经纬度
	 * @param lat
	 * @param lon
	 * @param raidus 单位米
	 * @return 数组[minLat, minLng, maxLat, maxLng]
	 */
	public static double[] getAround(double lat, double lon, int raidus) {
		double latitude = lat;
		double longitude = lon;

		double degree = (24901 * 1609) / 360.0;
		double raidusMile = raidus;

		double dpmLat = 1 / degree;
		double radiusLat = dpmLat * raidusMile;
		double minLat = latitude - radiusLat;
		double maxLat = latitude + radiusLat;

		double mpdLng = degree * Math.cos(latitude * (Math.PI / 180));
		double dpmLng = 1 / mpdLng;
		double radiusLng = dpmLng * raidusMile;
		double minLng = longitude - radiusLng;
		double maxLng = longitude + radiusLng;
		return new double[] { minLat, minLng, maxLat, maxLng };
	}
	
	private static final double A = 6378137.0; 
	private static final double B = 6356725.0;
	
	private static double Radians(double value) {
		return value * Math.PI / 180.0;
	}
	
	public static double[] anotherPointByDistanceAndAngle(double lat, double lng, double distance, double angle) {
		double radAngle = Radians(angle);
		double dx = distance * Math.sin(radAngle);
		double dy = distance * Math.cos(radAngle);
		double radLat = Radians(lat);
		double radLon = Radians(lng);
	
		double ec = B + (A - B) * (90 - lat) / 90;
		double ed = ec * Math.cos(radLat);
		double outLng = (dx / ed + radLon) * 180 / Math.PI;
		double outLat = (dy / ec + radLat) * 180 / Math.PI;
		return new double[]{outLat, outLng};
	}


	public static void main(String[] args) {
		/*
		System.out.println(getDistance(22.1111, 113, 22.11111, 113));
		
		double[] result = getAround(22.11112, 113, 111319);
		
		System.out.println("[" + result[0] + "," + result[1] + "," + result[2] + "," + result[3] + "]");
		
		System.out.println(getDistance(30.6547, 104.0076, (306547 - 187) / 10000.0, (1040076 + 564)) / 10000.0);
		*/
		System.out.println(Arrays.toString(anotherPointByDistanceAndAngle(22.54343,113.92765, 5000, 30)));
		System.out.println(Arrays.toString(anotherPointByDistanceAndAngle(22.54343,113.92765, 9000, 30)));
	}

}
