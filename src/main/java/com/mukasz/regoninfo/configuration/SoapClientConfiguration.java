package com.mukasz.regoninfo.configuration;

import com.mukasz.regoninfo.regonapi.client.RegonApiLoginClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.soap.SoapVersion;
import org.springframework.ws.soap.saaj.SaajSoapMessageFactory;
import com.mukasz.regoninfo.regonapi.client.RegonApiSubjectClient;
import com.mukasz.regoninfo.regonapi.session.SessionManager;

import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPException;

@Configuration
public class SoapClientConfiguration {
    @Value("${regon.api.default.uri}")
    private String regonApiDefaultUri;

    @Value("${regon.api.namespace}")
    private String regonApiNamespace;

    @Value("${regon.api.key}")
    private String apiKey;

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.gus.regon.api");
        return jaxb2Marshaller;
    }

    @Bean
    public SaajSoapMessageFactory  messageFactor() throws SOAPException {
        MessageFactory messageFactory = MessageFactory.newInstance(SOAPConstants.SOAP_1_2_PROTOCOL);
        SaajSoapMessageFactory saajSoapMessageFactory = new SaajSoapMessageFactory(messageFactory);
        saajSoapMessageFactory.setSoapVersion(SoapVersion.SOAP_12);

        return saajSoapMessageFactory;
    }

    @Bean
    public SessionManager defaultSessionManager(RegonApiLoginClient loginClient) {
        return new SessionManager(() -> loginClient.login(apiKey));
    }

    @Bean
    public RegonApiLoginClient getRegonApiLoginClient(Jaxb2Marshaller jaxb2Marshaller, SaajSoapMessageFactory messageFactory) {
        RegonApiLoginClient regonApiLoginClient = new RegonApiLoginClient();
        regonApiLoginClient.setEndpointURI(regonApiDefaultUri);
        regonApiLoginClient.setMarshaller(jaxb2Marshaller);
        regonApiLoginClient.setUnmarshaller(jaxb2Marshaller);
        regonApiLoginClient
                .getWebServiceTemplate()
                .setMessageFactory(messageFactory);
        regonApiLoginClient.setSessionManager(new SessionManager(() -> ""));
        return regonApiLoginClient;
    }

    @Bean
    public RegonApiSubjectClient getRegonApiSubjectClient(Jaxb2Marshaller jaxb2Marshaller, SaajSoapMessageFactory messageFactory, SessionManager sessionManager) {
        RegonApiSubjectClient regonApiSubjectClient = new RegonApiSubjectClient();
        regonApiSubjectClient.setEndpointURI(regonApiDefaultUri);
        regonApiSubjectClient.setMarshaller(jaxb2Marshaller);
        regonApiSubjectClient.setUnmarshaller(jaxb2Marshaller);
        regonApiSubjectClient
                .getWebServiceTemplate()
                .setMessageFactory(messageFactory);
        regonApiSubjectClient.setSessionManager(sessionManager);
        return regonApiSubjectClient;
    }
}
