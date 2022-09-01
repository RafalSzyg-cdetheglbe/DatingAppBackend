package com.matcher.matcherApi.event;

import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.service.implementation.RegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
@RequiredArgsConstructor
public class RegistrationCompleteEventListener implements ApplicationListener<RegistrationCompleteEvent> {
    private final RegistrationService registrationService;

    @Override
    public void onApplicationEvent(RegistrationCompleteEvent event) {
        User user = event.getUser();
        String token = UUID.randomUUID().toString();
        registrationService.saveVerificationTokenForUser(token, user);

        String url = event.getAppUrl() + "/verifyRegistration?token=" + token;
        log.info("Click the link to verify your account: {}", url);
    }

}