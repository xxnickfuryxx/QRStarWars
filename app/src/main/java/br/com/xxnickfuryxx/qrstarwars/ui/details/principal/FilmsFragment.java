package br.com.xxnickfuryxx.qrstarwars.ui.details.principal;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import br.com.xxnickfuryxx.qrstarwars.R;
import br.com.xxnickfuryxx.qrstarwars.model.Person;

/**
 * Created by xxnickfuryxx on 14/10/2017.
 */

public class FilmsFragment extends Fragment implements View.OnClickListener{

    private Person person;
    private int position;
    private String urlFilm;

    @SuppressLint("ValidFragment")
    public FilmsFragment(Person person, int position) {

        this.person = person;
        this.position = position;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup view = (ViewGroup)inflater.inflate(R.layout.fragment_page_films, container, false);
        this.init(view);

        return view;
    }

    private void init(ViewGroup view) {
        ImageView imageFilm = (ImageView)view.findViewById(R.id.fragment_page_films_image);
        try {
            imageFilm.setImageDrawable(ContextCompat.getDrawable(view.getContext(), getIdImageFilm()));

        } catch (Exception e) {
            Log.e(FilmsFragment.class.getName(), e.getMessage());
        }

        imageFilm.setOnClickListener(this);

    }

    private int getIdImageFilm() throws Exception {

        String film = person.getFilms().get(position);
        char charIdFilm = film.charAt((film.length()-2));

        switch (charIdFilm){
            case '1':
                urlFilm = this.getString(R.string.film_4);
                return R.drawable.film_4;
            case '2':
                urlFilm = this.getString(R.string.film_5);
                return R.drawable.film_5;
            case '3':
                urlFilm = this.getString(R.string.film_6);
                return R.drawable.film_6;
            case '4':
                urlFilm = this.getString(R.string.film_1);
                return R.drawable.film_1;
            case '5':
                urlFilm = this.getString(R.string.film_2);
                return R.drawable.film_2;
            case '6':
                urlFilm = this.getString(R.string.film_3);
                return R.drawable.film_3;
            case '7':
                urlFilm = this.getString(R.string.film_7);
                return R.drawable.film_7;
            case '8':
                urlFilm = this.getString(R.string.film_8);
                return R.drawable.film_8;
            default:
                urlFilm = null;
                throw new Exception(getString(R.string.films_fragment_film_no_related));

        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fragment_page_films_image){
            this.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(urlFilm)));
            this.getActivity().overridePendingTransition(android.support.v7.appcompat.R.anim.abc_slide_in_bottom, android.support.v7.appcompat.R.anim.abc_slide_out_top);

        }
    }
}
