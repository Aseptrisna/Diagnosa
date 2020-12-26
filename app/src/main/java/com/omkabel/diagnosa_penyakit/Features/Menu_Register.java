package com.omkabel.diagnosa_penyakit.Features;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.omkabel.diagnosa_penyakit.Controller.User;
import com.omkabel.diagnosa_penyakit.Model.Model_User;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.View.ViewUser;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_Register extends AppCompatActivity implements ViewUser {
    @BindView(R.id.RegisterEmail)
    EditText email;
    @BindView(R.id.RegisterName)
    EditText nama;
    @BindView(R.id.RegsiterPassword)
    EditText password;
    ProgressDialog loading;
    User user;
    @BindView(R.id.FormRegister)
    CardView Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__register);
        loading=new ProgressDialog(Menu_Register.this);
        user=new User(this);
        ButterKnife.bind(this);
    }

    public void BtnRegister(View view){
        Validasi();

    }
    public void BtnLoginRegister(View view){
        Goto_Login();

    }

    private void Goto_Login() {
        Intent intent=new Intent(Menu_Register.this,Menu_Login.class);
        startActivity(intent);
        finish();
    }

    private void Validasi() {
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
            user.UserRegister(Nama,Email,Pass);

        }
    }

    public void showSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(Register,""+message , Snackbar.LENGTH_INDEFINITE)
                .setAction("Ulangi?", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Snackbar snackbar1 = Snackbar.make(Register, "Silahkan Ulangi", Snackbar.LENGTH_SHORT);
                        snackbar1.show();
                        nama.setFocusableInTouchMode(true);
                        email.setFocusableInTouchMode(true);
                        password.setFocusableInTouchMode(true);
                    }
                });
        snackbar.show();
    }
    @Override
    public void Berhasil(String Message){
        loading.dismiss();
        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        Goto_Login();

    }

    @Override
    public void BerhasilLogin(List<Model_User> user) {

    }

    @Override
    public void GagalLogin(String Message) {
        loading.dismiss();
//        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
        showSnackbar(Message);
    }

    @Override
    public void NoInternet(String Message) {
        loading.dismiss();
        showSnackbar(Message);
//        Toast.makeText(this, ""+Message, Toast.LENGTH_SHORT).show();
    }
}