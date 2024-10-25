package com.ntt.mediatn.Model;

public class ActivityModel {

    int profile;
    String activity, time;

    public ActivityModel(int profile, String activity, String time) {
        this.profile = profile;
        this.activity = activity;
        this.time = time;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public String getActivity() {
        return activity;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
