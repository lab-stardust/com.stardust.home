package com.stardust.home;

import com.stardust.home.R.id;
import com.stardust.home.R.layout;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.widget.RemoteViews;

/**
 * Home Planetarium
 * Receiver class : BroadcastReceiver
 * @author Stardust Laboratory
 * @version 1.0
 */
public class Receiver extends BroadcastReceiver {
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		RemoteViews rv = new RemoteViews(context.getPackageName(), layout.main);
		Resources resources = context.getResources();

		if (Main.currentTime == 0) {
			Main.currentTime = System.currentTimeMillis();
			Main.mode = 2;
		}

		if (action.equals(Main.IMAGE_BUTTON)) {
			if (Main.mode < 2) {
				Main.mode++;
			} else {
				Main.mode = 0;
			}
			Data.update(Main.currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(Main.currentTime));
			rv.setImageViewBitmap(id.imageview, Data.getBitmap(resources));
			updateWidget(context, rv);

		} else if (action.equals(Main.UPDATE_BUTTON)) {
			Main.currentTime = System.currentTimeMillis();
			Data.update(Main.currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(Main.currentTime));
			rv.setImageViewBitmap(id.imageview, Data.getBitmap(resources));
			updateWidget(context, rv);

		} else if (action.equals(Main.LEFT_BUTTON)) {
			Main.currentTime -= Time.OFFSET[Main.oid];
			if (Main.currentTime < 0) {
				Main.currentTime = 0;
			}
			Data.update(Main.currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(Main.currentTime));
			rv.setImageViewBitmap(id.imageview, Data.getBitmap(resources));
			updateWidget(context, rv);

		} else if (action.equals(Main.CENTER_BUTTON)) {
			if (Main.oid < 5) {
				Main.oid++;
			} else {
				Main.oid = 0;
			}
			rv.setTextViewText(id.offset, Time.OFFSET_LABEL[Main.oid]);
			updateWidget(context, rv);

		} else if (action.equals(Main.RIGHT_BUTTON)) {
			Main.currentTime += Time.OFFSET[Main.oid];
			Data.update(Main.currentTime);
			rv.setTextViewText(id.time, Time.getStringTime(Main.currentTime));
			rv.setImageViewBitmap(id.imageview, Data.getBitmap(resources));
			updateWidget(context, rv);
		}
	}

	private void updateWidget(Context context, RemoteViews rv) {
		rv.setOnClickPendingIntent(id.imageview, Main.buildImageButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.update, Main.buildUpdateButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.left, Main.buildLeftButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.offset, Main.buildCenterButtonPendingIntent(context));
		rv.setOnClickPendingIntent(id.right, Main.buildRightButtonPendingIntent(context));
		Main.pushWidgetUpdate(context.getApplicationContext(), rv);
	}
}
