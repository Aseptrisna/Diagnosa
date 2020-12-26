package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;

public class Menu_Dashboard extends AppCompatActivity {
    SharedPrefManager sharedPrefManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__dashboard);
        sharedPrefManager=new SharedPrefManager(Menu_Dashboard.this);
//        Toast.makeText(this, ""+sharedPrefManager.getUser(), Toast.LENGTH_SHORT).show();
//        String User=sharedPrefManager.getUser();
//        JsonArray array=new JsonArray(User);
    }
}