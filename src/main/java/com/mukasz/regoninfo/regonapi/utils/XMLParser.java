package com.mukasz.regoninfo.regonapi.utils;

import lombok.extern.slf4j.Slf4j;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;

@Slf4j
public class XMLParser  {
    public static <T> T  parseString (String xmlString, Class<T> responseClass) {
        log.info("Parsing xml to: {}", responseClass.toString());
        JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(responseClass);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                return (T) jaxbUnmarshaller.unmarshal(new StringReader(xmlString));
            } catch (JAXBException e) {
                // TODO: Handle this ex
                e.printStackTrace();
                throw new RuntimeException(e);
        }
    }
}
