package br.com.xxnickfuryxx.qrstarwars;


import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.xxnickfuryxx.qrstarwars.ui.principal.PrincipalActivity;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class QrStarWarsInstrumentedTeste {

    @Rule
    public ActivityTestRule<PrincipalActivity> mActivityRule = new ActivityTestRule<PrincipalActivity>(
            PrincipalActivity.class);


    @Test
    public void testApp() {



    }
}

