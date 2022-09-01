package com.matcher.matcherApi.service.implementation;

import com.matcher.matcherApi.model.*;
import com.matcher.matcherApi.repository.PasswordResetTokenRepository;
import com.matcher.matcherApi.repository.UserRepository;
import com.matcher.matcherApi.repository.VerificationTokenRepository;
import com.matcher.matcherApi.service.interfaces.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationService implements IRegistrationService {
    private final UserRepository userRepository;

    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository verificationTokenRepository;

    @Transactional
    public User registerUser(User user) {

         User userByEmail = userRepository.findUserByEmail(user.getEmail());

        /*
        if (!isValidEmailAddress(user.getEmail())) {
            throw new IllegalStateException("illegal");
        }
*/

        UserRole userRole = new UserRole();
        userRole.setId(1L);
        GenderInterest genderInterest=new GenderInterest();
        genderInterest.setId(1L);
        user.setGenderInterest(genderInterest);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setGender("niezidentyfikowany");

        user.setUserRole(userRole);
        user.setDeleted(false);

        try {
            this.userRepository.save(user);
        }catch (Exception e){
            throw new IllegalStateException("illegal");
        }


        return user;
    }
/*
    public static boolean isValidEmailAddress(String email) {
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            return false;
        }
        return true;
    }
*/

    public void saveVerificationTokenForUser(String token, User user) {
        //VerificationToken verificationToken = new VerificationToken(user, token);
       // verificationTokenRepository.save(verificationToken);
    }

    public String validateVerificationToken(String token) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(token);
        if (verificationToken == null)
            return "invalid";
        User user = verificationToken.getUser();
        Calendar calendar = Calendar.getInstance();
        if (verificationToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            verificationTokenRepository.delete(verificationToken);
            return "expired";
        }

        userRepository.save(user);
        return "valid";
    }

    public VerificationToken generateNewVerificationToken(String oldToken) {
        VerificationToken verificationToken = verificationTokenRepository.findByToken(oldToken);
        verificationToken.setToken(UUID.randomUUID().toString());
        verificationTokenRepository.save(verificationToken);
        return verificationToken;
    }

    public PasswordResetToken generateTokenForResetPassword(Password password) {
        User user = userRepository.findUserByEmail(password.getEmail());
        if (user != null) {
            String token = UUID.randomUUID().toString();
            PasswordResetToken passwordResetToken = new PasswordResetToken(user, token);
            passwordResetTokenRepository.save(passwordResetToken);
            return passwordResetToken;
        }
        return null;
    }

    public String validatePasswordResetToken(String token) {
        PasswordResetToken passwordResetToken = passwordResetTokenRepository.findByToken(token);
        if (passwordResetToken == null)
            return "invalid";

        Calendar calendar = Calendar.getInstance();
        if (passwordResetToken.getExpirationTime().getTime() - calendar.getTime().getTime() <= 0) {
            passwordResetTokenRepository.delete(passwordResetToken);
            return "expired";
        }
        return "valid";
    }

    public Optional<User> getUserByPasswordResetToken(String token) {
        return Optional.ofNullable(passwordResetTokenRepository.findByToken(token).getUser());
    }

    public void resetPassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    public boolean checkValidOldPassword(User user, String oldPassword) {
        return passwordEncoder.matches(oldPassword, user.getPassword());
    }
}
