package com.example.lulutong.ui.BottomNavActivity.PersonalCenter;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lulutong.R;

public class PersonalCenterFragment extends Fragment {

    private PersonalCenterViewModel mViewModel;

    public static PersonalCenterFragment newInstance() {
        return new PersonalCenterFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.personal_center_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PersonalCenterViewModel.class);
        // TODO: Use the ViewModel
    }

}