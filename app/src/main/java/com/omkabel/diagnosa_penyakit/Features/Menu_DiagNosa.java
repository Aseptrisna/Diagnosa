package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.omkabel.diagnosa_penyakit.R;

public class Menu_DiagNosa extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__diag_nosa);
    }
    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_DiagNosa.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();
    }
}