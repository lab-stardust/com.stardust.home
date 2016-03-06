package com.stardust.home;

import android.graphics.Color;

/**
 * Home Planetarium
 * Asterism class
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Asterism {
	public static final String[][] NAME = {
			{ "Spring Triangle", "春の大三角" },
			{ "Summer Triangle", "夏の大三角" },
			{ "Great Square of Pegasus", "秋の四辺形" },
			{ "Winter Triangle", "冬の大三角" }
	};
	public static final float[][] LINE_RD = {
			{ 177.26491f, 14.572061f, 201.29825f, -11.161322f, 201.29825f, -11.161322f, 213.9153f, 19.182413f, 213.9153f, 19.182413f, 177.26491f, 14.572061f },
			{ 279.23474f, 38.78369f, 297.69586f, 8.868322f, 297.69586f, 8.868322f, 310.35797f, 45.28034f, 310.35797f, 45.28034f, 279.23474f, 38.78369f },
			{ 345.94357f, 28.08279f, 346.1901f, 15.205363f, 346.1901f, 15.205363f, 3.3089583f, 15.183617f, 3.3089583f, 15.183617f, 2.0969126f, 29.090433f, 2.0969126f, 29.090433f, 345.94357f,
					28.08279f },
			{ 88.79294f, 7.407064f, 101.287155f, -16.716118f, 101.287155f, -16.716118f, 114.8255f, 5.224992f, 114.8255f, 5.224992f, 88.79294f, 7.407064f }
	};
	public static final short[] RA = { 197, 295, 354, 101 };
	public static final byte[] DEC = { 7, 30, 22, -10 };
	public static int[] COLOR = { Color.rgb(255, 153, 153), Color.rgb(102, 255, 102), Color.rgb(255, 204, 102), Color.rgb(204, 204, 255) };
	public static short[] X = new short[4];
	public static short[] Y = new short[4];
	public static short[][] LINE_XY = new short[4][];
}
