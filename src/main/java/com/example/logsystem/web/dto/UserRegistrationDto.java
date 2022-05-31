package com.example.logsystem.web.dto;

public class UserRegistrationDto {
    private String  fisrstname;
    private String lastname;
    private String email;
    private String password;

   public UserRegistrationDto(){

    }

    public UserRegistrationDto(String fisrstname, String lastname, String email, String password) {
        super();
        this.fisrstname = fisrstname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }


    public String getFisrstname() {
        return fisrstname;
    }

    public void setFisrstname(String fisrstname) {
        this.fisrstname = fisrstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

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
}
