package com.example.lulutong.ui.BottomNavActivity.MyBusiness;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MyBusinessViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MyBusinessViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is dashboard fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}