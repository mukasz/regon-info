package com.mukasz.regoninfo.regonapi.client;


import com.mukasz.regoninfo.regonapi.session.SessionManager;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.WebServiceTransportException;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapFaultException;
import org.springframework.ws.soap.SoapHeader;
import org.springframework.ws.soap.SoapHeaderElement;
import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.client.core.SoapActionCallback;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.springframework.ws.transport.context.TransportContext;
import org.springframework.ws.transport.context.TransportContextHolder;
import org.springframework.ws.transport.http.HttpUrlConnection;

import javax.xml.namespace.QName;
import java.io.IOException;


@Slf4j
@Setter
public class AbstractRegonApiClient extends WebServiceGatewaySupport {
    private final String wsaNamespaceURI = "http://www.w3.org/2005/08/addressing";
    private final String wsaPrefix = "wsa";
    private final String wsaToHeader = "To";
    private final String wsaActionHeader = "Action";

    private String endpointURI;
    private SessionManager sessionManager;

    protected Object sendReceive(Object requestObject, final String action)
            throws SoapFaultException, SoapFaultClientException, WebServiceTransportException,
            IllegalStateException{

        final WebServiceTemplate wst = getWebServiceTemplate();

        SoapActionCallback requestCallback = new SoapActionCallback(action) {
            public void doWithMessage(WebServiceMessage message) {
                SaajSoapMessage soapMessage = (SaajSoapMessage) message;
                SoapHeader soapHeader = soapMessage.getSoapHeader();

                QName wsaToQName = new QName(wsaNamespaceURI, wsaToHeader, wsaPrefix);
                SoapHeaderElement wsaTo = soapHeader.addHeaderElement(wsaToQName);
                wsaTo.setText(endpointURI);

                QName wsaActionQName = new QName(wsaNamespaceURI, wsaActionHeader, wsaPrefix);
                SoapHeaderElement wsaAction = soapHeader.addHeaderElement(wsaActionQName);
                wsaAction.setText(action);

                TransportContext context = TransportContextHolder.getTransportContext();
                HttpUrlConnection connection = (HttpUrlConnection) context.getConnection();
                try {
                    String sid = sessionManager.getSessionId();
                    if(!sid.isBlank()) {
                        log.info("Add sid Http header to request: {}", sid);
                        connection.addRequestHeader("sid", sid);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

        return wst.marshalSendAndReceive(endpointURI, requestObject, requestCallback);
    }
}
