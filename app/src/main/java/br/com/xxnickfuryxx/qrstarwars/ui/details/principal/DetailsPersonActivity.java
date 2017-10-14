package br.com.xxnickfuryxx.qrstarwars.ui.details.principal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import br.com.xxnickfuryxx.qrstarwars.R;
import br.com.xxnickfuryxx.qrstarwars.constants.Constants;
import br.com.xxnickfuryxx.qrstarwars.model.Person;

/**
 * Created by xxnickfuryxx on 14/10/2017.
 */

public class DetailsPersonActivity extends FragmentActivity {

    private Person person;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setContentView(R.layout.activity_details_person);

        person = (Person) this.getIntent().getSerializableExtra(Constants.PERSON_EXTRA);

        ViewPager viewPager = (ViewPager) this.findViewById(R.id.activity_details_person_view_pager);
        viewPager.setAdapter(new FragmentFilmsAdapter(getSupportFragmentManager()));

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private class FragmentFilmsAdapter extends FragmentPagerAdapter{

        public FragmentFilmsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return new FilmsFragment(person, position);
        }

        @Override
        public int getCount() {
            return person.getFilms().size();
        }
    }

}
