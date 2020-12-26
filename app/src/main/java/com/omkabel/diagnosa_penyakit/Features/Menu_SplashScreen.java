package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omkabel.diagnosa_penyakit.Controller.Request;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.View.MyRequest;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_SplashScreen extends AppCompatActivity implements MyRequest {
    ProgressDialog loading;
    Request request;
    @BindView(R.id.Splash_Screen)
    LinearLayout Menu_SplashScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__splash_screen);
        loading=new ProgressDialog(Menu_SplashScreen.this);
        request=new Request(this);
        ButterKnife.bind(this);
        loading.setMessage("Mohon Tunggu Sebentar");
        loading.setCancelable(true);
        loading.show();
        Request_Server();
    }

    private void Request_Server() {
        request.RequestServer();
    }

    private void Goto_Login() {
        Intent i=new Intent(Menu_SplashScreen.this,Menu_Login.class);
        startActivity(i);
        finish();

    }
    @Override
    public void ServerResponse(String Message){
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        Goto_Login();
    }
    @Override
    public void ServerNoResponse(String Message){
        loading.dismiss();
        showSnackbar(Message);

    }
    @Override
    public void NoInternet(String Message){
        loading.dismiss();
        showSnackbar(Message);
    }
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(Menu_SplashScreen,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Coba Kembali?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       request.RequestServer();
                    }
                });
        snackbar.show();
    }

}