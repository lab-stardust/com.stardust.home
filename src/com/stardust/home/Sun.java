package com.stardust.home;

import android.graphics.Color;

/**
 * Home Planetarium
 * Sun class
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Sun {
	public static float lambda;
	public static float ra;
	public static float dec;
	public static float az;
	public static float alt;
	public static short x;
	public static short y;
	private static final double[][] SUN = { { 280.4603, 360.00769 }, { 1.9146, -0.00005, 357.538, 359.991 }, { 0.0200, 355.05, 719.981 }, { 0.0048, 234.95, 19.341 }, { 0.0020, 247.1, 329.64 },
			{ 0.0018, 297.8, 4452.67 }, { 0.0018, 251.3, 0.2 }, { 0.0015, 343.2, 450.37 }, { 0.0013, 81.4, 225.18 }, { 0.0008, 132.5, 659.29 }, { 0.0007, 153.3, 90.38 }, { 0.0007, 206.8, 30.35 },
			{ 0.0006, 29.8, 337.18 }, { 0.0005, 207.4, 1.5 }, { 0.0005, 291.2, 22.81 }, { 0.0004, 234.9, 315.56 }, { 0.0004, 157.3, 299.3 }, { 0.0004, 21.1, 720.02 }, { 0.0003, 352.5, 1079.97 },
			{ 0.0003, 329.7, 44.43 }, { 0.007256, -0.0000002, 267.54, 359.991 }, { 0.000091, 265.1, 719.98 }, { 0.00003, 90.0 }, { 0.000013, 27.8, 4452.67 }, { 0.000007, 254, 450.4 }, { 0.000007, 156, 329.6 } };

	public static void setRD(double t) {
		setLambda(t / 365.25);
		double psi = lambda * Data.RAD;
		double epsilon = Data.getTheTrueObliquityOfTheEcliptic(t / 36525.0);
		double delta = Math.asin(Math.sin(psi) * Math.sin(epsilon));
		double sinAlpha = Math.sin(psi) * Math.cos(epsilon) / Math.cos(delta);
		double cosAlpha = Math.cos(psi) / Math.cos(delta);
		double alpha = 0;

		if (sinAlpha >= 0) {
			alpha = Math.acos(cosAlpha);

		} else {
			alpha = Math.asin(sinAlpha);

			if (cosAlpha >= 0) {
				alpha = alpha + Math.PI * 2;

			} else {
				alpha = Math.PI - alpha;
			}
		}

		ra = (float) (alpha * Data.DEG);
		dec = (float) (delta * Data.DEG);
	}

	private static void setLambda(double t) {
		int i = 0;
		double ret = SUN[0][0] + SUN[0][1] * t;
		ret += (SUN[1][0] + SUN[1][1] * t) * Math.sin((SUN[1][2] + SUN[1][3] * t) * Data.RAD);

		for (i = 2; i < 20; i++) {
			ret += SUN[i][0] * Math.sin((SUN[i][1] + SUN[i][2] * t) * Data.RAD);
		}

		lambda = (float) Data.normAngle(ret);
	}

	public static int getSkyColor() {
		int r = 0;
		int g = 0;
		int b = 0;

		if (alt >= -18 && alt <= 5) {
			r = (int) Math.floor(102 * (18 + alt) / 23.0);
			g = (int) Math.floor(153 * (18 + alt) / 23.0);
			b = (int) Math.floor(204 * (18 + alt) / 23.0);

		} else if (alt > 5) {
			r = 102;
			g = 153;
			b = 204;
		}

		return Color.rgb(r, g, b);
	}
}
