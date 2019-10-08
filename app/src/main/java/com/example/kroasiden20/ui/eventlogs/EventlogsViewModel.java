package com.example.kroasiden20.ui.eventlogs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class EventlogsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public EventlogsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is event logs placeholder fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}