package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Profile extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;
    @BindView(R.id.emailprofile)
    TextView Email;
    @BindView(R.id.namaprofile)
    TextView Nama;
    @BindView(R.id.Profile)
    LinearLayout Profile;
    Animation toRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__profile);
        ButterKnife.bind(Menu_Profile.this);
        sharedPrefManager=new SharedPrefManager(this);
        String email=sharedPrefManager.getSPEmail();
        Email.setText(email);
        Nama.setText(sharedPrefManager.getSPNama());
//        Toast.makeText(this,sharedPrefManager.getSPEmail(), Toast.LENGTH_SHORT).show();
        toRight = AnimationUtils.loadAnimation(this, R.anim.to_right);
        Profile.setAnimation(toRight);
    }



    public void BtnLogout(View v){
        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(this, Menu_Login.class));
        finish();

    }
    public void BtnUpdate(View view){
        Intent intent=new Intent(Menu_Profile.this,Menu_UpdateProfile.class);
        startActivity(intent);
        finish();

    }
    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_Profile.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();
    }
}