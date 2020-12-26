package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class Menu_UpdateProfile extends AppCompatActivity implements ViewUser {
SharedPrefManager sharedPrefManager;
ProgressDialog loading;
User user;
    @BindView(R.id.UpdateEmail)
    EditText email;
    @BindView(R.id.UpdateName)
    EditText nama;
    @BindView(R.id.UpdatePassword)
    EditText password;
    @BindView(R.id.FormUpdate)
    CardView Update;
    Animation toup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__update_profile);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        loading=new ProgressDialog(this);
        user=new User(this);
        email.setText(sharedPrefManager.getSPEmail());
        nama.setText(sharedPrefManager.getSPNama());
        toup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        Update.setAnimation(toup);
    }

    public  void BtnUpdateProfile(View view){
        Validasi();

    }

    private void Validasi() {
        String Id=sharedPrefManager.getSpId();
        String Email=email.getText().toString();
        String Nama=nama.getText().toString();
        String Pass=password.getText().toString();
        String Message="Kolom Tidak Boleh Kosong";
        if (Email.isEmpty()||Nama.isEmpty()||Pass.isEmpty()){
            showSnackbar(Message);
        }else {
            loading.setMessage("Loading......");
            loading.show();
            loading.setCancelable(true);
            user.userupdate(Id,Nama,Email,Pass);

        }
    }
    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(Update,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(Update, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        nama.setFocusableInTouchMode(true);
                        email.setFocusableInTouchMode(true);
                        password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_UpdateProfile.this,Menu_Profile.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void Berhasil(String Message) {
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        Exit();

    }

    private void Exit() {
        SharedPrefManager sharedPrefManager=new SharedPrefManager(this);
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(this, Menu_Login.class));
        finish();
    }

    @Override
    public void BerhasilLogin(List<Model_User> user) {

    }

    @Override
    public void GagalLogin(String Message) {
        loading.dismiss();
        showSnackbar(Message);

    }

    @Override
    public void NoInternet(String Message) {
        loading.dismiss();
        showSnackbar(Message);

    }
}