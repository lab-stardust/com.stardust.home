package com.stardust.home;

import com.stardust.home.R.id;
import com.stardust.home.R.layout;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Home Planetarium
 * Main class : AppWidgetProvider
 * @author Stardust Laboratory
 * @version 1.1
 */
public class Main extends AppWidgetProvider {
	public static byte mode = 2;
	public static byte oid = 0;
	public static long currentTime = 0;
	public static final String IMAGE_BUTTON = "com.stardust.home.IMAGE_BUTTON";
	public static final String UPDATE_BUTTON = "com.stardust.home.UPDATE_BUTTON";
	public static final String LEFT_BUTTON = "com.stardust.home.LEFT_BUTTON";
	public static final String CENTER_BUTTON = "com.stardust.home.CENTER_BUTTON";
	public static final String RIGHT_BUTTON = "com.stardust.home.RIGHT_BUTTON";

	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		RemoteViews rv = new RemoteViews(context.getPackageName(), layout.main);
		rv.setOnClickPendingIntent(id.imageview, buildImageButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.update, buildUpdateButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.left, buildLeftButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.offset, buildCenterButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.right, buildRightButtonPendingIntent(context));
		currentTime = System.currentTimeMillis();
		Data.update(currentTime);
		rv.setTextViewText(id.offset, Time.OFFSET_LABEL[oid]);
		rv.setTextViewText(id.time, Time.getStringTime(currentTime));
		rv.setImageViewBitmap(id.imageview, Data.getBitmap(context.getResources()));
		pushWidgetUpdate(context, rv);
	}

	public static PendingIntent buildImageButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(IMAGE_BUTTON);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static PendingIntent buildUpdateButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(UPDATE_BUTTON);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static PendingIntent buildLeftButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(LEFT_BUTTON);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static PendingIntent buildCenterButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(CENTER_BUTTON);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static PendingIntent buildRightButtonPendingIntent(Context context) {
		Intent intent = new Intent();
		intent.setAction(RIGHT_BUTTON);
		return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	}

	public static void pushWidgetUpdate(Context context, RemoteViews remoteViews) {
		ComponentName cn = new ComponentName(context, Main.class);
		AppWidgetManager awm = AppWidgetManager.getInstance(context);
		awm.updateAppWidget(cn, remoteViews);
	}
}
