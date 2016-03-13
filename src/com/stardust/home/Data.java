package com.stardust.home;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.PointF;
import android.graphics.Typeface;

/**
 * Home Planetarium
 * Data class
 * @author Stardust Laboratory
 * @version 1.1
 */
public class Data {
	public static final short R = 250;
	public static final byte moonR = 12;
	public static final double RAD = Math.PI / 180.0;
	public static final double DEG = 180.0 / Math.PI;
	public static final int J2000 = 2451545;

	public static Bitmap getBitmap(Resources resources) {
		Bitmap ret = Bitmap.createBitmap(Data.R * 2, Data.R * 2, Bitmap.Config.ARGB_4444);
		Canvas c = new Canvas(ret);
		Paint p = new Paint();
		p.setAntiAlias(true);
		p.setTextSize(20);
		p.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.NORMAL));
		Bitmap moonLight = BitmapFactory.decodeResource(resources, Moon.image);
		moonLight = Bitmap.createScaledBitmap(moonLight, Data.moonR * 2, Data.moonR * 2, false);
		c.drawBitmap(moonLight, Data.moonR + 10, 50, p);

		Path path = new Path();
		path.addCircle(Data.R, Data.R, Data.R, Direction.CCW);
		c.clipPath(path);

		p.setStyle(Paint.Style.FILL);
		p.setColor(Sun.getSkyColor());
		c.drawRect(0, 0, Data.R * 2, Data.R * 2, p);

		int i = 0;
		int j = 0;
		int iLen = 0;
		int jLen = 0;

		if (Main.mode > 0) {
			p.setColor(Color.rgb(204, 204, 204));
			iLen = Constellation.LINE_XY.length;
			p.setStyle(Paint.Style.STROKE);
			p.setStrokeWidth(1);

			for (i = 0; i < iLen; i++) {
				jLen = Constellation.LINE_XY[i].length;
				jLen /= 4;

				for (j = 0; j < jLen; j++) {
					if (Math.abs(Constellation.LINE_XY[i][j * 4 + 2] - Constellation.LINE_XY[i][j * 4]) < Data.R
							&& Math.abs(Constellation.LINE_XY[i][j * 4 + 3] - Constellation.LINE_XY[i][j * 4 + 1]) < Data.R) {
						c.drawLine(Constellation.LINE_XY[i][j * 4], Constellation.LINE_XY[i][j * 4 + 1], Constellation.LINE_XY[i][j * 4 + 2], Constellation.LINE_XY[i][j * 4 + 3], p);
					}
				}
			}

			iLen = Asterism.LINE_XY.length;

			for (i = 0; i < iLen; i++) {
				jLen = Asterism.LINE_XY[i].length;
				jLen /= 4;

				for (j = 0; j < jLen; j++) {
					if (Math.abs(Asterism.LINE_XY[i][j * 4 + 2] - Asterism.LINE_XY[i][j * 4]) < Data.R
							&& Math.abs(Asterism.LINE_XY[i][j * 4 + 3] - Asterism.LINE_XY[i][j * 4 + 1]) < Data.R) {
						p.setColor(Asterism.COLOR[i]);
						c.drawLine(Asterism.LINE_XY[i][j * 4], Asterism.LINE_XY[i][j * 4 + 1], Asterism.LINE_XY[i][j * 4 + 2], Asterism.LINE_XY[i][j * 4 + 3], p);
					}
				}
			}
		}

		p.setStrokeWidth(1.0f);
		p.setStyle(Paint.Style.FILL);

		if (Sun.alt < -26) {
			iLen = Star.color[5].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[5][i]]);
				c.drawPoint(Star.px[5][i], Star.py[5][i], p);
			}
		}

		if (Sun.alt < -22) {
			iLen = Star.color[4].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[4][i]]);
				c.drawCircle(Star.px[4][i], Star.py[4][i], 0.8f, p);
			}
		}

		if (Sun.alt < -18) {
			iLen = Star.color[3].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[3][i]]);
				c.drawCircle(Star.px[3][i], Star.py[3][i], 1.0f, p);
			}
		}

		if (Sun.alt < -14) {
			iLen = Star.color[2].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[2][i]]);
				c.drawCircle(Star.px[2][i], Star.py[2][i], 1.4f, p);
			}
		}

		if (Sun.alt < -10) {
			iLen = Star.color[1].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[1][i]]);
				c.drawCircle(Star.px[1][i], Star.py[1][i], 1.9f, p);
			}
		}

		if (Sun.alt < -6) {
			iLen = Star.color[0].length;

			for (i = 0; i < iLen; i++) {
				p.setColor(Star.RGB[Star.color[0][i]]);
				c.drawCircle(Star.px[0][i], Star.py[0][i], 2.5f, p);
			}
		}

		p.setColor(Color.rgb(174, 134, 90));
		c.drawCircle(Planet.x[0], Planet.y[0], 2.5f, p);
		p.setColor(Color.rgb(195, 175, 163));
		c.drawCircle(Planet.x[1], Planet.y[1], 3.0f, p);
		p.setColor(Color.rgb(204, 113, 76));
		c.drawCircle(Planet.x[2], Planet.y[2], 3.0f, p);
		p.setColor(Color.rgb(236, 209, 159));
		c.drawCircle(Planet.x[3], Planet.y[3], 3.5f, p);
		p.setColor(Color.rgb(147, 110, 71));
		c.drawCircle(Planet.x[4], Planet.y[4], 3.5f, p);
		p.setColor(Color.rgb(174, 236, 236));
		c.drawCircle(Planet.x[5], Planet.y[5], 2.5f, p);
		p.setColor(Color.rgb(65, 113, 216));
		c.drawCircle(Planet.x[6], Planet.y[6], 2.5f, p);

		int r, g, b;

		if (Sun.alt < 10 && Sun.alt >= 0) {
			r = 255;
			g = (int) Math.floor(102 + 153 * Sun.alt / 10.0);
			b = (int) Math.floor(51 + 204 * Sun.alt / 10.0);

		} else if (Sun.alt < 0 && Sun.alt > -20) {
			r = (int) Math.floor(255 * (Sun.alt + 20) / 20.0);
			g = (int) Math.floor(102 * (Sun.alt + 20) / 20.0);
			b = (int) Math.floor(51 * (Sun.alt + 20) / 20.0);

			if (r < 51) {
				r = 51;
			}

			if (g < 51) {
				g = 51;
			}

			if (b < 51) {
				b = 51;
			}

		} else if (Sun.alt <= -20) {
			r = 51;
			g = 51;
			b = 51;

		} else {
			r = 255;
			g = 255;
			b = 255;
		}

		p.setStyle(Paint.Style.FILL);
		p.setColor(Color.rgb(r, g, b));
		c.drawCircle(Sun.x, Sun.y, Data.moonR / 2, p);
		p.setColor(Color.rgb(255, 255, 255));
		moonLight = Bitmap.createScaledBitmap(moonLight, Data.moonR, Data.moonR, false);
		c.drawBitmap(moonLight, Moon.x - Data.moonR / 2, Moon.y - Data.moonR / 2, p);

		if (Main.mode > 1) {
			p.setColor(Color.rgb(255, 255, 255));
			p.setTextSize(14);
			iLen = Constellation.NAME.length;

			if (Location.isjp()) {
				j = 1;
			} else {
				j = 0;
			}

			for (i = 0; i < iLen; i++) {
				c.drawText(Constellation.NAME[i][j], Constellation.X[i] - p.measureText(Constellation.NAME[i][j]) / 2, Constellation.Y[i], p);
			}

			iLen = Asterism.NAME.length;
			p.setTextSize(16);
			p.setTypeface(Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD));

			for (i = 0; i < iLen; i++) {
				p.setColor(Asterism.COLOR[i]);
				c.drawText(Asterism.NAME[i][j], Asterism.X[i] - p.measureText(Asterism.NAME[i][j]) / 2, Asterism.Y[i], p);
			}

			p.setTextSize(18);
			p.setColor(Color.rgb(255, 255, 0));
			iLen = 7;

			if (Location.isjp()) {
				j = 1;
			} else {
				j = 0;
			}

			for (i = 0; i < iLen; i++) {
				c.drawText(Planet.NAME[j][i], Planet.x[i] + 5, Planet.y[i] - 5, p);
			}

			p.setColor(Color.rgb(255, 0, 0));
			p.setTextSize(20);
			c.drawText("N", Data.R - 8, 20, p);
			c.drawText("W", Data.R * 2 - 22, Data.R + 8, p);
			c.drawText("S", Data.R - 8, Data.R * 2 - 5, p);
			c.drawText("E", 5, Data.R + 8, p);
		}

		return ret;
	}

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
