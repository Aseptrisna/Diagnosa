package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omkabel.diagnosa_penyakit.Controller.User;
import com.omkabel.diagnosa_penyakit.Model.Model_User;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;
import com.omkabel.diagnosa_penyakit.View.ViewUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Login extends AppCompatActivity implements ViewUser {
    @BindView(R.id.LoginEmail)
    EditText Email;
    @BindView(R.id.LoginPassword)
    EditText Password;
    @BindView(R.id.BtnLogin)
    Button Login;
    @BindView(R.id.BtnNoAkun)
    Button Register;
    @BindView(R.id.Formlogin)
    CardView FromLogin;
    ProgressDialog  loading;
    SharedPrefManager sharedPrefManager;
    User user;
    Animation uptodown, downtoup,Fadein,FadeOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__login);
        ButterKnife.bind(this);
        loading=new ProgressDialog(this);
        user=new User(this);
        sharedPrefManager=new SharedPrefManager(Menu_Login.this);
        Session();
        uptodown = AnimationUtils.loadAnimation(this, R.anim.to_left);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.to_right);
        Fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        FadeOut= AnimationUtils.loadAnimation(this, R.anim.fade_out);
        FromLogin.setAnimation(downtoup);
    }

    private void Session() {
        if (sharedPrefManager.getSudahLogin()){
            startActivity(new Intent(Menu_Login.this, Menu_Dashboard.class)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
            finish();
        }
    }

    private void Goto_Register(){
        Intent intent=new Intent(Menu_Login.this,Menu_Register.class);
        startActivity(intent);
        finish();
    }
    public void ButtonLoginRegister(View V){
        Goto_Register();
    }
    public void ButtonLogin(View view){
        ChekInputan();

    }

    private void ChekInputan() {
        String email=Email.getText().toString();
        String pass=Password.getText().toString();
        String Message="Email dan Password Tidak boleh Kosong";
        if (email.isEmpty()||pass.isEmpty()){
            showSnackbar(Message);
        } else {
            loading.setMessage("Loading..............");
            loading.setCancelable(true);
            loading.show();
            user.UserLogin(email,pass);
        }
    }
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(FromLogin,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(FromLogin, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        Email.setFocusableInTouchMode(true);
                        Password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }

    @Override
    public void Berhasil(String Message) {

    }

    @Override
    public void BerhasilLogin(List<Model_User>user){
        loading.dismiss();
        Toast.makeText(this, "Berhasil Login", Toast.LENGTH_SHORT).show();
        Log.i("user",user.toString());
        LoginSave(user);
        Goto_Dashboard();
    }

    private void LoginSave(List<Model_User> User) {
        String id=User.get(0).getId();
        String Nama=User.get(0).getNama();
        String Email=User.get(0).getEmail();
        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, id);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_NAMA, Nama);
        sharedPrefManager.saveSPString(SharedPrefManager.SP_EMAIL, Email);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, true);
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_Login.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();

    }

    @Override
    public void GagalLogin(String Message){
        loading.dismiss();
        showSnackbar(Message);
    }
    @Override
    public void NoInternet(String Message){
        loading.dismiss();
        showSnackbar(Message);
    }
    @Override
    public void onBackPressed(){
        Activity meActivity = Menu_Login.this;
        Menu_Login.super.onBackPressed();
        AlertDialog.Builder builder = new AlertDialog.Builder(meActivity );
        builder.setTitle("Mohon Konfirmasi");
        builder.setMessage("Apakah Anda Yakin akan keluar dari Aplikasi?");
        builder.setCancelable(true);
        builder.setPositiveButton("Iya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Menu_Login.super.onBackPressed();
            }
        });
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(Menu_Login.this,"Terima Kasih",Toast.LENGTH_LONG).show();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}