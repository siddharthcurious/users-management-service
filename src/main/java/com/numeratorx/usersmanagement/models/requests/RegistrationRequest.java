package com.numeratorx.usersmanagement.models.requests;

import com.numeratorx.usersmanagement.constants.Gender;
import com.numeratorx.usersmanagement.validators.REGEX_PATTERNS;
import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class RegistrationRequest {

    @Size(min = 3, max = 255)
    private String firstName;
    @Size(min = 3, max = 255)
    private String lastName;
    private Gender gender;
    @Pattern(regexp = REGEX_PATTERNS.DATE, message = "Date must be in valid, format 2022-03-31")
    private String dateOfBirth;

    @Size(min = 4, max = 255)
    @Pattern(regexp = REGEX_PATTERNS.USERNAME, message = "Username must be aphanumeric underscore")
    private String username;

    private Long mobileNumber;

    @Pattern(regexp = REGEX_PATTERNS.EMAIL, message = "Invalid email id")
    private String emailId;
    private String password;

}
