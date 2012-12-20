package com.orleonsoft.android.baseproject.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.orleonsoft.android.baseproject.R;


/**
 *File: Util.java
 *Autor: Yesid Lazaro Mayoriano
 */



public class Util {

	public static void hideSoftKeyboard(Context context) {
		InputMethodManager imm = (InputMethodManager) context
				.getSystemService(Activity.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
	}

	/* Permit to make toast notification */
	public static void toast(Context context, String message, boolean islong)
	{
		Toast.makeText(context, message, (islong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT).show();
	}

	public static String md5(String string) {
		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
			digest.update(string.getBytes(), 0, string.length());
			String hash = new BigInteger(1, digest.digest()).toString(16);
			return hash;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Este metodo retorna un String con la fecha actual en un formato
	 * especifico
	 * 
	 * @return String con fecha en el formato especificado
	 */
	public static String dateNowToString(String dateFormat) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		return sdf.format(cal.getTime());
	}

	
	/**
	 * Display a simple alert dialog with the given text and title.
	 * 
	 * @param context
	 *            Android context in which the dialog should be displayed
	 * @param title
	 *            Alert dialog title
	 * @param text
	 *            Alert dialog message
	 */
	public static void showAlert(Context context, int titleId, int msgId) {
		Builder alertBuilder = new Builder(context);
		alertBuilder.setTitle(titleId);
		alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
		alertBuilder.setMessage(msgId);
		alertBuilder.setPositiveButton("Aceptar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				dialog.cancel();
			}
		});
		alertBuilder.create().show();
	}

	public static void showNoInternetConnectionDialog(final Activity activity) {
		Builder alertBuilder = new Builder(activity);
		alertBuilder.setTitle("Internet ");
		alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
		alertBuilder.setMessage("No esta conectado a internet ");
		alertBuilder.setPositiveButton("Aceptar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		alertBuilder.setNegativeButton("Configurar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				activity.startActivityForResult(new Intent(
						android.provider.Settings.ACTION_WIFI_SETTINGS), 0);
			}
		});
		alertBuilder.create().show();
	}
	
	public static void showNoGPSEnableDialog(final Activity activity) {
		Builder alertBuilder = new Builder(activity);
		alertBuilder.setTitle("GPS");
		alertBuilder.setIcon(android.R.drawable.ic_dialog_alert);
		alertBuilder.setMessage("Debe activar el gps para continuar");
		alertBuilder.setPositiveButton("Aceptar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});

		alertBuilder.setNegativeButton("Configurar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				activity.startActivityForResult(
						new Intent(
								android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
						0);
			}
		});
		alertBuilder.create().show();
	}
	
	public static void launchShareTask(Context context, String title, String content)
	{
		Intent intent = new Intent(android.content.Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_SUBJECT, title);
		intent.putExtra(Intent.EXTRA_TEXT, content
				+ " - via MyApp");
		context.startActivity(Intent.createChooser(intent, title));
	}
	public static void launchBrowserTask(Context context, String link)
	{
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
		context.startActivity(intent);	
	
	}
	
	/* Permit to make status bar notification */
	@SuppressWarnings("deprecation")
	public static void statusBarNotification(Context context, String title, String message, Intent intent)
	{
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);

		int icon = R.drawable.ic_launcher;
		long when = System.currentTimeMillis();

		Notification notification = new Notification(icon, message, when);

		CharSequence contentTitle = title;
		CharSequence contentText = message;
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificationManager.notify(1, notification);
	}

	@TargetApi(16)
	public static void statusBarNotificationForJellyBeans(Context context, String title, String message, Intent intent)
	{
		String ns = Context.NOTIFICATION_SERVICE;
		NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(ns);

		int icon = R.drawable.ic_launcher;

		Notification.Builder builder = new Notification.Builder(context);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent, 0);

		builder.addAction(icon, title, contentIntent);
		builder.setContentTitle(title);
		builder.setContentText(message);
		mNotificationManager.notify(1, builder.build());
	}

	
	

}
