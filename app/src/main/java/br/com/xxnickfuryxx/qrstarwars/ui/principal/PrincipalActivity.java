package br.com.xxnickfuryxx.qrstarwars.ui.principal;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import br.com.xxnickfuryxx.qrstarwars.R;
import br.com.xxnickfuryxx.qrstarwars.model.Person;
import br.com.xxnickfuryxx.qrstarwars.utils.Utils;

public class PrincipalActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private static final int RESULT_QR_OK = 1;
    private static final int MY_PERMISSIONS_REQUEST = 1;
    private List<String> permissions = new ArrayList<String>();
    private FloatingActionButton fab;
    private ListView listPersons;
    private IPrincipalPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);


        this.init();


    }

    /**
     * Metodo inicial da activity
     */
    private void init() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitNetwork().build();
        StrictMode.setThreadPolicy(policy);

        presenter = new PrincipalPresenterImpl(this);

        this.loadPermissions(MY_PERMISSIONS_REQUEST);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(this);
        fab.setSize(FloatingActionButton.SIZE_AUTO);

        listPersons = (ListView)findViewById(R.id.activity_principal_lv_person);
        listPersons.setAdapter(presenter.loadPersonAdapter());
        listPersons.setOnItemClickListener(this);

    }

    @Override
    public void onBackPressed() {
        
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (Utils.isConnected(this)) {
            fab.setVisibility(FloatingActionButton.VISIBLE);
        } else {
            fab.setVisibility(FloatingActionButton.GONE);
        }

        listPersons.setAdapter(presenter.loadPersonAdapter());

        RelativeLayout relList = (RelativeLayout) this.findViewById(R.id.activity_principal_rl_list);
        RelativeLayout relNodata = (RelativeLayout) this.findViewById(R.id.activity_principal_rl_no_data);

        if(presenter.sizeAdapterPersons() > 0){
            relList.setVisibility(RelativeLayout.VISIBLE);
            relNodata.setVisibility(RelativeLayout.GONE);
        }else{
            relList.setVisibility(RelativeLayout.GONE);
            relNodata.setVisibility(RelativeLayout.VISIBLE);
        }

    }

    @Override
    public void onClick(View view) {
        try {

            Intent intent = new Intent("com.google.zxing.client.android.SCAN");
            intent.putExtra("SCAN_MODE", "QR_CODE_MODE");

            startActivityForResult(intent, RESULT_QR_OK);

        } catch (Exception e) {

            Log.e(PrincipalActivity.class.getName(), e.getMessage());

            Uri marketUri = Uri.parse("market://details?id=com.google.zxing.client.android");
            Intent marketIntent = new Intent(Intent.ACTION_VIEW, marketUri);
            startActivity(marketIntent);

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_QR_OK) {

            if (resultCode == RESULT_OK) {
                String url = data.getStringExtra("SCAN_RESULT");

                presenter.processCaptureQRCode(url);

            }
        }
    }

    /**
     * CMostra na tela as permissao ao usuario.
     * @param requestCode
     */
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
            Log.e(PrincipalActivity.class.getName(), e.getMessage());
        }


    }

    public void showToastMsg(String msg) {
        Snackbar snackbar = Snackbar.make(this.findViewById(R.id.activity_rl_root), msg, Snackbar.LENGTH_LONG);
        snackbar.show();

    }



    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Person person = (Person)adapterView.getItemAtPosition(i);
        presenter.sendDetailActivity(person);
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom, android.support.v7.appcompat.R.anim.abc_slide_out_top);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
