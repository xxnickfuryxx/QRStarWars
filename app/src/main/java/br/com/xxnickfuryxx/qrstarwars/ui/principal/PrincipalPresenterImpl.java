package br.com.xxnickfuryxx.qrstarwars.ui.principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.icu.text.SimpleDateFormat;
import android.location.Location;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import com.google.gson.Gson;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import br.com.xxnickfuryxx.qrstarwars.R;
import br.com.xxnickfuryxx.qrstarwars.constants.Constants;
import br.com.xxnickfuryxx.qrstarwars.model.Person;
import br.com.xxnickfuryxx.qrstarwars.ui.details.principal.DetailsPersonActivity;
import br.com.xxnickfuryxx.qrstarwars.ui.principal.adapter.PersonAdapter;
import br.com.xxnickfuryxx.qrstarwars.utils.Utils;

/**
 * Created by xxnickfuryxx on 12/10/2017.
 */

public class PrincipalPresenterImpl implements IPrincipalPresenter {

    private PrincipalActivity mView;
    private PersonAdapter personAdapter;

    public PrincipalPresenterImpl(PrincipalActivity mView){
        this.mView = mView;
    }

    /**
     * Processa o QRCode selecionado.
     * @param url
     */
    @RequiresApi(Build.VERSION_CODES.N)
    @Override
    public void processCaptureQRCode(String url) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try {
            Gson gson = new Gson();
            Person p = gson.fromJson(Utils.readUrl(url), Person.class);
            Location location = Utils.getLocationService(mView);

            if(location != null){
                p.setLatitude(location.getLatitude());
                p.setLongitude(location.getLongitude());
            }
            p.setUrl(url);
            p.setUserCapture(Utils.getUserName(mView));
            p.setDateCapture(sdf.format(new Date()));

            this.savePerson(p);

        }catch(Exception e){
            Log.e(PrincipalActivity.class.getName(), e.getMessage());
        }

    }

    /**
     * Carrega a lista de personagem do Shared preferences
     * @return
     */
    @Override
    public PersonAdapter loadPersonAdapter() {

        SharedPreferences preferences = Utils.getSharedPreferences(mView);
        Set<String> persons = preferences.getStringSet(Constants.LIST_PERSONS, new HashSet<String>());

        personAdapter = new PersonAdapter(persons, mView);
        personAdapter.notifyDataSetChanged();

        return personAdapter;
    }

    @Override
    public Integer sizeAdapterPersons(){
        return personAdapter.getCount();
    }


    /**
     * encaminha para Acitivity de delhes o personagem selecionado
     * @param person
     */
    @Override
    public void sendDetailActivity(Person person) {

        Intent intent = new Intent(mView, DetailsPersonActivity.class);
        intent.setAction(Intent.ACTION_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Constants.PERSON_EXTRA, person);

        mView.startActivity(intent);
    }

    /**
     * Salva o Json capturado no SharedPrefereces local
     * @param personLoad
     */
    private void savePerson(Person personLoad){

        Gson gson = new Gson();

        SharedPreferences preferences = Utils.getSharedPreferences(mView);
        Set<String> persons = preferences.getStringSet(Constants.LIST_PERSONS, new HashSet<String>());
        for(String person: persons){
            Person personParse = gson.fromJson(person, Person.class);
            if(personParse.getName().equals(personLoad.getName())){
                mView.showToastMsg(mView.getString(R.string.activity_principal_erro_exists, personLoad.getName().toUpperCase()));
                return;
            }
        }

        SharedPreferences.Editor editor = Utils.getSharedEditor(mView);
        editor.remove(Constants.LIST_PERSONS);
        editor.commit();

        persons.add(gson.toJson(personLoad));
        editor.putStringSet(Constants.LIST_PERSONS, persons);
        editor.commit();

    }
}
