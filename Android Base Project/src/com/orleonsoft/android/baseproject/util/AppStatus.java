package com.orleonsoft.android.baseproject.util;

import android.content.Context;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppStatus {

	/**
	 * Este metodo sirve para saber si hay conexion a internet
	 * 
	 * @param context
	 * @return true si hay conexion o false si no hay conexion
	 */
	public static boolean isNetworkAvailable(Context context) {

		ConnectivityManager connectivity = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		if (connectivity == null) {
			return false;
		} else {
			NetworkInfo[] info = connectivity.getAllNetworkInfo();
			if (info != null) {
				for (int i = 0; i < info.length; i++) {
					if (info[i].getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		}
		return false;
	}
	

	/**
	 * Este metodo se usa para saber si un location provider en particular esta
	 * habilitado
	 * 
	 * @param provider
	 *            Nombre del provider a verificar si esta habilitado o no
	 * @param context
	 *            Contexto de la aplicacion
	 * @return true si el el provider esta habilitado
	 */
	public static boolean isLocationProviderEnable(String provider,
			Context context) {
		LocationManager locationManager = (LocationManager) context
				.getSystemService(Context.LOCATION_SERVICE);
		return locationManager.isProviderEnabled(provider);
	}
}
