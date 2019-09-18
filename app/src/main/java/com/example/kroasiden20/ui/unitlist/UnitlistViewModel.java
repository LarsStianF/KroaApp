package com.example.kroasiden20.ui.unitlist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UnitlistViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UnitlistViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is a placeholder Unitlist fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}