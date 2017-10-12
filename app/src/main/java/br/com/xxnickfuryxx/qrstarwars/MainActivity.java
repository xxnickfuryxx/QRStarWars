package br.com.xxnickfuryxx.qrstarwars;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v4.util.ArraySet;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import br.com.xxnickfuryxx.qrstarwars.model.Person;
import br.com.xxnickfuryxx.qrstarwars.utils.Utils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int RESULT_QR_OK = 1;
    private static final int MY_PERMISSIONS_REQUEST = 1;
    private List<String> permissions = new ArrayList<String>();
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.init();


    }

    private void init() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);

        this.loadPermissions(MY_PERMISSIONS_REQUEST);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setSize(FloatingActionButton.SIZE_AUTO);


    }


    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isConnected(this)) {
            fab.setVisibility(FloatingActionButton.VISIBLE);
        } else {
            fab.setVisibility(FloatingActionButton.GONE);
        }

    }

    @Override
    public void onClick(View view) {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE"); // "PRODUCT_MODE for bar codes

            startActivityForResult(intent, RESULT_QR_OK);

        } catch (Exception e) {

            Log.e(MainActivity.class.getName(), e.getMessage());

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_QR_OK) {

            if (resultCode == RESULT_OK) {
                String url = data.getStringExtra("SCAN_RESULT");

                try {
                    Gson gson = new Gson();
                    Person p = gson.fromJson(Utils.readUrl(url), Person.class);
                    Location location = Utils.getLocationService(this);

                    if(location != null){
                        p.setLatitude(location.getLatitude());
                        p.setLongitude(location.getLongitude());
                        p.setUserCapture(Utils.getUserName(this));

                    }

                }catch(Exception e){
                    Log.e(MainActivity.class.getName(), e.getMessage());
                }

            }
        }
    }



    private void loadPermissions(int requestCode) {

        try {

            PackageInfo info = getPackageManager().getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            if (info.requestedPermissions != null) {

                for (String p : info.requestedPermissions) {
                    if (ContextCompat.checkSelfPermission(this,p)!= PackageManager.PERMISSION_GRANTED) {
                        if (!ActivityCompat.shouldShowRequestPermissionRationale(this, p)) {
                            permissions.add(p);
                        }
                    }
                }

                ActivityCompat.requestPermissions(this, permissions.toArray(new String[permissions.size()]), requestCode);
            }
        } catch (Exception e) {
            Log.e(MainActivity.class.getName(), e.getMessage());
        }


    }
}
