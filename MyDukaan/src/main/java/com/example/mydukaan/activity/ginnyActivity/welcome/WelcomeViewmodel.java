package com.example.mydukaan.activity.ginnyActivity.welcome;

import androidx.lifecycle.ViewModel;

public class WelcomeViewmodel extends ViewModel {

    private WelcomeRepo welcomeRepo;

    public WelcomeViewmodel(){
        welcomeRepo = new WelcomeRepo();
    }

}
