package com.ntt.mediatn.Model;

public class FriendsModel {

    int profile;
    String name;


    public FriendsModel(int profile, String name) {
        this.profile = profile;
        this.name = name;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
