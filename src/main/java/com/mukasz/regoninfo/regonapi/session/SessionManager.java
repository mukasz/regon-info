package com.mukasz.regoninfo.regonapi.session;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.function.Supplier;

@Slf4j
public class SessionManager {
    private final Supplier<String> getSessionIdFunction;

    private String sessionID = "";

    public SessionManager(Supplier<String> getSessionIdFunction) {
        this.getSessionIdFunction = getSessionIdFunction;

        refreshSession();
    }

    public String getSessionId() {
        return sessionID;
    }

    @Scheduled(fixedRateString = "${regon.api.session.refresh.miliseconds}")
    private void refreshSession() {
        log.info("Refreshing REGON API session");
        sessionID = getSessionIdFunction.get();
        log.info("New session id: {}", sessionID);
    }
}
