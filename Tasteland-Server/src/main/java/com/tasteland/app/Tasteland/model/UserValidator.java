package com.tasteland.app.Tasteland.model;

import com.tasteland.app.Tasteland.utils.FieldMatch;
import com.tasteland.app.Tasteland.utils.ValidEmail;
import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@FieldMatch.List({
        @FieldMatch(first = "password", second = "matchingPassword", message = "The password fields must match")
})
@Data
public class UserValidator {

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String userName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String password;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String matchingPassword;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String firstName;

    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String lastName;

    @NotNull(message = "is required")
    private String pictureUrl;

    @NotNull(message = "is required")
    private Date dateOfBirth;

    @NotNull(message = "is required")
    private Gender gender;

    @NotNull(message = "is required")
    private String country;

    @ValidEmail
    @NotNull(message = "is required")
    @Size(min = 1, message = "is required")
    private String email;

}
