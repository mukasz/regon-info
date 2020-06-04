package com.mukasz.regoninfo.regonapi.client;

import com.gus.regon.api.DaneSzukajPodmioty;
import com.gus.regon.api.DaneSzukajPodmiotyResponse;
import com.gus.regon.api.ObjectFactory;
import com.gus.regon.api.ParametryWyszukiwania;
import com.mukasz.regoninfo.regonapi.utils.ApiResponseHandler;
import com.mukasz.regoninfo.regonapi.utils.XMLParser;
import lombok.extern.slf4j.Slf4j;
import com.mukasz.regoninfo.regonapi.exception.RegonApiUnrecognizeException;
import com.mukasz.regoninfo.regonapi.model.subject.Subject;
import com.mukasz.regoninfo.regonapi.model.subject.SubjectResponse;

@Slf4j
public class RegonApiSubjectClient extends AbstractRegonApiClient {
    private final String DANE_SZUKAJ_PODMIOTY_ACTION = "http://CIS/BIR/PUBL/2014/07/IUslugaBIRzewnPubl/DaneSzukajPodmioty";

    public Subject getDaneSzukajPodmiotyByNip(String nip) {
        ObjectFactory factory = new ObjectFactory();
        DaneSzukajPodmioty daneSzukajPodmioty = new DaneSzukajPodmioty();
        ParametryWyszukiwania searchParams = new ParametryWyszukiwania();
        searchParams.setNip(
                factory.createParametryWyszukiwaniaNip(nip)
        );
        daneSzukajPodmioty.setPParametryWyszukiwania(
                factory.createDaneSzukajPodmiotyPParametryWyszukiwania(searchParams)
        );
        log.info("Requesting for subject with nip: {}", nip);
        String strResponse = ((DaneSzukajPodmiotyResponse) sendReceive(daneSzukajPodmioty, DANE_SZUKAJ_PODMIOTY_ACTION))
                .getDaneSzukajPodmiotyResult()
                .getValue();
        if (strResponse.isBlank()) {
            throw new RegonApiUnrecognizeException("REGON API respods whith empty body.");
        }
        SubjectResponse subjectResponse = XMLParser.parseString(strResponse, SubjectResponse.class);
        Subject subject = subjectResponse.getDane();
        ApiResponseHandler.handleErrors(subject);
        return subject;
    }
}
