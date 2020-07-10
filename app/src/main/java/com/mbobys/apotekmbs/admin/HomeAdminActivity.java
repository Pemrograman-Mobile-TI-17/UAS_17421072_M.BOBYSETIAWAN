package com.mbobys.apotekmbs.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.mbobys.apotekmbs.R;
import com.mbobys.apotekmbs.session.PrefSetting;
import com.mbobys.apotekmbs.session.SessionManager;
import com.mbobys.apotekmbs.users.LoginActivity;

public class HomeAdminActivity extends AppCompatActivity {

    CardView cardexit, cardDataObat, cardInputObat, cardProfile;

    SessionManager session;
    SharedPreferences prefs;
    PrefSetting prefSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        prefSetting = new PrefSetting(this);
        prefs = prefSetting.getSharePreference();

        session = new SessionManager(HomeAdminActivity.this);

        prefSetting.isLogin(session, prefs);

        cardexit = (CardView) findViewById(R.id.cardexit);
        cardDataObat = (CardView) findViewById(R.id.cardDataObat);
        cardInputObat = (CardView) findViewById(R.id.cardInputObat);
        cardProfile = (CardView) findViewById(R.id.cardProfile);

        cardexit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setLogin(false);
                session.setSessid(0);
                Intent i = new Intent(HomeAdminActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

        cardDataObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, ActivityDataObat.class);
                startActivity(i);
                finish();
            }
        });

        cardInputObat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, InputDataObat.class);
                startActivity(i);
                finish();
            }
        });

        cardProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeAdminActivity.this, Profile.class);
                startActivity(i);
                finish();
            }
        });

    }
}

