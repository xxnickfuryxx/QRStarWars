package br.com.xxnickfuryxx.qrstarwars;

import android.database.Cursor;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONTokener;
import org.junit.Before;
import org.junit.Test;

import br.com.xxnickfuryxx.qrstarwars.model.Person;
import br.com.xxnickfuryxx.qrstarwars.utils.Utils;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {



    @Test
    public void testeURL() throws Exception {

        String s1 = Utils.readUrl("https://swapi.co/api/people/1/?format=json");
        String s2 = "{\"name\":\"Luke Skywalker\",\"height\":\"172\",\"mass\":\"77\",\"hair_color\":\"blond\",\"skin_color\":\"fair\",\"eye_color\":\"blue\",\"birth_year\":\"19BBY\",\"gender\":\"male\",\"homeworld\":\"https://swapi.co/api/planets/1/\",\"films\":[\"https://swapi.co/api/films/2/\",\"https://swapi.co/api/films/6/\",\"https://swapi.co/api/films/3/\",\"https://swapi.co/api/films/1/\",\"https://swapi.co/api/films/7/\"],\"species\":[\"https://swapi.co/api/species/1/\"],\"vehicles\":[\"https://swapi.co/api/vehicles/14/\",\"https://swapi.co/api/vehicles/30/\"],\"starships\":[\"https://swapi.co/api/starships/12/\",\"https://swapi.co/api/starships/22/\"],\"created\":\"2014-12-09T13:50:51.644000Z\",\"edited\":\"2014-12-20T21:17:56.891000Z\",\"url\":\"https://swapi.co/api/people/1/\"}";

        assertEquals(s1, s2);

    }

}