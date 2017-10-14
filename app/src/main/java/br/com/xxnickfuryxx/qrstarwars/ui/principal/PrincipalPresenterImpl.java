package br.com.xxnickfuryxx.qrstarwars.ui.principal;

import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.util.Log;

import com.google.gson.Gson;

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


    @Override
    public void processCaptureQRCode(String url) {
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

            this.savePerson(p);

        }catch(Exception e){
            Log.e(PrincipalActivity.class.getName(), e.getMessage());
        }

    }

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

    @Override
    public void sendDetailActivity(Person person) {

        Intent intent = new Intent(mView, DetailsPersonActivity.class);
        intent.setAction(Intent.ACTION_DEFAULT);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(Constants.PERSON_EXTRA, person);

        mView.startActivity(intent);
    }

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

        persons.add(gson.toJson(personLoad));
        SharedPreferences.Editor editor = Utils.getSharedEditor(mView);
        editor.putStringSet(Constants.LIST_PERSONS, persons);
        editor.commit();

    }
}
