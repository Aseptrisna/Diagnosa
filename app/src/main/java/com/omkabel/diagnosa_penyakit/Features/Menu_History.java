package com.omkabel.diagnosa_penyakit.Features;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.omkabel.diagnosa_penyakit.Adapter.Adapter_History;
import com.omkabel.diagnosa_penyakit.Controller.Sensor;
import com.omkabel.diagnosa_penyakit.Model.Model_Sensor;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.View.MySensor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Menu_History extends AppCompatActivity implements MySensor {
    ProgressDialog progressDialog;
    @BindView(R.id.list_history)
    RecyclerView recyclerView;
    ProgressDialog loading;
    Sensor sensor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__history);
        ButterKnife.bind(this);
        loading=new ProgressDialog(this);
        sensor=new Sensor(this);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager llm=new GridLayoutManager(Menu_History.this,1);
        recyclerView.setLayoutManager(llm);
        getsensor();
    }
    public void getsensor(){
        sensor.getSensor();

    }


    @Override
    public void Berhasil(List<Model_Sensor> sensors) {
        loading.dismiss();
        Adapter_History adapter_history=new Adapter_History(Menu_History.this,sensors);
        recyclerView.setAdapter(adapter_history);

    }
    @Override
    public void Gagal(String Message){
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }
    @Override
    public void NoInternet(String Message){
        loading.dismiss();
        Toast.makeText(this, Message, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_History.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();
    }
}