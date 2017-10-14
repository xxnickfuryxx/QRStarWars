package br.com.xxnickfuryxx.qrstarwars.ui.principal;

import br.com.xxnickfuryxx.qrstarwars.model.Person;
import br.com.xxnickfuryxx.qrstarwars.ui.principal.adapter.PersonAdapter;

/**
 * Created by xxnickfuryxx on 12/10/2017.
 */

public interface IPrincipalPresenter {

    void processCaptureQRCode(String url);

    PersonAdapter loadPersonAdapter();

    void sendDetailActivity(Person person);

    Integer sizeAdapterPersons();


}
