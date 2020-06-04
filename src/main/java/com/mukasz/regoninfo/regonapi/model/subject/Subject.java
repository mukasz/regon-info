package com.mukasz.regoninfo.regonapi.model.subject;

import com.mukasz.regoninfo.regonapi.model.RegonApiObject;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "dane")
public class Subject extends RegonApiObject {
    @XmlElement private String Regon;
    @XmlElement private String Nip;
    @XmlElement private String StatusNip;
    @XmlElement private String Nazwa;
    @XmlElement private String Wojewodztwo;
    @XmlElement private String Powiat;
    @XmlElement private String Gmina;
    @XmlElement private String Miejscowosc;
    @XmlElement private String KodPocztowy;
    @XmlElement private String Ulica;
    @XmlElement private String NrNieruchomosci;
    @XmlElement private String NrLokalu;
    @XmlElement private String Typ;
    @XmlElement private String SilosID;
    @XmlElement private String DataZakonczeniaDzialalnosci;
    @XmlElement private String MiejscowoscPoczty;
}