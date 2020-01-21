package com.george.mynotification;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        String message = getIntent().getStringExtra("message");
        if (savedInstanceState == null) {
            getIntent().getAction().equals("chacha");
            if (message.equals("accept")) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NotificationActivityFragment fragment = new NotificationActivityFragment();
                fragmentTransaction.add(R.id.fragment_layout,
                        fragment, "view_profile_fragment");
                fragmentTransaction.commit();
            }else if (message.equals("viewProfile")){
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                NotificationSuccessFragment fragment = new NotificationSuccessFragment();
                fragmentTransaction.add(R.id.fragment_layout,
                        fragment, "success_fragment");
                fragmentTransaction.commit();
            }
        }
    }

}
