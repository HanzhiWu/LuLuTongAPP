package com.example.lulutong.ui.BottomNavActivity.BusinessHall;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class BusinessHallViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public BusinessHallViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is notifications fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}