package com.tasteland.app.Tasteland.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Data
@RequiredArgsConstructor
@NamedQueries({
        @NamedQuery(name = "getUserByUsername", query = "SELECT u FROM User u WHERE u.username=:uName"),
        @NamedQuery(name = "getAllUsers", query = "SELECT u FROM User u"),
        @NamedQuery(name = "getUserById", query = "SELECT u FROM User u WHERE u.id=:uId"),
        @NamedQuery(name = "deleteUserById", query = "DELETE FROM User u WHERE u.id=:uId")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "last_password_reset")
    private Date lastPasswordResetDate;

    @Column(name = "enabled")
    private Boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;
}
