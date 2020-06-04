package com.mukasz.regoninfo.service;

import com.mukasz.regoninfo.controller.exception.CompanyNotFoundException;
import com.mukasz.regoninfo.model.SubjectDTO;
import com.mukasz.regoninfo.regonapi.client.RegonApiSubjectClient;
import com.mukasz.regoninfo.regonapi.exception.RegonApiNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.mukasz.regoninfo.controller.exception.ExternalServiceException;
import com.mukasz.regoninfo.model.SubjectConverter;
import com.mukasz.regoninfo.regonapi.exception.RegonApiUnrecognizeException;
import com.mukasz.regoninfo.regonapi.model.subject.Subject;

@Slf4j
@Service
public class SubjectService {
    private final RegonApiSubjectClient regonApiSubjectClient;

    public SubjectService(RegonApiSubjectClient regonApiSubjectClient) {
        this.regonApiSubjectClient = regonApiSubjectClient;
    }

    public SubjectDTO getSubjectInfoByNip(String nip) {
        try {
            Subject subject = regonApiSubjectClient
                    .getDaneSzukajPodmiotyByNip(nip);
            return SubjectConverter.convertSubjectToDTO(subject);
        } catch (RegonApiNotFoundException ex) {
            throw new CompanyNotFoundException("Company with provided NIP not found", ex);
        } catch (RegonApiUnrecognizeException ex) {
            throw new ExternalServiceException("Error while accessing REGON API", ex);
        }
    }


}
