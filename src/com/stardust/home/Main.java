package com.stardust.home;

import com.stardust.home.R.id;
import com.stardust.home.R.layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Typeface;
import android.widget.RemoteViews;

/**
 * Home Planetarium
 * Main class : AppWidgetProvider
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Main extends AppWidgetProvider {
	private static byte mode;
	private static byte oid;
	private static long currentTime;
	private static final String IMAGE_BUTTON = "com.stardust.home.IMAGE_BUTTON";
	private static final String UPDATE_BUTTON = "com.stardust.home.UPDATE_BUTTON";
	private static final String LEFT_BUTTON = "com.stardust.home.LEFT_BUTTON";
	private static final String CENTER_BUTTON = "com.stardust.home.CENTER_BUTTON";
	private static final String RIGHT_BUTTON = "com.stardust.home.RIGHT_BUTTON";

	public void onDeleted(Context context, int[] appWidgetIds) {
		super.onDeleted(context, appWidgetIds);
	}

	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

	public void onEnabled(Context context) {
		super.onEnabled(context);
		mode = 2;
		oid = 0;
	}

	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
		AppWidgetManager awm = AppWidgetManager.getInstance(context);
		int awid = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, -1);
		RemoteViews rv = new RemoteViews(context.getPackageName(), layout.main);
		String action = intent.getAction();
		Resources resources = context.getResources();

		if (currentTime == 0) {
			currentTime = System.currentTimeMillis();
			mode = 2;
		}

		if (action.equals(IMAGE_BUTTON)) {
			if (mode < 2) {
				mode++;
			} else {
				mode = 0;
			}

			Data.update(currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(currentTime));
			rv.setImageViewBitmap(id.imageview, getBitmap(resources));

			try {
				awm.updateAppWidget(awid, rv);
			} catch (IllegalArgumentException e) {

			}

		} else if (action.equals(UPDATE_BUTTON)) {
			currentTime = System.currentTimeMillis();
			Data.update(currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(currentTime));
			rv.setImageViewBitmap(id.imageview, getBitmap(resources));

			try {
				awm.updateAppWidget(awid, rv);
			} catch (IllegalArgumentException e) {

			}

		} else if (action.equals(LEFT_BUTTON)) {
			currentTime -= Time.OFFSET[oid];

			if (currentTime < 0) {
				currentTime = 0;
			}

			Data.update(currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(currentTime));
			rv.setImageViewBitmap(id.imageview, getBitmap(resources));

			try {
				awm.updateAppWidget(awid, rv);
			} catch (IllegalArgumentException e) {

			}

		} else if (action.equals(CENTER_BUTTON)) {
			if (oid < 5) {
				oid++;
			} else {
				oid = 0;
			}

			rv.setTextViewText(id.offset, Time.OFFSET_LABEL[oid]);

			try {
				awm.updateAppWidget(awid, rv);
			} catch (IllegalArgumentException e) {

			}

		} else if (action.equals(RIGHT_BUTTON)) {
			currentTime += Time.OFFSET[oid];
			Data.update(currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(currentTime));
			rv.setImageViewBitmap(id.imageview, getBitmap(resources));

			try {
				awm.updateAppWidget(awid, rv);
			} catch (IllegalArgumentException e) {

			}
		}
	}

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		RemoteViews rv = new RemoteViews(context.getPackageName(), layout.main);
		Resources resources = context.getResources();
		currentTime = System.currentTimeMillis();
		Data.update(currentTime);
		rv.setTextViewText(id.offset, Time.OFFSET_LABEL[oid]);
		rv.setTextViewText(id.time, Time.getStringTime(currentTime));
		rv.setImageViewBitmap(id.imageview, getBitmap(resources));
		Intent intentImageButton = new Intent(context, Main.class);
		intentImageButton.setAction(IMAGE_BUTTON);
		Intent intentUpdateButton = new Intent(context, Main.class);
		intentUpdateButton.setAction(UPDATE_BUTTON);
		Intent intentLeftButton = new Intent(context, Main.class);
		intentLeftButton.setAction(LEFT_BUTTON);
		Intent intentCenterButton = new Intent(context, Main.class);
		intentCenterButton.setAction(CENTER_BUTTON);
		Intent intentRightButton = new Intent(context, Main.class);
		intentRightButton.setAction(RIGHT_BUTTON);

		for (int appWidgetId : appWidgetIds) {
			intentImageButton.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setOnClickPendingIntent(id.imageview, PendingIntent.getBroadcast(context, appWidgetId, intentImageButton, PendingIntent.FLAG_UPDATE_CURRENT));
			intentUpdateButton.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setOnClickPendingIntent(id.update, PendingIntent.getBroadcast(context, appWidgetId, intentUpdateButton, PendingIntent.FLAG_UPDATE_CURRENT));
			intentLeftButton.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setOnClickPendingIntent(id.left, PendingIntent.getBroadcast(context, appWidgetId, intentLeftButton, PendingIntent.FLAG_UPDATE_CURRENT));
			intentCenterButton.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setOnClickPendingIntent(id.offset, PendingIntent.getBroadcast(context, appWidgetId, intentCenterButton, PendingIntent.FLAG_UPDATE_CURRENT));
			intentRightButton.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
			rv.setOnClickPendingIntent(id.right, PendingIntent.getBroadcast(context, appWidgetId, intentRightButton, PendingIntent.FLAG_UPDATE_CURRENT));
			appWidgetManager.updateAppWidget(appWidgetId, rv);
		}
	}

	private Bitmap getBitmap(Resources resources) {
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

		if (mode > 0) {
			p.setColor(Color.rgb(102, 102, 102));
			iLen = Constellation.LINE_XY.length;
			p.setStyle(Paint.Style.STROKE);
			p.setStrokeWidth(1.5f);

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

		if (mode > 1) {
			p.setColor(Color.rgb(204, 204, 204));
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
}
