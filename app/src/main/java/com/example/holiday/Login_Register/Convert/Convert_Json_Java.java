package com.example.holiday.Login_Register.Convert;

public class Convert_Json_Java {
    private String username;
    private String email;
    private String key;
    private String status;
    private String token;
    private int id;
    private int pk;
    private int[] groups;

    private Profile profile;
    private User user ;


    public User getUser() {     return user;   }

    public void setUser(User user) {     this.user = user;   }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public int getId() {   return id;   }

    public void setId(int id) {   this.id = id;   }

    public int getPk() {     return pk;  }

    public void setPk(int pk) {    this.pk = pk;   }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int[] getGroups() {
        return groups;
    }

    public void setGroups(int[] groups) {
        this.groups = groups;
    }
}
