package com.george.mynotification;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotificationSuccessFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotificationSuccessFragment extends Fragment {

    public NotificationSuccessFragment() {
    }

    public static NotificationSuccessFragment newInstance(String param1, String param2) {
        NotificationSuccessFragment fragment = new NotificationSuccessFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notification_success, container, false);
    }

}
