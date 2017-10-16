package br.com.xxnickfuryxx.qrstarwars.ui.details.principal;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import br.com.xxnickfuryxx.qrstarwars.R;
import br.com.xxnickfuryxx.qrstarwars.constants.Constants;
import br.com.xxnickfuryxx.qrstarwars.model.Person;

/**
 * Created by xxnickfuryxx on 14/10/2017.
 */

public class DetailsPersonActivity extends AppCompatActivity implements View.OnClickListener {

    private Person person;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_details_person);

        this.init();
        this.setupPersonData();

    }

    /**
     * Metodo inicial da activity
     */
    private void init(){
        person = (Person) this.getIntent().getSerializableExtra(Constants.PERSON_EXTRA);
        getSupportActionBar().setTitle(person.getName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ViewPager viewPager = (ViewPager) this.findViewById(R.id.activity_details_person_view_pager);
        viewPager.setAdapter(new FragmentFilmsAdapter(getSupportFragmentManager()));

        RelativeLayout rlMovies = (RelativeLayout) this.findViewById(R.id.activity_details_person_rl_movies);
        rlMovies.setOnClickListener(this);

        RelativeLayout rlInfo = (RelativeLayout)this.findViewById(R.id.activity_details_person_rl_info);
        rlInfo.setOnClickListener(this);

        TextView link = (TextView)this.findViewById(R.id.activity_details_person_link);
        link.setOnClickListener(this);

    }

    /**
     * Popula o personagem selecionado para exibicao na tela
     */
    private void setupPersonData(){

        TextView name = (TextView)this.findViewById(R.id.activity_details_person_name);
        TextView height = (TextView)this.findViewById(R.id.activity_details_person_height);
        TextView mass = (TextView)this.findViewById(R.id.activity_details_person_mass);
        TextView hairColor = (TextView)this.findViewById(R.id.activity_details_person_hair_color);
        TextView skinColor = (TextView)this.findViewById(R.id.activity_details_person_skin_color);
        TextView eyeColor = (TextView)this.findViewById(R.id.activity_details_person_eye_color);
        TextView birthYear = (TextView)this.findViewById(R.id.activity_details_person_birth_year);
        TextView gender = (TextView)this.findViewById(R.id.activity_details_person_gender);
        TextView dateCapture = (TextView)this.findViewById(R.id.activity_details_person_date_capture);

        name.setText(person.getName());
        height.setText(person.getHeight());
        mass.setText(person.getMass());
        hairColor.setText(person.getHairColor());
        skinColor.setText(person.getSkinColor());
        eyeColor.setText(person.getEyeColor());
        birthYear.setText(person.getBirthYear());
        gender.setText(person.getGender());
        dateCapture.setText(person.getDateCapture());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            onBackPressed();
            overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_top, android.support.v7.appcompat.R.anim.abc_slide_out_bottom);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_top, android.support.v7.appcompat.R.anim.abc_slide_out_bottom);
    }

    @Override
    public void onClick(View view) {
        RelativeLayout rlPageFilms = (RelativeLayout)this.findViewById(R.id.activity_details_person_page_films);
        LinearLayout llPageFilms = (LinearLayout)this.findViewById(R.id.activity_details_person_ll_info);
        ImageView infoArrow = (ImageView)this.findViewById(R.id.activity_details_person_info_arrow);
        ImageView moviesArrow = (ImageView)this.findViewById(R.id.activity_details_person_arrow);

        if(view.getId() == R.id.activity_details_person_rl_movies){

            if(rlPageFilms.getVisibility() == RelativeLayout.GONE){
                rlPageFilms.setVisibility(RelativeLayout.VISIBLE);
                llPageFilms.setVisibility(LinearLayout.GONE);
                moviesArrow.setImageDrawable(this.getDrawable(R.drawable.ic_keyboard_arrow_up_white_48dp));
            }else{
                rlPageFilms.setVisibility(RelativeLayout.GONE);
                moviesArrow.setImageDrawable(this.getDrawable(R.drawable.ic_keyboard_arrow_down_white_48dp));
            }
        } else if(view.getId() == R.id.activity_details_person_rl_info){

            if(llPageFilms.getVisibility() == LinearLayout.GONE){
                rlPageFilms.setVisibility(RelativeLayout.GONE);
                llPageFilms.setVisibility(LinearLayout.VISIBLE);
                infoArrow.setImageDrawable(this.getDrawable(R.drawable.ic_keyboard_arrow_up_white_48dp));
            }else{
                llPageFilms.setVisibility(RelativeLayout.GONE);
                infoArrow.setImageDrawable(this.getDrawable(R.drawable.ic_keyboard_arrow_down_white_48dp));
            }
        } else if(view.getId() == R.id.activity_details_person_link){

            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:0,0?q="+person.getLatitude()+
                    ","+person.getLongitude()+"(QR Code '"+person.getName()+"') ")));
        }

    }

    /**
     * Subclasse do frangmento do PageView
     */
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

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }
}
