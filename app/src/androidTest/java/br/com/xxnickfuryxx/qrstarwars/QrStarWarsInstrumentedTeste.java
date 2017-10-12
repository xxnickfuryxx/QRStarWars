package br.com.xxnickfuryxx.qrstarwars;


import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@SmallTest
public class QrStarWarsInstrumentedTeste {

    public static final String TEST_STRING = "This is a string";
    public static final long TEST_LONG = 12345678L;
    private MainActivity mainActivity;

    @Before
    public void init() {
        mainActivity = new MainActivity();
    }

    @Test
    public void testApp() {

        assertTrue(true);
    }
}

