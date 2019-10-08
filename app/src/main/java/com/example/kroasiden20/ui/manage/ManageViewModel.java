package com.example.kroasiden20.ui.manage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ManageViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ManageViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is manager placeholder fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}