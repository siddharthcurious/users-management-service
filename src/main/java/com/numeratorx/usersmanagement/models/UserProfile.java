package com.numeratorx.usersmanagement.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "users_profile")
public class UserProfile extends DateAudit {
    @Id
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "uuid2")
    private String id;
    private String userId;
    private String firstName;
    private String middleName;
    private String lastName;
    private String about;
}
