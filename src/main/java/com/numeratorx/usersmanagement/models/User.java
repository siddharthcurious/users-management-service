package com.numeratorx.usersmanagement.models;

import com.numeratorx.usersmanagement.constants.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends DateAudit {

    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;

    private String username;
    private String emailId;
    private long mobileNumber;
    private Gender gender;
    private String dateOfBirth;
    private String password;
    private String roles;
    private boolean isActive;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;

}
