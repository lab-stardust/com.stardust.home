package com.stardust.home;

import android.graphics.PointF;

/**
 * Home Planetarium
 * Data class
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Data {
	public static final short R = 250;
	public static final byte moonR = 12;
	public static final double RAD = Math.PI / 180.0;
	public static final double DEG = 180.0 / Math.PI;
	public static final int J2000 = 2451545;

	public static void update(long currentTime) {
		PointF p = Location.point();
		double st = Time.st(currentTime, -p.x);
		double jd = Time.jd(currentTime);
		double jd2000 = jd - J2000;
		calcSun(st, p.y, jd2000);
		calcMoon(st, p.y, jd2000);
		calcPlanet(st, p.y, jd);
		calcStar(st, p.y * RAD);
		calcConstellation(st, p.y * RAD);
		calcAsterism(st, p.y * RAD);
	}

	public static double normAngle(double angle) {
		while (angle < 0) {
			angle += 360;
		}

		while (angle >= 360) {
			angle -= 360;
		}

		return angle;
	}

	public static double getTheTrueObliquityOfTheEcliptic(double t) {
		double ret = 23 + 26 / 60.0 + 21.448 / 3600.0 + 46.8510 / 3600.0 * t + 0.00059 / 3600.0 * t * t;
		ret = ret - 0.001813 / 3600.0 * t * t * t - 0.00256 * Math.cos((1934 * t + 235) * RAD);
		ret = ret - 0.00015 * Math.cos((72002 * t + 201) * RAD);
		ret *= RAD;
		return ret;
	}

	private static void calcSun(double theta, double phi, double jd2000) {
		Sun.setRD(jd2000);
		PointF p = convertHorizontalCoordinateSystem(Sun.ra, Sun.dec * RAD, theta, phi * RAD);
		Sun.az = p.x;
		Sun.alt = p.y;
		p.x = -(float) ((p.x + 90) * RAD);
		p.y = R * (90.0f - p.y) / 90.0f;
		Sun.x = (short) (p.y * Math.cos(p.x) + R);
		Sun.y = (short) (p.y * Math.sin(p.x) + R);
	}

	private static void calcMoon(double theta, double phi, double jd2000) {
		Moon.setRD(jd2000);
		PointF p = convertHorizontalCoordinateSystem(Moon.ra, Moon.dec * RAD, theta, phi * RAD);
		Moon.az = p.x;
		Moon.alt = p.y;
		p.x = -(float) ((p.x + 90) * RAD);
		p.y = R * (90.0f - p.y) / 90.0f;
		Moon.x = (short) (p.y * Math.cos(p.x) + R);
		Moon.y = (short) (p.y * Math.sin(p.x) + R);
	}

	private static void calcPlanet(double theta, double phi, double jd) {
		int i = 0;
		PointF p = null;

		for (i = 0; i < 7; i++) {
			Planet.setRD(i, jd);
			p = convertHorizontalCoordinateSystem(Planet.ra, Planet.dec * RAD, theta, phi * RAD);
			p.x = -(float) ((p.x + 90) * RAD);
			p.y = R * (90.0f - p.y) / 90.0f;
			Planet.x[i] = (short) (p.y * Math.cos(p.x) + R);
			Planet.y[i] = (short) (p.y * Math.sin(p.x) + R);
		}
	}

	private static void calcStar(double theta, double phi) {
		int i = 0;
		double ra = 0;
		double dec = 0;
		int iLen = 6;
		PointF p = null;
		int[] size = new int[6];

		for (i = 0; i < iLen; i++) {
			size[i] = 0;
			Star.px[i] = new short[Star.SIZE[i]];
			Star.py[i] = new short[Star.SIZE[i]];
			Star.color[i] = new byte[Star.SIZE[i]];
		}

		iLen = Star.C.length;

		for (i = 0; i < iLen; i++) {
			ra = 360.0 * (StarRD.RA[i] + 32768) / 65535.0;
			dec = 180.0 * (StarRD.DEC[i] + 32768) / 65535.0 - 90.0;
			p = convertHorizontalCoordinateSystem(ra, dec * RAD, theta, phi);
			p.x = -(float) ((p.x + 90) * RAD);
			p.y = R * (90.0f - p.y) / 90.0f;
			Star.px[Star.MAG[i]][size[Star.MAG[i]]] = (short) (p.y * Math.cos(p.x) + R);
			Star.py[Star.MAG[i]][size[Star.MAG[i]]] = (short) (p.y * Math.sin(p.x) + R);
			Star.color[Star.MAG[i]][size[Star.MAG[i]]] = Star.C[i];
			size[Star.MAG[i]]++;
		}
	}

	private static void calcConstellation(double theta, double phi) {
		int i = 0;
		int j = 0;
		int iLen = 0;
		int jLen = 0;
		PointF p = null;

		iLen = Constellation.LINE_RD.length;

		for (i = 0; i < iLen; i++) {
			p = convertHorizontalCoordinateSystem(Constellation.RA[i], Constellation.DEC[i] * RAD, theta, phi);
			p.x = -(float) ((p.x + 90) * RAD);
			p.y = R * (90.0f - p.y) / 90.0f;
			Constellation.X[i] = (short) (p.y * Math.cos(p.x) + R);
			Constellation.Y[i] = (short) (p.y * Math.sin(p.x) + R);
			jLen = Constellation.LINE_RD[i].length;
			Constellation.LINE_XY[i] = new short[jLen];
			jLen /= 2;

			for (j = 0; j < jLen; j++) {
				p = convertHorizontalCoordinateSystem(Constellation.LINE_RD[i][j * 2], Constellation.LINE_RD[i][j * 2 + 1] * RAD, theta, phi);
				p.x = -(float) ((p.x + 90) * RAD);
				p.y = R * (90.0f - p.y) / 90.0f;
				Constellation.LINE_XY[i][j * 2] = (short) (p.y * Math.cos(p.x) + R);
				Constellation.LINE_XY[i][j * 2 + 1] = (short) (p.y * Math.sin(p.x) + R);
			}
		}
	}

	private static void calcAsterism(double theta, double phi) {
		int i = 0;
		int j = 0;
		int iLen = 0;
		int jLen = 0;
		PointF p = null;

		iLen = Asterism.LINE_RD.length;

		for (i = 0; i < iLen; i++) {
			p = convertHorizontalCoordinateSystem(Asterism.RA[i], Asterism.DEC[i] * RAD, theta, phi);
			p.x = -(float) ((p.x + 90) * RAD);
			p.y = R * (90.0f - p.y) / 90.0f;
			Asterism.X[i] = (short) (p.y * Math.cos(p.x) + R);
			Asterism.Y[i] = (short) (p.y * Math.sin(p.x) + R);
			jLen = Asterism.LINE_RD[i].length;
			Asterism.LINE_XY[i] = new short[jLen];
			jLen /= 2;

			for (j = 0; j < jLen; j++) {
				p = convertHorizontalCoordinateSystem(Asterism.LINE_RD[i][j * 2], Asterism.LINE_RD[i][j * 2 + 1] * RAD, theta, phi);
				p.x = -(float) ((p.x + 90) * RAD);
				p.y = R * (90.0f - p.y) / 90.0f;
				Asterism.LINE_XY[i][j * 2] = (short) (p.y * Math.cos(p.x) + R);
				Asterism.LINE_XY[i][j * 2 + 1] = (short) (p.y * Math.sin(p.x) + R);
			}
		}
	}

	private static PointF convertHorizontalCoordinateSystem(double alpha, double delta, double theta, double phi) {
		PointF ret = new PointF();
		double ret0, ret1, h, sin_h, cos_h, sin_delta, cos_delta, sin_phi, cos_phi, sin_az, cos_az;

		h = theta - alpha;
		h = normAngle(h) * RAD;
		sin_h = Math.sin(h);
		cos_h = Math.cos(h);
		sin_delta = Math.sin(delta);
		cos_delta = Math.cos(delta);
		sin_phi = Math.sin(phi);
		cos_phi = Math.cos(phi);

		ret1 = sin_delta * sin_phi + cos_delta * cos_phi * cos_h;
		ret1 = Math.asin(ret1);

		sin_az = cos_delta * sin_h / Math.cos(ret1);
		cos_az = (sin_delta * cos_phi - cos_delta * sin_phi * cos_h) / Math.cos(ret1);
		ret0 = Math.asin(sin_az);

		if (sin_az < 0 && cos_az >= 0) {
			ret0 *= -1;
		} else if (sin_az < 0 && cos_az < 0) {
			ret0 += Math.PI;
		} else if (sin_az >= 0 && cos_az < 0) {
			ret0 += Math.PI;
		} else if (sin_az >= 0 && cos_az >= 0) {
			ret0 *= -1;
			ret0 += Math.PI * 2;
		}

		ret.x = (float) (ret0 * DEG);
		ret.y = (float) (ret1 * DEG);

		return ret;
	}
}
