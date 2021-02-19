package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Dashboard extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    Animation uptodown, downtoup,Fadein,FadeOut;
    @BindView(R.id.MenuDashboar)
    ConstraintLayout Dashboar;
    @BindView(R.id.Buttom)
    CardView Buttom;
    @BindView(R.id.email)
    TextView Email;
    @BindView(R.id.nama)
    TextView Nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__dashboard);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(Menu_Dashboard.this);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.to_left);
        Dashboar.setAnimation(uptodown);
        Email.setText(sharedPrefManager.getSPEmail());
        Nama.setText(sharedPrefManager.getSPNama());
    }
    public void Diagnosa(View v){
        Intent intent=new Intent(Menu_Dashboard.this,Menu_Monitoring.class);
        startActivity(intent);
        finish();
    }
    public void Profile(View v){
        Intent intent=new Intent(Menu_Dashboard.this,Menu_Profile.class);
        startActivity(intent);
        finish();
    }
    public void About(View v){
        Intent intent=new Intent(Menu_Dashboard.this,Menu_Setting.class);
        startActivity(intent);
        finish();
    }
    public void Penyakit (View v){
        Intent intent=new Intent(Menu_Dashboard.this,Menu_History.class);
        startActivity(intent);
        finish();
    }
}