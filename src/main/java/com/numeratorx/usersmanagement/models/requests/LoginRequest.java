package com.numeratorx.usersmanagement.models.requests;

import com.numeratorx.usersmanagement.validators.REGEX_PATTERNS;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Data
public class LoginRequest {

    @Size(min = 4, max = 255)
    @Pattern(regexp = REGEX_PATTERNS._USERNAME)
    private Optional<String> username;

    private Optional<Long> mobileNumber;

    @Email(message = "Invalid email id", regexp = REGEX_PATTERNS._EMAIL)
    private Optional<String> emailId;
    private String password;

    public boolean isValidRequest(){
        return username.isPresent() || mobileNumber.isPresent() || emailId.isPresent();
    }
}
