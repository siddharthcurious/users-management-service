package com.numeratorx.usersmanagement.models.requests;

import com.numeratorx.usersmanagement.validators.REGEX_PATTERNS;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class LoginRequest {

    @Size(min = 4, max = 255)
    @Pattern(regexp = REGEX_PATTERNS.USERNAME, message = "Username must be aphanumeric underscore")
    private String username;

    private Long mobileNumber;

    @Email(message = "Invalid email id", regexp = REGEX_PATTERNS.EMAIL)
    private String emailId;
    private String password;

}
