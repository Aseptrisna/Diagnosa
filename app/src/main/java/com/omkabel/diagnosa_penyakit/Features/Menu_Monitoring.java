package com.omkabel.diagnosa_penyakit.Features;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.omkabel.diagnosa_penyakit.Controller.Kontrol;
import com.omkabel.diagnosa_penyakit.R;
import com.omkabel.diagnosa_penyakit.Server.InitRetrofit;
import com.omkabel.diagnosa_penyakit.Server.Koneksi_RMQ;
import com.omkabel.diagnosa_penyakit.Server.MyRmq;
import com.omkabel.diagnosa_penyakit.Session.SharedPrefManager;
import com.omkabel.diagnosa_penyakit.View.MyKontol;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Menu_Monitoring extends AppCompatActivity implements MyRmq, MyKontol {
    @BindView(R.id.valuesuhu)
    TextView Suhu;
    @BindView(R.id.valuejam)
    TextView Jam;
    @BindView(R.id.valuedate)
    TextView Tanggal ;
    @BindView(R.id.valueBtnLampu)
    TextView Lampu;
    @BindView(R.id.valueButtonKipas)
    TextView Kipas;
    @BindView(R.id.btnKipason)
    Button KipasOn;
    @BindView(R.id.btnKipasoff)
    Button KipasOff;
    @BindView(R.id.btnLampuon)
    Button LampuOn;
    @BindView(R.id.btnLampuoff)
    Button LampuOff;
    @BindView(R.id.Suhu_Max)
    TextView Max_Suhu;
    @BindView(R.id.Suhu_Min)
    TextView Min_Suhu;
    private OkHttpClient client;
    private Request request;
    private String TAG = "data";
    ProgressDialog Loading;
    Kontrol kontrol;
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__monitoring);
        ButterKnife.bind(this);
        sharedPrefManager=new SharedPrefManager(this);
        Loading=new ProgressDialog(Menu_Monitoring.this);
        kontrol=new Kontrol(Menu_Monitoring.this);
        Max_Suhu.setText("suhu mx: "+sharedPrefManager.getSP_max()+"");
        Min_Suhu.setText("suhu min: "+sharedPrefManager.getSP_min()+"");
//        request=new Request(Menu_Monitoring.this);
        getsuhu();
        KipasOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.setMessage("Loading......");
                Loading.setCancelable(true);
                Loading.show();
                kipas("on");
            }
        });
        KipasOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.setMessage("Loading......");
                Loading.setCancelable(true);
                Loading.show();
                kipas("off");
            }
        });
        LampuOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.setMessage("Loading......");
                Loading.setCancelable(true);
                Loading.show();
                lampu("on");
            }
        });
        LampuOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Loading.setMessage("Loading......");
                Loading.setCancelable(true);
                Loading.show();
                lampu("off");
            }
        });
    }
    private void getsuhu() {
        Koneksi_RMQ rmq = new Koneksi_RMQ(this);
        rmq.setupConnectionFactory();
        final Handler incomingMessageHandler = new Handler() {
            @SuppressLint("HandlerLeak")
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void handleMessage(Message msg) {
                String message = msg.getData().getString("msg");
                Log.d("RMQMessage", message);
                String s = message.toString();
//                Suhu.setText(s);
                try {
                    JSONObject jsonRESULTS = new JSONObject(s);
                    String mac = jsonRESULTS.getString("mac");
                    String suhu = jsonRESULTS.getString("suhu");
                    String lampu = jsonRESULTS.getString("lampu");
                    String kipas = jsonRESULTS.getString("kipas");
                    Suhu.setText(suhu + "°C");
//                    String data=suhu.substring(0,4);
//                    try {
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
                    String max=sharedPrefManager.getSP_max();
                    String min=sharedPrefManager.getSP_min();
                    float SuhuMax=Float.parseFloat(max);
                    float SuhuMin=Float.parseFloat(min);
                    float dataSuhu=Float.parseFloat(suhu);
                    Log.d("min", String.valueOf(SuhuMin));
                    Log.v("max", String.valueOf(SuhuMax));
                    Log.v("suhu", String.valueOf(dataSuhu));


                    if (dataSuhu > SuhuMax){
                        kontrol.Kontrol_Kipas1("on");
                        kontrol.Kontrol_Lampu1("off");
                    }else if(dataSuhu < SuhuMin){
                        kontrol.Kontrol_Kipas1("off");
                        kontrol.Kontrol_Lampu1("on");

                    }else{
                        kontrol.Kontrol_Kipas1("off");
                        kontrol.Kontrol_Lampu1("off");
                    }
                    Kipas.setText(kipas);
                    Lampu.setText(lampu);
                    getjam();
                    gettanggal();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread subscribeThread = new Thread();
        String data = "dani";
        rmq.subscribe(incomingMessageHandler, subscribeThread, data, data);

    }
    private void getjam(){
        DateFormat jam = new SimpleDateFormat("dd-MM-yyyy");
        Date Formatjam = new Date();
        Tanggal.setText(jam.format(Formatjam));
    }

    private void gettanggal() {
        DateFormat dateFormattanggal = new SimpleDateFormat("HH:mm:ss");
        Date tanggal = new Date();
        Jam.setText(dateFormattanggal.format(tanggal));
    }

    @Override
    public void onBackPressed(){
        Goto_Dashboard();
    }

    private void Goto_Dashboard() {
        Intent intent=new Intent(Menu_Monitoring.this,Menu_Dashboard.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void Berhasil(String message) {
        Loading.dismiss();

    }

    @Override
    public void Gagal() {

    }
    private void kipas(String Message){
        kontrol.Kontrol_Kipas(Message);

    }

    private void lampu(String Message){
        kontrol.Kontrol_Lampu(Message);

    }
    private void kipas1(String Message){
        kontrol.Kontrol_Kipas1(Message);

    }

    private void lampu1(String Message){
        kontrol.Kontrol_Lampu1(Message);

    }
    @Override
    public void BerhasilKontrol(String Message){
        Loading.dismiss();
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void GagalKontrol(String Message){
        Loading.dismiss();
        Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show();

    }
    @Override
    public void NoInternet(String Message){
        Loading.dismiss();
        Toast.makeText(this, "Tidak ada koneksi Internet", Toast.LENGTH_SHORT).show();
    }
}