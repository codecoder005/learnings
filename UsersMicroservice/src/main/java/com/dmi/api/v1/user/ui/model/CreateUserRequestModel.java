package com.dmi.api.v1.user.ui.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateUserRequestModel {
    @NotNull(message = "email can not be empty")
    @Email(message = "invalid email Id")
    private String email;

    @NotNull(message = "password cannot be empty")
    @Pattern(regexp = "[a-zA-Z0-9]{5,16}", message = "Password did not met expectation.")
    private String password;

    @NotNull(message = "first name is required")
    @NotBlank
    @Pattern(regexp = "[a-zA-Z ]{2,}", message = "First name should only have alphabets")
    private String firstName;

    private String lastName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
