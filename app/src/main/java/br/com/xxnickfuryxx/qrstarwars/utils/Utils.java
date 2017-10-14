package br.com.xxnickfuryxx.qrstarwars.utils;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import br.com.xxnickfuryxx.qrstarwars.constants.Constants;

/**
 * Created by xxnickfuryxx on 11/10/2017.
 */

public class Utils {

    public static SharedPreferences getSharedPreferences(Context context){

        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        return preferences;

    }

    public static SharedPreferences.Editor getSharedEditor(Context context){

        SharedPreferences preferences = context.getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.edit();

    }

    public static boolean isConnected(Context context){
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        return info == null?false:true;
    }

    public static String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {

            URL url = new URL(urlString);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "Mozilla/4.76");

            reader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        }catch(Exception e){
            Log.e(Utils.class.getName(), e.getMessage());
        } finally {
            if (reader != null)
                reader.close();
        }

        return null;
    }

    public static Location getLocationService(Context context){
        LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            return null;
        }

        LocationListener listener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {

            }

            @Override
            public void onStatusChanged(String s, int i, Bundle bundle) {

            }

            @Override
            public void onProviderEnabled(String s) {

            }

            @Override
            public void onProviderDisabled(String s) {

            }
        };

        lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10, 10, listener);
        Location location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        lm.removeUpdates(listener);

        return location;
    }

    public static String getUserName(Context context){
        Cursor c = context.getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        String name = c.getString(c.getColumnIndex("display_name"));
        c.close();

        return name;
    }

}
