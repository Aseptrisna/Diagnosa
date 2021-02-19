package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Setting extends AppCompatActivity {
    @BindView(R.id.BtnSetting)
    Button Submit;
    @BindView(R.id.suhumax)
    EditText SuhuMax;
    @BindView(R.id.suhumin)
    EditText Suhumin;
    SharedPrefManager sharedPrefManager;
    ProgressDialog loading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__setting);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loading.setMessage("....");
                loading.show();
                loading.setCancelable(true);
                sharedPrefManager.saveSPString(SharedPrefManager.SP_max,SuhuMax.getText().toString());
                sharedPrefManager.saveSPString(sharedPrefManager.SP_min,Suhumin.getText().toString());
                mantaps();
            }
        });

    }

    private void mantaps() {
        loading.dismiss();
        SuhuMax.setText("");
        Suhumin.setText("");
        Toast.makeText(this, "Berhasil Setting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_Setting.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();
    }
}