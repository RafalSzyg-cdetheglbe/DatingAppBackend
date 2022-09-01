package com.matcher.matcherApi.controller;

import com.matcher.matcherApi.event.RegistrationCompleteEvent;
import com.matcher.matcherApi.model.Password;
import com.matcher.matcherApi.model.PasswordResetToken;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.model.VerificationToken;
import com.matcher.matcherApi.service.implementation.RegistrationService;
import com.matcher.matcherApi.service.implementation.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping()
@RequiredArgsConstructor
public class RegistrationController {
    private final RegistrationService registrationService;
    private final ApplicationEventPublisher publisher;
    private final UserService userService;

    @PostMapping("/register")
    public void registerNewUser(@RequestBody User user, final HttpServletRequest request) {
        user = this.registrationService.registerUser(user);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
    }

    @GetMapping("/verifyRegistration")
    public String verifyRegistration(@RequestParam String token) {
        if (registrationService.validateVerificationToken(token).equalsIgnoreCase("valid"))
            return "user verifies successfully";

        return "Bad user";
    }

    @GetMapping("/resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest request) {
        VerificationToken verificationToken = registrationService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(request), verificationToken);
        return "verification Link Sent";
    }

    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        log.info("Click the link to verify your account: {}", url);
    }

    @PostMapping("/resetPassword")
    public String resetPassword(@RequestBody Password password, HttpServletRequest request) {
        PasswordResetToken passwordResetToken = registrationService.generateTokenForResetPassword(password);
        if (passwordResetToken != null)
            return passwordResetTokenMail(passwordResetToken.getUser(), applicationUrl(request),
                    passwordResetToken.getToken());
        return null;
    }

    @PostMapping("/savePassword")
    public String savePassword(@RequestParam String token, @RequestBody Password password) {

        String result = registrationService.validatePasswordResetToken(token);
        if (!result.equalsIgnoreCase("valid"))
            return "Invalid Token";

        Optional<User> user = registrationService.getUserByPasswordResetToken(token);
        if (user.isPresent()) {
            registrationService.resetPassword(user.get(), password.getNewPassword());
            return "Password Reset Successfully";
        } else {
            return "Invalid Token";
        }
    }

    private String passwordResetTokenMail(User user, String applicationUrl, String token) {
        String url = applicationUrl + "/savePassword?token=" + token;
        log.info("Click the link to reset your password: {}", url);
        return url;
    }



    @PostMapping("/users")
    public void addUser(@RequestBody User user) {
        //filtrowanie
        this.registrationService.registerUser(user);
    }


    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
