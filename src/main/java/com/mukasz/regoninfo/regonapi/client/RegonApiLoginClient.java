package com.mukasz.regoninfo.regonapi.client;

import com.gus.regon.api.ObjectFactory;
import com.gus.regon.api.Zaloguj;
import com.gus.regon.api.ZalogujResponse;

import javax.xml.bind.JAXBElement;

public class RegonApiLoginClient extends AbstractRegonApiClient {
    public final String ACTION_ZALOGUJ = "http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/Zaloguj";

    public String login(String apiKey) {
        ObjectFactory factory = new ObjectFactory();

        Zaloguj zaloguj = new Zaloguj();
        JAXBElement<String> apiKeyJAXB = factory.createZalogujPKluczUzytkownika(apiKey);
        zaloguj.setPKluczUzytkownika(apiKeyJAXB);

        ZalogujResponse zalogujResponse = (ZalogujResponse) sendReceive(
                zaloguj,
                ACTION_ZALOGUJ
        );

        return zalogujResponse
                .getZalogujResult()
                .getValue();
    }
}
