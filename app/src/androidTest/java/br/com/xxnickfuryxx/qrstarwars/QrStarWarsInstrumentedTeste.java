package br.com.xxnickfuryxx.qrstarwars;


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
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.app.ActivityCompat;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.xxnickfuryxx.qrstarwars.constants.Constants;
import br.com.xxnickfuryxx.qrstarwars.ui.principal.PrincipalActivity;

@MediumTest
@RunWith(AndroidJUnit4.class)
public class QrStarWarsInstrumentedTeste {


    @Rule
    public ActivityTestRule<PrincipalActivity> mActivityRule = new ActivityTestRule<PrincipalActivity>(
            PrincipalActivity.class);

    @Test
    public void testGetSharedPreferences(){

        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        Assert.assertNotNull(preferences);

    }

    @Test
    public void testGetSharedEditor(){

        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences(Constants.MY_PREFERENCES, Context.MODE_PRIVATE);
        Assert.assertNotNull(preferences.edit());

    }

    @Test
    public void testIsConnected(){
        ConnectivityManager cm =
                (ConnectivityManager) mActivityRule.getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();

        Assert.assertTrue(info == null?false:true);
    }

    @Test
    public void testGetLocationService(){
        LocationManager lm = (LocationManager) mActivityRule.getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(mActivityRule.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(mActivityRule.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            Assert.assertNotNull(null);
        }

        Assert.assertNotNull(lm);
    }

    @Test
    public void testeGetUserName(){
        Cursor c = mActivityRule.getActivity().getContentResolver().query(ContactsContract.Profile.CONTENT_URI, null, null, null, null);
        c.moveToFirst();
        String name = c.getString(c.getColumnIndex("display_name"));
        c.close();

        Assert.assertNotNull(name);
    }
}

