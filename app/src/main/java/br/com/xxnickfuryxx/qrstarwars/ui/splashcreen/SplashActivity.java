package br.com.xxnickfuryxx.qrstarwars.ui.splashcreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import br.com.xxnickfuryxx.qrstarwars.ui.principal.PrincipalActivity;
import br.com.xxnickfuryxx.qrstarwars.R;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this
                        , PrincipalActivity.class));
            }
        }, 3000);
    }
}
