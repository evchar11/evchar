package cn.evchar.common.util;

public class GeographyUtils {
	private final static double EARTH_R = 6378.137;// 地球半径,km

	// 角度转弧度，即360度转2*Math.PI
	private static double rad(double d) {
		return d * Math.PI / 180.0;
	}

	public static double GetDistance(double lat1, double lng1, double lat2,
			double lng2) {
		double radLat1 = rad(lat1);
		double radLat2 = rad(lat2);
		double a = radLat1 - radLat2;
		double b = rad(lng1) - rad(lng2);

		double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
				+ Math.cos(radLat1) * Math.cos(radLat2)
				* Math.pow(Math.sin(b / 2), 2)));
		s = s * EARTH_R;
		s = Math.round(s * 10000) / 10000;
		return s;
	}
}
