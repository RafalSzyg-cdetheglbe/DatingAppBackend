package com.matcher.matcherApi.service.interfaces;

import com.matcher.matcherApi.model.PasswordResetToken;
import com.matcher.matcherApi.model.User;
import com.matcher.matcherApi.model.VerificationToken;


import java.util.Optional;

public interface IRegistrationService {

    public User registerUser(User user);

    public void saveVerificationTokenForUser(String token, User user);

    public String validateVerificationToken(String token);

    public VerificationToken generateNewVerificationToken(String oldToken);



    public String validatePasswordResetToken(String token);

    public Optional<User> getUserByPasswordResetToken(String token);

    public void resetPassword(User user, String newPassword);

    public boolean checkValidOldPassword(User user, String oldPassword);
}
