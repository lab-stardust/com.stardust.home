package com.stardust.home;

import com.stardust.home.R.drawable;

/**
 * Home Planetarium
 * Moon class
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Moon {
	public static float ra;
	public static float dec;
	public static float az;
	public static float alt;
	public static short x;
	public static short y;
	public static int image;
	private static final double[][] MOON_L = { { 218.3161, 4812.67881, 6.2887, 134.961, 4771.9886 }, { 1.274, 100.738, 4133.3536 }, { 0.6583, 235.7, 8905.3422 }, { 0.2136, 269.926, 9543.9773 },
			{ 0.1856, 177.525, 359.9905 }, { 0.1143, 6.546, 9664.0404 }, { 0.0588, 214.22, 638.635 }, { 0.0572, 103.21, 3773.363 }, { 0.0533, 10.66, 13677.331 }, { 0.0459, 238.18, 8545.352 },
			{ 0.041, 137.43, 4411.998 }, { 0.0348, 117.84, 4452.671 }, { 0.0305, 312.49, 5131.979 }, { 0.0153, 130.84, 758.698 }, { 0.0125, 141.51, 14436.029 }, { 0.011, 231.59, 4892.052 },
			{ 0.0107, 336.44, 13038.696 }, { 0.01, 44.89, 14315.966 }, { 0.0085, 201.5, 8266.71 }, { 0.0079, 278.2, 4493.34 }, { 0.0068, 53.2, 9265.33 }, { 0.0052, 197.2, 319.32 },
			{ 0.005, 295.4, 4812.66 }, { 0.0048, 235, 19.34 }, { 0.004, 13.2, 13317.34 }, { 0.004, 145.6, 18449.32 }, { 0.004, 119.5, 1.33 }, { 0.0039, 111.3, 17810.68 }, { 0.0037, 349.1, 5410.62 },
			{ 0.0027, 272.5, 9183.99 }, { 0.0026, 107.2, 13797.39 }, { 0.0024, 211.9, 988.63 }, { 0.0024, 252.8, 9224.66 }, { 0.0022, 240.6, 8185.36 }, { 0.0021, 87.5, 9903.97 },
			{ 0.0021, 175.1, 719.98 }, { 0.0021, 105.6, 3413.37 }, { 0.002, 55, 19.34 }, { 0.0018, 4.1, 4013.29 }, { 0.0016, 242.2, 18569.38 }, { 0.0012, 339, 12678.71 }, { 0.0011, 276.5, 19208.02 },
			{ 0.0009, 218, 8586 }, { 0.0008, 188, 14037.3 }, { 0.0008, 204, 7906.7 }, { 0.0007, 140, 4052 }, { 0.0007, 275, 4853.3 }, { 0.0007, 216, 278.6 }, { 0.0006, 128, 1118.7 },
			{ 0.0005, 247, 22582.7 }, { 0.0005, 181, 19088 }, { 0.0005, 114, 17450.7 }, { 0.0005, 332, 5091.3 }, { 0.0004, 313, 398.7 }, { 0.0004, 278, 120.1 }, { 0.0004, 71, 9584.7 },
			{ 0.0004, 20, 720 }, { 0.0003, 83, 3814 }, { 0.0003, 66, 3494.7 }, { 0.0003, 147, 18089.3 }, { 0.0003, 311, 5492 }, { 0.0003, 161, 40.7 }, { 0.0003, 280, 23221.3 },
			{ 0.004, 119.5, 1.33 }, { 0.002, 55, 19.34 }, { 0.0006, 71, 0.2 }, { 0.0006, 54, 19.3 } };
	private static final double[][] MOON_B = { { 5.1282, 93.273, 4832.0202 }, { 0.2806, 228.235, 9604.0088 }, { 0.2777, 138.311, 60.0316 }, { 0.1732, 142.427, 4073.322 },
			{ 0.0554, 194.01, 8965.374 }, { 0.0463, 172.55, 698.667 }, { 0.0326, 328.96, 13737.362 }, { 0.0172, 3.18, 14375.997 }, { 0.0093, 277.4, 8845.31 }, { 0.0088, 176.7, 4711.96 },
			{ 0.0082, 144.9, 3713.33 }, { 0.0043, 307.6, 5470.66 }, { 0.0042, 103.9, 18509.35 }, { 0.0034, 319.9, 4433.31 }, { 0.0025, 196.5, 8605.38 }, { 0.0022, 331.4, 13377.37 },
			{ 0.0021, 170.1, 1058.66 }, { 0.0019, 230.7, 9244.02 }, { 0.0018, 243.3, 8206.68 }, { 0.0018, 270.8, 5192.01 }, { 0.0017, 99.8, 14496.06 }, { 0.0016, 135.7, 420.02 },
			{ 0.0015, 211.1, 9284.69 }, { 0.0015, 45.8, 9964 }, { 0.0014, 219.2, 299.96 }, { 0.0013, 95.8, 4472.03 }, { 0.0013, 155.4, 379.35 }, { 0.0012, 38.4, 4812.68 }, { 0.0012, 148.2, 4851.36 },
			{ 0.0011, 138.3, 19147.99 }, { 0.001, 18, 12978.66 }, { 0.0008, 70, 17870.7 }, { 0.0008, 326, 9724.1 }, { 0.0007, 294, 13098.7 }, { 0.0006, 224, 5590.7 }, { 0.0006, 52, 13617.3 },
			{ 0.0005, 280, 8485.3 }, { 0.0005, 239, 4193.4 }, { 0.0004, 311, 9483.9 }, { 0.0004, 238, 23281.3 }, { 0.0004, 81, 10242.6 }, { 0.0004, 13, 9325.4 }, { 0.0004, 147, 14097.4 },
			{ 0.0003, 205, 22642.7 }, { 0.0003, 107, 18149.4 }, { 0.0003, 146, 3353.3 }, { 0.0003, 234, 19268 }, { 0.0267, 234.95, 19.341 }, { 0.0043, 322.1, 19.36 }, { 0.004, 119.5, 1.33 },
			{ 0.0026, 55, 19.34 }, { 0.0005, 307, 19.4 } };

	public static void setRD(double t) {
		float lambda = getLambda(t / 365.25);
		double psi = lambda * Data.RAD;
		double beta = getBeta(t / 365.25);
		double epsilon = Data.getTheTrueObliquityOfTheEcliptic(t / 36525.0);
		double delta = Math.asin(Math.cos(beta) * Math.sin(psi) * Math.sin(epsilon) + Math.sin(beta) * Math.cos(epsilon));
		double sinAlpha = (-Math.sin(beta) * Math.sin(epsilon) + Math.cos(beta) * Math.sin(psi) * Math.cos(epsilon)) / Math.cos(delta);
		double cosAlpha = Math.cos(beta) * Math.cos(psi) / Math.cos(delta);
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
		float d_lambda = lambda - Sun.lambda;

		if (d_lambda < 0) {
			d_lambda += 360;
		}

		setImage(d_lambda);
	}

	private static float getLambda(double t) {
		double ret, a;
		int i;
		a = 0;

		for (i = 63; i < 67; i++) {
			a += MOON_L[i][0] * Math.sin((MOON_L[i][1] + MOON_L[i][2] * t) * Data.RAD);
		}

		ret = MOON_L[0][0] + MOON_L[0][1] * t;
		ret += MOON_L[0][2] * Math.sin((MOON_L[0][3] + MOON_L[0][4] * t + a) * Data.RAD);

		for (i = 1; i < 63; i++) {
			ret += MOON_L[i][0] * Math.sin((MOON_L[i][1] + MOON_L[i][2] * t) * Data.RAD);
		}

		ret += 0.0057;
		return (float) Data.normAngle(ret);
	}

	private static double getBeta(double t) {
		double ret, b;
		int i;

		b = 0;

		for (i = 47; i < 52; i++) {
			b += MOON_B[i][0] * Math.sin((MOON_B[i][1] + MOON_B[i][2] * t) * Data.RAD);
		}

		ret = MOON_B[0][0] * Math.sin((MOON_B[0][1] + MOON_B[0][2] * t + b) * Data.RAD);

		for (i = 1; i < 47; i++) {
			ret += MOON_B[i][0] * Math.sin((MOON_B[i][1] + MOON_B[i][2] * t) * Data.RAD);
		}

		ret *= Data.RAD;

		return ret;
	}

	private static void setImage(float phase) {
		byte age = (byte) Math.floor(phase / 360.0 * 27.0);

		switch (age) {
		case 0:
			image = drawable.mp0;
			break;
		case 1:
			image = drawable.mp1;
			break;
		case 2:
			image = drawable.mp2;
			break;
		case 3:
			image = drawable.mp3;
			break;
		case 4:
			image = drawable.mp4;
			break;
		case 5:
			image = drawable.mp5;
			break;
		case 6:
			image = drawable.mp6;
			break;
		case 7:
			image = drawable.mp7;
			break;
		case 8:
			image = drawable.mp8;
			break;
		case 9:
			image = drawable.mp9;
			break;
		case 10:
			image = drawable.mp10;
			break;
		case 11:
			image = drawable.mp11;
			break;
		case 12:
			image = drawable.mp12;
			break;
		case 13:
			image = drawable.mp13;
			break;
		case 14:
			image = drawable.mp14;
			break;
		case 15:
			image = drawable.mp15;
			break;
		case 16:
			image = drawable.mp16;
			break;
		case 17:
			image = drawable.mp17;
			break;
		case 18:
			image = drawable.mp18;
			break;
		case 19:
			image = drawable.mp19;
			break;
		case 20:
			image = drawable.mp20;
			break;
		case 21:
			image = drawable.mp21;
			break;
		case 22:
			image = drawable.mp22;
			break;
		case 23:
			image = drawable.mp23;
			break;
		case 24:
			image = drawable.mp24;
			break;
		case 25:
			image = drawable.mp25;
			break;
		case 26:
			image = drawable.mp26;
			break;
		default:
			image = drawable.mp0;
			break;
		}
	}
}
