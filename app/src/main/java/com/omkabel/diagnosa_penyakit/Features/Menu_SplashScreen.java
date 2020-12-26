package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
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
    Animation uptodown, downtoup,Fadein,FadeOut;
    @BindView(R.id.iconapk)
    ImageView iconApk;
    @BindView(R.id.welcome)
    TextView Welcome;
    @BindView(R.id.copyright)
    TextView CopyRight;
    int waktu_loading=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__splash_screen);
        loading=new ProgressDialog(Menu_SplashScreen.this);
        request=new Request(this);
        ButterKnife.bind(this);
        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        FadeOut= AnimationUtils.loadAnimation(this, R.anim.fade_out);
        iconApk.setAnimation(uptodown);
        Welcome.setAnimation(downtoup);
        CopyRight.setAnimation(downtoup);
        Loading();
    }

    private void Request_Server() {
        loading.setMessage("Mohon Tunggu Sebentar");
        loading.setCancelable(true);
        loading.show();
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
//        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        Goto_Login();
//        Loading();
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
                        loading.setMessage("Mohon Tunggu Sebentar");
                        loading.setCancelable(true);
                        loading.show();
                       request.RequestServer();
                    }
                });
        snackbar.show();
    }
    public void Loading(){
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Request_Server();
            }
        },waktu_loading);
    }
}